package uk.ac.ucl.util.io;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.ac.ucl.bean.conf.Context;
import uk.ac.ucl.util.Constant;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebXMLParsing {
    private static Map<String, String> mimeTypeMapping = new HashMap<>();

    /**
     * Initialising the mime-type map
     * Since there is multi-threading in the server, to prevent the map being initialised
     * several times at the same time, this method has to add synchronized
     */
    private static synchronized void initMimeType(){
        try {
            Document document = Jsoup.parse(Constant.webXMLFile, "utf-8");
            Elements elements = document.select("mime-mapping");
            for (Element element : elements){
                String extension = element.select("extension ").first().text();
                String mimeType = element.select("mime-type").first().text();
                mimeTypeMapping.put(extension, mimeType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMimeType(String extension) {
        if (mimeTypeMapping.isEmpty()) { initMimeType(); }
        String mimeType = mimeTypeMapping.get(extension);

        if (mimeType == null){
            // return default type if no corresponding configuration
            return "text/html";
        }
        return mimeType;
    }

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
