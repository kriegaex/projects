package uk.ac.ucl.main.bean;

import uk.ac.ucl.main.util.MiniBrowser;
import uk.ac.ucl.main.util.core.StrTool;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Request {
    private String requestString;

    public String getRequestString() {
        return requestString;
    }

    public String getUri() {
        return uri;
    }

    private String uri;
    private Socket socket;

    public Request(Socket socket) throws IOException {
        this.socket = socket;
        parseHttpRequest();
        if (StrTool.isEmpty(requestString)){
            return;
        }
        parseUri();
    }

    private void parseHttpRequest() throws IOException {
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = MiniBrowser.readBytes(inputStream);
        requestString = new String(bytes, "utf-8");
    }

    private void parseUri(){
        String temp;
        temp = StrTool.subBetween(requestString, " ");
        if (!temp.contains("?")){
            StrTool.subBefore(temp, "?");
        }
        uri = temp;
    }

}
