package uk.ac.ucl.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.ucl.Bootstrap;
import uk.ac.ucl.util.Constant;
import uk.ac.ucl.util.core.StrUtil;
import uk.ac.ucl.util.core.ThreadUtil;
import uk.ac.ucl.util.io.HTMLParsing;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Server {
    public Service service;

    public Server(){
        this.service = new Service(this);
    }

    public void start(){
        logJVM();
        init();
    }

    private void init() {
        int port = 18080;

        try {
            LogManager.getLogger().info("Successfully established a server");
            ServerSocket ss = new ServerSocket(port);
            // Start waiting for requests
            while (true) {
                // Listens for a connection to be made to this socket and accepts it.
                Socket socket = ss.accept();
                // Receiving requests in multi-threads
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Read message from browser
                            Request request = new Request(socket, service);
                            System.out.println("Header is ---> " + request.getRequestString());
                            String uri = request.getUri();
                            // If the port is occupied, the returning uri could be null
                            if (uri == null) {
                                return;
                            }
                            Context context = request.getContext();
                            Response response = new Response();
                            // If the folder directory is the root directory
                            if (uri.equals("/")) {
                                String html = "Hello Tomcat from Chaozy";
                                response.getPrintWriter().write(html);
                            } else {
                                String fileName = StrUtil.subAfter(uri, "/", true);

                                File file = new File(context.getDocBase(), fileName);
//                                LogManager.getLogger().info("docBase: " + context.getDocBase());
//                                LogManager.getLogger().info("path: " + context.getPath());
//                                LogManager.getLogger().info("fileName: " + fileName);
                                if (fileName.equals("500.html")){
                                    throw new RuntimeException("this is a deliberately created exception");
                                }
                                if (file.exists()) {
                                    String content = HTMLParsing.getBody(file);
                                    response.getPrintWriter().write(content);
                                    // To test multithreading
                                    if (fileName.equals("sleep.html")) {
                                        Thread.sleep(1000);
                                    }
                                }
                                else{
                                    handle404(socket, uri);
                                    return ;
                                }
                            }
                            handle200(socket, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                            handle500(socket, e);
                        }
                        finally {
                            if (!socket.isClosed()){
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                };
                ThreadUtil.run(task);
            }
        }catch (IOException e) {
            LogManager.getLogger().error(e);
            e.printStackTrace();
        }
    }

    /**
     * Display the server information
     */
    private static void logJVM() {
        Map<String,String> infos = new LinkedHashMap<>();
        infos.put("Server version", "Chaozy's DiyTomcat/1.0.1");
        infos.put("Server built", "2020-07-01 10:20:22");
        infos.put("Server number", "1.0.1");
        infos.put("OS Name\t", System.getProperty("os.name"));
        infos.put("OS Version", System.getProperty("os.version"));
        infos.put("Architecture", System.getProperty("os.arch"));
        infos.put("Java Home", System.getProperty("java.home"));
        infos.put("JVM Version", System.getProperty("java.runtime.version"));
        infos.put("JVM Vendor", System.getProperty("java.vm.specification.vendor"));

        Set<String> keys = infos.keySet();
        Logger logger = LogManager.getLogger(Server.class.getName());
        for (String key : keys) {
            logger.info(key+":\t\t" + infos.get(key));
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
        headText = StrUtil.format(headText, contentType);

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

