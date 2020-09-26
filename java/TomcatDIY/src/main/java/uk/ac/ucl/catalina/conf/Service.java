package uk.ac.ucl.catalina.conf;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import uk.ac.ucl.util.core.TimeUtil;
import uk.ac.ucl.util.io.ServerXMLParsing;

import java.util.List;

@Getter
public class Service {
    private String name;
    private Engine engine;
    private Server server;
    private List<Connector> connectors;

    public Service(Server server){
        this.server = server;
        this.name = ServerXMLParsing.getServiceName();
        this.engine = new Engine(this);
        this.connectors = ServerXMLParsing.getConnectors(this);
    }

    public void start() { init(); }

    public void init() {
        TimeUtil timeUtil = new TimeUtil();
        for (Connector connector : connectors) {
            connector.init();
        }
        LogManager.getLogger().info("Initialization processed in {} ms",
                timeUtil.interval());
        for (Connector connector : connectors) {
            connector.start();
        }
    }
}
