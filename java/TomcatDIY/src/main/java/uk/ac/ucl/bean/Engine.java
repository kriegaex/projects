package uk.ac.ucl.bean;

import uk.ac.ucl.util.io.ServerXMLParsing;

import java.util.List;

public class Engine {
    private String defaultHost;
    private List<Host> hosts;
    private Service service;

    public Engine(Service service){
        this.defaultHost = ServerXMLParsing.getEngineDefaultHostName();
        this.service = service;
        System.out.println(defaultHost);
        this.hosts = ServerXMLParsing.getHosts(this);
    }

    private void checkDefault(){
        if (getDefaultHost() == null){
            throw new RuntimeException("Default Host " +
                    defaultHost + " does not exists");
        }
    }

    public Host getDefaultHost(){
        for (Host host : hosts){
            if (host.getName().equals(defaultHost)){
                return host;
            }
        }
        return null;
    }
}
