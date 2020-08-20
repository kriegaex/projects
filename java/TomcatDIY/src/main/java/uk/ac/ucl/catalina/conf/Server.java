package uk.ac.ucl.catalina.conf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.ac.ucl.util.core.TimeUtil;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Server {
    private Logger logger = LogManager.getLogger();

    public Service service;

    public Server(){
        this.service = new Service(this);
    }

    public void start(){
        TimeUtil timeUtil = new TimeUtil();
        logJVM();
        init();
        logger.info("Server startup in {} ms", timeUtil.interval());
    }

    private void init() {
        service.start();
    }

    /**
     * Display the server information
     */
    private static void logJVM() {
        Map<String,String> infos = new LinkedHashMap<>();
        infos.put("Server version", "Chaozy's DiyTomcat/1.0.1");
        infos.put("Server built", "2020-07-01 10:20:22");
        infos.put("Server number", "1.0.1");
        infos.put("OS Name\t", System.getProperty("os.name"));
        infos.put("OS Version", System.getProperty("os.version"));
        infos.put("Architecture", System.getProperty("os.arch"));
        infos.put("Java Home", System.getProperty("java.home"));
        infos.put("JVM Version", System.getProperty("java.runtime.version"));
        infos.put("JVM Vendor", System.getProperty("java.vm.specification.vendor"));

        Set<String> keys = infos.keySet();
        Logger logger = LogManager.getLogger(Server.class.getName());
        for (String key : keys) {
            logger.info(key+":\t\t" + infos.get(key));
        }
    }

}

