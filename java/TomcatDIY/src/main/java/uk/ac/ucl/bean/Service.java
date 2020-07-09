package uk.ac.ucl.bean;

import uk.ac.ucl.util.io.ServerXMLParsing;

public class Service {
    private String name;
    private Engine engine;
    private Server server;

    public Service(Server server){
        this.server = server;
        this.name = ServerXMLParsing.getServiceName();
        this.engine = new Engine(this);
    }

    public Engine getEngine() { return engine; }

    public Server getService() { return server; }
}
