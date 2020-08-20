package uk.ac.ucl.catalina.conf;

import org.apache.logging.log4j.LogManager;
import uk.ac.ucl.catalina.request.Request;
import uk.ac.ucl.catalina.response.Response;
import uk.ac.ucl.processor.HttpProcessor;
import uk.ac.ucl.util.core.ThreadUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector implements Runnable {
    private int port;
    private Service service;
    private String compression;
    private int compressionMinSize;
    private String compressionMimeType;
    private String noCompressionUserAgent;

    public Connector(Service service) {
        this.service = service;
    }

    public void setPort(int port) { this.port = port; }

    public Service getService() { return service; }

    /**
     * In order to create tomcat-style log
     */
    public void init() {
        LogManager.getLogger().info("Initializing ProtocolHandler [http-bio-{}]", port);
    }

    public void start() {
        LogManager.getLogger().info("Starting ProtocolHandler [http-bio-{}]", port);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
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
                            Request request = new Request(socket, Connector.this);
                            Response response = new Response();
                            HttpProcessor processor = new HttpProcessor();
                            processor.execute(socket, request, response);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        finally {
                            if (!socket.isClosed()) {
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
        } catch(IOException e){
            LogManager.getLogger().error(e);
            e.printStackTrace();
        }
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        System.out.println();
        this.compression = compression;
    }

    public int getCompressionMinSize() {
        return compressionMinSize;
    }

    public void setCompressionMinSize(int compressionMinSize) {
        this.compressionMinSize = compressionMinSize;
    }

    public String getCompressionMimeType() {
        return compressionMimeType;
    }

    public void setCompressionMimeType(String compressionMimeType) {
        this.compressionMimeType = compressionMimeType;
    }

    public String getNoCompressionUserAgent() {
        return noCompressionUserAgent;
    }

    public void setNoCompressionUserAgent(String noCompressionUserAgent) {
        this.noCompressionUserAgent = noCompressionUserAgent;
    }
}
