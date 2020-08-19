package uk.ac.ucl.util.io;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.ac.ucl.context.Context;
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
    public static List<Context> getContexts(Host host){
        List<Context> list = new ArrayList<>();
        try {
            Document document = Jsoup.parse(Constant.confServerXML, "utf-8");
            Elements elements = document.select("Context");
            for (Element element : elements){
                String path = element.attr("path");
                String docBase = element.attr("docBase");
                boolean reloadable = element.attr("reloadable").equals("true");
                Context context = new Context(path, docBase, host, reloadable);
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
                int port = Integer.parseInt(element.attr("port"));
                String compression = element.attr("compression");
                int compressionMinSize;
                if (element.attr("compressionMinSize").equals("")){
                    compressionMinSize = 0;
                }
                else{
                    compressionMinSize =
                            Integer.parseInt(element.attr("compressionMinSize"));
                }

                String noCompressionUserAgent = element.attr("noCompressionUserAgent");
                String compressionMimeType = element.attr("compressionMimeType");

                Connector connector = new Connector(service);
                connector.setCompression(compression);
                connector.setCompressionMimeType(compressionMimeType);
                connector.setNoCompressionUserAgent(noCompressionUserAgent);
                connector.setCompressionMinSize(compressionMinSize);
                connector.setPort(port);
                connectors.add(connector);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connectors;
    }
}

