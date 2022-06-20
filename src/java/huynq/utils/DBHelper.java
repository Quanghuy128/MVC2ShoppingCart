
package huynq.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author huy
 */
public class DBHelper implements Serializable {
    public static Connection makeConnection()
        throws SQLException, NamingException {
//        //1.load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//classnotfound
//        //2. Make Connection String: protocol://ip:port;databaseName=db[;instanceName=name]
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=HUYNQ;instanceName=SQLEXPRESS";
//        //3. Open Connection
//        Connection connection = DriverManager.getConnection(url,"sa","12345"); //SQLException
//        return connection;

           Context context = new InitialContext(); //get context hien hanh`
           Context tomcatContext = (Context)context.lookup("java:comp/env"); //tomcat path in environment
           DataSource datasource = (DataSource) tomcatContext.lookup("DS007");
           Connection con = datasource.getConnection();
           return con;
    }
    
}
