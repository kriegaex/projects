package uk.ac.ucl.util.io;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.ac.ucl.bean.Context;
import uk.ac.ucl.util.Constant;

import java.io.File;
import java.io.IOException;

public class WebXMLParsing {
    public static String getWelcomeFileName(Context context) {
        try {
            Document document = Jsoup.parse(Constant.webXMLFile, "utf-8");
            Elements elements = document.select("welcome-file");
            for (Element element : elements){
                File file = new File(context.getDocBase(), element.text());
                if (file.exists()){
                    return element.text();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index.html";
    }
}
