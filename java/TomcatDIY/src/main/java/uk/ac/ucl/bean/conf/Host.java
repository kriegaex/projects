package uk.ac.ucl.bean.conf;

import org.apache.logging.log4j.LogManager;
import uk.ac.ucl.context.Context;
import uk.ac.ucl.util.Constant;
import uk.ac.ucl.util.io.ServerXMLParsing;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Host {
    private String name;
    private Map<String, Context> contextMap;
    private Engine engine;

    public Host(String name, Engine engine) {
        this.contextMap = new HashMap<>();
        this.name = name;
        this.engine = engine;

        scanContextRootFolder();
        scanServerXml();
    }

    /**
     * Scan all of the files and directories under /webapp, creating context for each of them
     * and then put them into a map
     */
    private void scanContextRootFolder() {
        File[] files = Constant.rootFolder.listFiles();
        // Adding the context of the root folder to the context map
        String rootPath = "/";
        String rootDocBase = Constant.rootFolder.getAbsolutePath();
        contextMap.put(rootPath, new Context(rootPath, rootDocBase, this, true));
        // Adding contexts of all directories under root folder to the context map
        for (File file : files){
            String path = "/" + file.getName();
            String docBase;

            if (file.isDirectory()){
                docBase = file.getAbsolutePath();
            }
            else{
                docBase = file.getParentFile().getAbsolutePath();
            }
            Context context = new Context(path, docBase, this, true);
            contextMap.put(context.getPath(), context);
        }
    }

    /**
     * Scan the /conf/server.xml to find all contexts nodes and put corresponding Context
     * into context map
     */
    private void scanServerXml() {
        List<Context> contextList = ServerXMLParsing.getContexts(this);
        for (Context context : contextList){
            contextMap.put(context.getPath(), context);
        }
    }

    /**
     * Record important information, then delete the old context from contextMap
     * Iniliasing a new context with old basic information, then add it to the contextMap
     * @param context
     */
    public void reload(Context context) {
        LogManager.getLogger().info("Reloading context : [{}] has started", context.getPath());

        String path = context.getPath();
        String docBase = context.getDocBase();
        boolean reloadable = context.isReloadable();

        contextMap.remove(path);

        Context newContxt = new Context(path, docBase, this, reloadable);
        contextMap.put(path, newContxt);
    }

    public String getName() { return name; }

    public Context getContext(String path) {
        return contextMap.get(path); }
}
