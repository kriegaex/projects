package uk.ac.ucl.bean;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

public class Response {
    private StringWriter stringWriter;
    private PrintWriter printWriter;
    private String contentType;
    private byte[] body;

    public Response(){
        // default content-type
        this.contentType = "text/html";
        this.stringWriter = new StringWriter();
        this.printWriter = new PrintWriter(stringWriter);
    }

    public String getContentType(){ return contentType; }

    public PrintWriter getPrintWriter() {return printWriter; }

    public void setBody(byte[] body) { this.body = body; }

    public byte[] getBody() throws UnsupportedEncodingException {
        if (body == null) {
            String content = stringWriter.toString();
            byte[] body = content.getBytes("utf-8");
        }
        return body;
    }

    public void setContentType(String contentType){ this.contentType = contentType; }
}
