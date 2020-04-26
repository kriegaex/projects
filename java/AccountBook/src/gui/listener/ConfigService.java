package gui.listener;

import dao.ConfigDAO;

/**
 * BEFORE passing the data to ConfigDAO, data has to be pre-disposed
 * Structure: Listener --> service --> DAO (<-- entity ) --> database
 */
public class ConfigService {
    private static String budget = "BUDGET";
    private static String mysqlPath = "mysqlPath";
    private static String defaultBudget = "500";

    static ConfigDAO dao = new ConfigDAO();
    static
}
