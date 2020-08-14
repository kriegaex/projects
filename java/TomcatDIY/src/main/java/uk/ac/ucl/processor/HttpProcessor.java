package uk.ac.ucl.processor;

import org.apache.logging.log4j.LogManager;
import uk.ac.ucl.bean.request.Request;
import uk.ac.ucl.bean.response.Response;
import uk.ac.ucl.context.Context;
import uk.ac.ucl.module.DefaultServlet;
import uk.ac.ucl.module.InvokerServlet;
import uk.ac.ucl.util.Constant;
import uk.ac.ucl.util.core.StrUtil;


import java.io.IOException;
import java.io.OutputStream;

import java.net.Socket;

public class HttpProcessor {
    public void execute(Socket socket, Request request, Response response) {
        try {
            String uri = request.getUri();
            // If the port is occupied, the returning uri could be null
            if (uri == null) { return; }
            Context context = request.getContext();
            String servletClassName = context.getServletClassName(uri);
            if (servletClassName != null) {
                InvokerServlet.getInstance().service(request, response);
            }
            else {
                DefaultServlet.getInstance().service(request, response);
            }
            int status = response.getStatus();
            if (status == Constant.code_200) {
                handle200(socket, response);
            }
            else if (status == Constant.code_404) {
                handle404(socket, uri);
                return;
            }
        } catch (Exception e) {
            LogManager.getLogger().error(e);
            e.printStackTrace();
            handle500(socket, e);
        }
    }

    /**
     * When the status code is 200, display the content of the web page
     * @param socket
     * @param response
     * @throws IOException
     */
    private static void handle200(Socket socket, Response response) throws IOException {
        String contentType = response.getContentType();
        String headText = Constant.response_head_200;
        String cookieHeader = response.getCookieHeader();
        headText = StrUtil.format(headText, contentType, cookieHeader);

        byte[] head = headText.getBytes();
        byte[] body = response.getBody();

        byte[] responseByte = new byte[head.length + body.length];
        System.arraycopy(head, 0, responseByte, 0, head.length);
        System.arraycopy(body, 0, responseByte, head.length, body.length);
        OutputStream os = socket.getOutputStream();
        os.write(responseByte);
        os.close();
    }

    private void handle404(Socket s, String uri){
        try {
            OutputStream outputStream = s.getOutputStream();
            String responseText = Constant.response_head_404 +
                    StrUtil.format(Constant.textFormat_404, uri, uri);
            outputStream.write(responseText.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle500(Socket s, Exception e){
        StackTraceElement traceElement[] = e.getStackTrace();
        StringBuffer buffer = new StringBuffer();
        buffer.append(e.toString());
        buffer.append("\r\n");
        for (StackTraceElement element : traceElement){
            buffer.append("\t");
            buffer.append(element.toString());
            buffer.append("\r\n");
        }
        // If there is over 20 messages, only displays the previous 20 lines
        String msg = e.getMessage();
        if (msg != null && msg.length() > 20){
            msg.substring(0, 19);
        }
        String text = StrUtil.format(Constant.textFormat_500,
                e.toString(), buffer.toString());
        text = Constant.response_head_500 + text;
        try {
            OutputStream outputStream = s.getOutputStream();
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
