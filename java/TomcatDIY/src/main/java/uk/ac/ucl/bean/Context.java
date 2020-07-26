package uk.ac.ucl.bean;

import org.apache.logging.log4j.LogManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.ac.ucl.classLoader.WebappClassLoader;
import uk.ac.ucl.exception.WebConfigDuplicateException;
import uk.ac.ucl.util.Constant;
import uk.ac.ucl.util.core.StrUtil;
import uk.ac.ucl.util.core.TimeUtil;
import uk.ac.ucl.util.io.ContextXMLUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * path means the path to access in url
 * docBase means its absolute path in the project
 *
 * Each web app has its own WebappClassLoader
 *
 * Also, url, servlet name and servlet class name map to each other
 */
public class Context {
    private String path;
    private String docBase;
    private File webXMLFile;
    private WebappClassLoader webappClassLoader;
    private Map<String, String> url_servletClassName;
    private Map<String, String> url_servletName;
    private Map<String, String> servletClassName_servletName;
    private Map<String, String> servletName_servletClassName;

    public Context(String path, String docBase){
        this.path = path;
        this.docBase = docBase;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        this.webappClassLoader = new WebappClassLoader(docBase, classLoader);

        this.webXMLFile = new File(docBase, ContextXMLUtil.getWatchedResources());

        this.url_servletClassName = new HashMap<>();
        this.url_servletName = new HashMap<>();
        this.servletClassName_servletName = new HashMap<>();
        this.servletName_servletClassName = new HashMap<>();
        deploy();


    }

    private void deploy() {
        TimeUtil timeUtil = new TimeUtil();
        LogManager.getLogger().info(
                "Deploying web application directory {}", this.docBase);
        init();
        LogManager.getLogger().info("Deployment of web application directory {}" +
                " has finished at {} ms", this.docBase, timeUtil.interval());
    }

    private void init() {
        if (!webXMLFile.exists()) { return; }
        try {
            checkDuplicate();
        } catch (WebConfigDuplicateException e) {
            e.printStackTrace();
            return;
        }
        try {
            Document document = Jsoup.parse(webXMLFile, "utf-8");
            parseServletMapping(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseServletMapping(Document document) {
        // servlet name and servlet class name
        Elements servletNames = document.select("servlet servlet-name");
        for (Element servletName : servletNames) {
            String name = servletName.text();
            String className =
                    servletName.parent().select("servlet-class").first().text();
            servletName_servletClassName.put(name, className);
            servletClassName_servletName.put(className, name);
        }

        // url and servlet name
        Elements urlElements = document.select("servlet-mapping url-pattern");
        for (Element urlElement : urlElements){
            String urlPattern = urlElement.text();
            String servletName =
                    urlElement.parent().select("servlet-name").first().text();
            url_servletName.put(urlPattern, servletName);
        }

        // url and servlet class name
        Set<String> urls = url_servletName.keySet();
        for (String url : urls) {
            String servletName = url_servletName.get(url);
            String servletClassName
                    = servletName_servletClassName.get(servletName);
            url_servletClassName.put(url, servletClassName);
        }
    }

    private void checkDuplicate(Document document, String pattern, String warning) throws WebConfigDuplicateException {
        Elements elements = document.select(pattern);

        // first put all the elements into a sorted array
        List<String> contents = new ArrayList<>();
        for (Element element : elements){ contents.add(element.text()); }
        Collections.sort(contents);
        // then check if adjacent elements are duplicated
        for (int i = 0; i < contents.size() - 1; i++) {
            if (contents.get(i).equals(contents.get(i + 1))){
                throw new WebConfigDuplicateException(warning);
            }
        }
    }

    private void checkDuplicate() throws WebConfigDuplicateException {
        try {
            Document document = Jsoup.parse(Constant.webXMLFile, "utf-8");
            checkDuplicate(document, "servlet servlet-name",
                    "Duplicate servlet-name");
            checkDuplicate(document, "servlet-mapping url-pattern",
                    "Duplicate url");
            checkDuplicate(document, "servlet servlet-class",
                    "Duplicate servlet-class");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WebappClassLoader getWebappClassLoader() { return webappClassLoader; }

    public String getServletClassName(String url){
        System.out.println("DOCBASE in CONTEXT: " + docBase);
        System.out.println("PATH in COntext: " + path);
        String uri = StrUtil.subAfter(url, path);
        if (!uri.startsWith("/")) { uri = "/" + uri; }
        System.out.println(uri);
        return url_servletClassName.get(uri);
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
