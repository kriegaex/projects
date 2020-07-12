package uk.ac.ucl.util.io;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.ac.ucl.bean.conf.*;
import uk.ac.ucl.util.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerXMLParsing {

    /**
     * Read context nodes
     * @return
     */
    public static List<Context> getContexts(){
        List<Context> list = new ArrayList<>();
        try {
            Document document = Jsoup.parse(Constant.confServerXML, "utf-8");
            Elements elements = document.select("Context");
            for (Element element : elements){
                String path = element.attr("path");
                String docBase = element.attr("docBase");
                Context context = new Context(path, docBase);
                list.add(context);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Read default host name
     * @return
     */
    public static String getEngineDefaultHostName(){
        String hostName = null;
        try {
            Document document = Jsoup.parse(Constant.confServerXML, "utf-8");
            Element host = document.select("Engine").first();
            hostName = host.attr("defaultHost");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hostName;
    }

    /**
     * Read service name
     * @return
     */
    public static String getServiceName(){
        String serviceName = null;
        try {
            Document document = Jsoup.parse(Constant.confServerXML, "utf-8");
            Element host = document.select("Service").first();
            serviceName = host.attr("name");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serviceName;
    }

    /**
     * Read all the hosts in the given engine
     * @param engine
     * @return
     */
    public static List<Host> getHosts(Engine engine){
        List<Host> hosts = new ArrayList<>();
        try {
            Document document = Jsoup.parse(Constant.confServerXML, "utf-8");
            Elements elements = document.select("Host");
            for (Element element : elements){
                Host host = new Host(element.attr("name"), engine);
                hosts.add(host);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hosts;
    }

    /**
     * Read all the connectors in a service
     */
    public static List<Connector> getConnectors(Service service) {
        List<Connector> connectors = new ArrayList<>();
        try {
            Document document = Jsoup.parse(Constant.confServerXML, "utf-8");
            Elements elements = document.select("Connector");
            for (Element element : elements) {
                int port = (Integer.parseInt(element.attr("port")));
                Connector connector = new Connector(service);
                connector.setPort(port);
                connectors.add(connector);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connectors;
    }
}

