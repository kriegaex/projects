package uk.ac.ucl.bean.conf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.ucl.util.core.TimeUtil;

/**
 * path means the path to access in url
 * docBase means its absolute path in the project
 */
public class Context {
    private String path;
    private String docBase;

    public Context(String path, String docBase){
        Logger logger = LogManager.getLogger();
        TimeUtil clock = new TimeUtil();
        this.path = path;
        this.docBase = docBase;
        logger.info("Deploying web application directory {}", this.docBase);
        logger.info("Deployment of web application directory {} has finished in {} ms",
                this.docBase, clock.interval());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDocBase() {
        return docBase;
    }

    public void setDocBase(String docBase) {
        this.docBase = docBase;
    }

}
