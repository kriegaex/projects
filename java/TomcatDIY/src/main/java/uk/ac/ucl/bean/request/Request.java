package uk.ac.ucl.bean.request;

import uk.ac.ucl.bean.Context;
import uk.ac.ucl.bean.conf.Service;
import uk.ac.ucl.util.MiniBrowser;
import uk.ac.ucl.util.core.StrUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Request extends BasicRequest {
    private String requestString;
    private String uri;
    private String method;
    private Socket socket;
    private Context context;
    private Service service;

    public Request(Socket socket, Service service) throws IOException {
        this.socket = socket;
        this.service = service;
        parseHttpRequest();
        if (StrUtil.isEmpty(requestString)) { return; }
        parseUri();
        parseContext();
        parseMethod();

        if (!"/".equals(context.getPath())) {
            uri.substring(context.getPath().length());
            if (uri.equals("")) {
                uri = "/";
            }
        }

    }

    private void parseMethod() {
        method = StrUtil.subBefore(requestString, " ");
    }

    private void parseHttpRequest() throws IOException {
        InputStream inputStream = socket.getInputStream();

        // Browser will send keep-alive connection,
        // So unless browser terminates the connection by itself,
        // the server will not receive the terminate signal (-1)
        byte[] bytes = MiniBrowser.readBytes(inputStream, false);
        requestString = new String(bytes, StandardCharsets.UTF_8);
    }

    private void parseUri() {
        String temp;
        temp = StrUtil.subBetween(requestString, " ");
        if (!temp.contains("?")) {
            StrUtil.subBefore(temp, "?");
        }
        uri = temp;
    }


    private void parseContext() {
        String path;

        path = StrUtil.subBetween(uri, "/");
        path = "/" + path;
        System.out.println("PATH: " + path);
        context = service.getEngine().getDefaultHost().getContext(path);

    }

    @Override
    public String getMethod() {
        return method; }

    public String getRequestString() { return requestString; }

    public String getUri() { return uri; }

    public Context getContext() { return context; }

}

