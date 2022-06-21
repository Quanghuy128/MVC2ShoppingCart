/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynq.registration;

import huynq.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author huy
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1.Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Select username "
                        + "From Registration "
                        + "Where username = ? and password = ?";
                //3.Create Statement Object
                stmt = con.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                //4.Execute Query
                rs = stmt.executeQuery();
                //5.Process result
                if(rs.next()){
                    result = true; 
                }
            }//end if con is not null
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
    
    public boolean getRole(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1.Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Select isAdmin "
                        + "From Registration "
                        + "Where username = ? and password = ?";
                //3.Create Statement Object
                stmt = con.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                //4.Execute Query
                rs = stmt.executeQuery();
                //5.Process result
                if(rs.next()){
                    result = rs.getBoolean("isAdmin"); //return role to switch page
                }
            }//end if con is not null
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
    
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    public void searchLastname(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //1.Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname like ?";
                //3.Create Statement Object
                stmt = con.prepareStatement(sql);
                stmt.setString(1, "%" + searchValue + "%");
                //4.Execute Query
                rs = stmt.executeQuery();
                //5.Process result
                while(rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username,password,fullName,role);
                    if(this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//end accounts are 
                    this.accounts.add(dto);
                }
            }//end if con is not null
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public boolean deleteAccount(String username)
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            //1.Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3.Create Statement Object
                stmt = con.prepareStatement(sql);
                stmt.setString(1,username);
                //4.Execute Query
                int affectedRows = stmt.executeUpdate();
                //5.Process result
                if(affectedRows > 0) {
                    result = true;
                }
            }//end if con is not null
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
    public boolean updateAccount(String username,String password,boolean role)
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            //1.Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL String
                
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ?";
                //3.Create Statement Object
                stmt = con.prepareStatement(sql);
                stmt.setString(1,password);
                stmt.setBoolean(2,role);
                stmt.setString(3,username);
                //4.Execute Query
                int affectedRows = stmt.executeUpdate();
                //5.Process result
                if(affectedRows > 0) {
                    result = true;
                }
            }//end if con is not null
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
    public boolean createAccount(String username, String password, String fullname, boolean isAdmin) 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            //1.make connection
            con = DBHelper.makeConnection();
            //2.create sql string
            String sql = "Insert into Registration(username, password, lastname, isAdmin) "
                        + "Values(?,?,?,?)";
            //3.create statement
            stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, fullname);
            stmt.setBoolean(4, isAdmin);
            //4.execute 
            int affectedRows = stmt.executeUpdate();
            //5.process
            if(affectedRows > 0) {
                result = true;
            }
        }finally {
            if(stmt != null) {
                stmt.close();
            }
            if(con != null) {
                con.close();
            }
        }
        return result;
    }
}
