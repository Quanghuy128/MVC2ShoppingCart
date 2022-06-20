/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynq.ordered_detail;

import huynq.utils.DBHelper;
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
public class OrderedDetailDAO {
    public boolean addOrderDetail(String title, int quantity, double price, double total, int order_id) 
        throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // 1. Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                // 2. Create SQL String
                String sql = "INSERT INTO Ordered_Detail (title, quantity, price, total, order_id) "
                        + "VALUES (?, ?, ?, ?, ?)";
                // 3. Create Statement & Assign Paramaters' values
                stmt = con.prepareStatement(sql);
                stmt.setString(1, title);
                stmt.setInt(2, quantity);
                stmt.setDouble(3, price);
                stmt.setDouble(4, total);
                stmt.setInt(5, order_id);
                // 4. Execute Query
                int row = stmt.executeUpdate();
                
                // 5. Process rs
                if (row > 0) {
                    return true;
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
        return false;
    }
}
