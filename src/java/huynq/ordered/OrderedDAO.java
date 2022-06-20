/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynq.ordered;

import huynq.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;

/**
 *
 * @author huy
 */
public class OrderedDAO implements Serializable{
     public int addOrder(String username, Timestamp time, double total) 
        throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // 1. Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                // 2. Create SQL String
                String sql = "INSERT INTO Ordered (order_date, total, username) "
                        + "VALUES (?, ?, ?)";
                // 3. Create Statement & Assign Paramaters' values
                stmt = con.prepareStatement(sql);
                stmt.setTimestamp(1, time);
                stmt.setDouble(2, total);
                stmt.setString(3, username);
                // 4. Execute Query
                int row = stmt.executeUpdate();
                
                // 5. Process rs
                if (row > 0) {
                    sql = "SELECT TOP 1 id FROM Ordered ORDER BY id DESC ";
                    stmt = con.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        return rs.getInt("id");
                    }
                }
            } // EndIf con is connected
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con!= null) {
                con.close();
            }
        }
        return 0;
    }
}
