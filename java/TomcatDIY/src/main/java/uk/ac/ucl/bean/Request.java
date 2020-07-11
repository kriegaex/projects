package uk.ac.ucl.bean;

import uk.ac.ucl.bean.conf.Context;
import uk.ac.ucl.bean.conf.Service;
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
//        System.out.println("uri: " + uri);
//        System.out.println("context.getpath: " + context.getPath());
        if (!"/".equals(context.getPath())){

            uri.substring(context.getPath().length());
            if (uri.equals("")){ uri = "/"; }
        }

    }

    private void parseHttpRequest() throws IOException {
        InputStream inputStream = socket.getInputStream();
        // Browser will send keep-alive connection,
        // So unless browser terminates the connection by itself,
        // the server will not receive the terminate signal (-1)
        byte[] bytes = MiniBrowser.readBytes(inputStream, false);
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
            return;
        }
    }

}
