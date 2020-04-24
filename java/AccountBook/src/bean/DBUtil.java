package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is responsible for the connection with MySql DataBase
 */
public class DBUtil {
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String schema = "AccountBook";

    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "james100";
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, schema, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }

}
