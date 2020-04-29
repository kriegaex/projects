package gui.listener.Config;

import bean.Config;
import dao.ConfigDAO;

/**
 * BEFORE passing the data to ConfigDAO, data has to be pre-disposed
 * Structure: Listener --> service --> DAO (<-- entity ) --> database
 */
public class ConfigService {
    public static String budget = "budget";
    public static String mysqlPath = "sqlPath";
    private static String defaultBudget = "500";

    private static ConfigDAO dao = new ConfigDAO();

    static{
        init();
    }

    private static void init(){
        init(budget, defaultBudget);
        init(mysqlPath, "");
    }

    private static void init(String key, String value){
        Config config = dao.getByKey(key);
        if (config == null){
            Config newConfig = new Config();
            newConfig.setKey(key);
            newConfig.setValue(value);
            dao.add(newConfig);
        }
    }

    public void update(String key, String value){
        Config config = dao.getByKey(key);
        config.setValue(value);
        dao.update(config);
    }

    public String getValue(String key){
        Config config = dao.getByKey(key);
        return config.getValue();
    }

    public int getBudget(){ return Integer.parseInt(getValue(budget)); }
}
