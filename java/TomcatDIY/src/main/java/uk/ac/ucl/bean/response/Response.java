package uk.ac.ucl.bean.response;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Locale;

public class Response extends BasicResponse {
    private StringWriter stringWriter;
    private PrintWriter printWriter;
    private String contentType;
    private byte[] body;
    private int status;

    public Response(){
        // default content-type
        this.contentType = "text/html";
        this.stringWriter = new StringWriter();
        this.printWriter = new PrintWriter(stringWriter);
        // When printWriter.print is used, it writes stuff into stringWriter
        // And outputStream will derive the stuff through getBody() then send it to the client
    }

    public byte[] getBody() throws UnsupportedEncodingException {
        if (body == null) {
            String content = stringWriter.toString();
            body = content.getBytes(StandardCharsets.UTF_8);
        }
        return body;
    }

    public void setBody(byte[] body) { this.body = body; }

    public void setContentType(String contentType){ this.contentType = contentType; }


    public String getContentType(){ return contentType; }

    @Override
    public PrintWriter getWriter() { return printWriter; }

    @Override
    public int getStatus() { return status; }

    @Override
    public void setStatus(int status) { this.status = status; }

}
