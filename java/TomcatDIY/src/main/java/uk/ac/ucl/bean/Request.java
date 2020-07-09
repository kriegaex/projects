package uk.ac.ucl.bean;

import org.apache.logging.log4j.LogManager;
import uk.ac.ucl.util.MiniBrowser;
import uk.ac.ucl.util.core.StrUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Request {
    private String requestString;
    private String uri;
    private Socket socket;
    private Context context;
    private Service service;

    public Request(Socket socket, Service service) throws IOException {
        this.socket = socket;
        this.service = service;
        parseHttpRequest();
        if (StrUtil.isEmpty(requestString)){ return; }
        parseUri();
        parseContext();
        if (!"/".equals(context.getPath())){
            uri.substring(context.getPath().length());
        }
    }

    private void parseHttpRequest() throws IOException {
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = MiniBrowser.readBytes(inputStream);
        requestString = new String(bytes, "utf-8");
    }

    private void parseUri(){
        String temp;
        temp = StrUtil.subBetween(requestString, " ");
        if (!temp.contains("?")){
            StrUtil.subBefore(temp, "?");
        }
        uri = temp;
    }

    public String getRequestString() { return requestString; }

    public String getUri() { return uri; }

    public Context getContext() { return context; }

    private void parseContext() {
        String path;

        path = StrUtil.subBetween(uri, "/");
        path = "/" + path;
        context = service.getEngine().getDefaultHost().getContext(path);

        if (context == null){
            context = service.getEngine().getDefaultHost().getContext(path);
        }
    }

}
