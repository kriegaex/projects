package bean;

import gui.MainPanel;
import gui.panel.ConnectionPanel;
import service.ConnectionService;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBUtil is responsible for the connection with MySql DataBase, configuration is stored here
 */
public class DBUtil {
    private static String ip = "127.0.0.1";
    private static int port = 3306;
    private static String schema = "AccountBook";

    private static String encoding = "UTF-8";
    private static String loginName = "root";
    private static String password = "james100";

    public static void setIp(String ip) { DBUtil.ip = ip; }

    public static void setPort(int port) { DBUtil.port = port; }

    public static void setSchema(String schema) { DBUtil.schema = schema; }

    public static void setEncoding(String encoding) { DBUtil.encoding = encoding; }

    public static void setLoginName(String loginName) { DBUtil.loginName = loginName; }

    public static void setPassword(String password) { DBUtil.password = password; }

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
