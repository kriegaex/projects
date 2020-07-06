package uk.ac.ucl.util.io;

import org.apache.logging.log4j.LogManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.ac.ucl.bean.Context;
import uk.ac.ucl.util.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerXMLParsing {
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
}

