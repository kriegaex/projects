package uk.ac.ucl.bean;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

public class Response {
    private StringWriter stringWriter;
    private PrintWriter printWriter;
    private String contentType;
    public Response(){
        this.contentType = "text/html";
        this.stringWriter = new StringWriter();
        this.printWriter = new PrintWriter(stringWriter);
    }

    public String getContentType(){ return contentType; }

    public PrintWriter getPrintWriter() {return printWriter; }

    public byte[] getBody() throws UnsupportedEncodingException {
        String content = stringWriter.toString();
        byte[] body = content.getBytes("utf-8");
        return body;
    }

    public void setContentType(String contentType){ this.contentType = contentType; }
}
