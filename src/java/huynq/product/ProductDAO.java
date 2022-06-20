/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynq.product;

import huynq.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author huy
 */
public class ProductDAO {

    public ProductDAO() {
    }
    
    private List<ProductDTO> items;

    public List<ProductDTO> getItems() {
        return items;
    }
    

    public void getItemList()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //1.Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Select id, name, quantity, price "
                        + "From Product";
                //3.Create Statement Object
                stmt = con.prepareStatement(sql);
                //4.Execute Query
                rs = stmt.executeQuery();
                //5.Process result
                while(rs.next()) {
                    ProductDTO dto = new ProductDTO(rs.getString("id"),
                                                    rs.getString("name"),
                                                    rs.getInt("quantity"),
                                                    rs.getFloat("price"));
                    if(this.items == null) {
                        this.items = new ArrayList<>();
                    }//end accounts are 
                    this.items.add(dto);
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
    
    public ProductDTO getProduct(String productName) 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
           //1.make connection
           con = DBHelper.makeConnection();
           //2.create sql string
           String sql = "Select id, name, quantity, price "
                        + "From Product "
                        + "Where name = ?";
           //3.create statement
           stmt = con.prepareStatement(sql);
           stmt.setString(1,productName);
           //4.execute query
           rs = stmt.executeQuery();
           //5.process
           if(rs.next()) {
                    ProductDTO dto = new ProductDTO(rs.getString("id"),
                                                    rs.getString("name"),
                                                    rs.getInt("quantity"),
                                                    rs.getFloat("price"));
                    return dto;
            }
        }finally {
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
        return null;
    }
    
    public int getQuantity(String productName) 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
           //1.make connection
           con = DBHelper.makeConnection();
           //2.create sql string
           String sql = "Select quantity "
                        + "From Product "
                        + "Where name = ?";
           //3.create statement
           stmt = con.prepareStatement(sql);
           stmt.setString(1,productName);
           //4.execute query
           rs = stmt.executeQuery();
           //5.process
           if(rs.next()) {
               return rs.getInt("quantity");
            }
        }finally {
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
        return 0;
    }
    
    public int setQuantity(String productName, int quantity) 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
           //1.make connection
           con = DBHelper.makeConnection();
           //2.create sql string
           String sql = "Update Product "
                        + "Set quantity = ? "
                        + "Where name = ?";
           //3.create statement
           stmt = con.prepareStatement(sql);
           stmt.setInt(1,quantity);
           stmt.setString(2,productName);
           //4.execute query
           rs = stmt.executeQuery();
           //5.process
           if(rs.next()) {
               return rs.getInt("quantity");
            }
        }finally {
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
        return 0;
    }
}
