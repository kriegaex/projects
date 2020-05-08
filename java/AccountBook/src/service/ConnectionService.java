package service;

import bean.Config;
import dao.ConfigDAO;

public class ConnectionService {
    final public static String IP = "IP";
    final public static String PORT = "port";
    final public static String SCHEMA = "schema";
    final public static String ENCODING = "endoding";
    final public static String LOGIN = "login name";
    final public static String PASSWD = "password";

    static { init(); }


    private static void init(){
        ConfigDAO dao = new ConfigDAO();
        String[] keys = new String[] {IP, PORT, SCHEMA, ENCODING, LOGIN, PASSWD};
        for (String key : keys){
            if (dao.getByKey(key) == null){
                Config newConfig = new Config();
                newConfig.setValue("");
                newConfig.setKey(key);
                dao.add(newConfig);
            }
        }
    }


    public static boolean isConnected(){
        ConfigDAO dao = new ConfigDAO();
        String[] keys = new String[] {IP, PORT, SCHEMA, ENCODING, LOGIN, PASSWD};
        for (String key : keys){
           if (dao.getByKey(key).getValue().compareTo("") == 0){
               return false;
           }
        }
        return true;
    }
}
