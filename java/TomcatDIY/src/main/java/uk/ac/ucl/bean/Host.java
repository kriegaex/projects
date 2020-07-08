package uk.ac.ucl.bean;

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
        contextMap.put(rootPath, new Context(rootPath, rootDocBase));
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
            Context context = new Context(path, docBase);
            contextMap.put(context.getPath(), context);
        }
    }

    /**
     * Scan the /conf/server.xml to find all contexts nodes and put corresponding Context
     * into context map
     */
    private void scanServerXml() {
        List<Context> contextList = ServerXMLParsing.getContexts();
        for (Context context : contextList){
            contextMap.put(context.getPath(), context);
        }
    }

    public String getName() { return name; }

    public Context getContext(String path) { return contextMap.get(path); }
}
