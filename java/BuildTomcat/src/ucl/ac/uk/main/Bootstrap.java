package ucl.ac.uk.main;

import uk.ac.ucl.main.bean.Request;
import uk.ac.ucl.main.bean.Response;
import uk.ac.ucl.main.util.Constant;
import uk.ac.ucl.main.util.core.StrTool;
import uk.ac.ucl.main.util.core.WebTool;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Bootstrap {
    public static void main(String[] args) {
        int port = 18080;

        if (WebTool.isPortUsable(port)){
            System.out.println("This port has been occupied, " +
                    "please find out the corresponding process and kill it");
        }
        try {
            System.out.println("Successfully established a server");
            ServerSocket ss = new ServerSocket(port);
            while (true){
                // Listens for a connection to be made to this socket and accepts it.
                Socket socket = ss.accept();

                // Read message from browser
                Request request = new Request(socket);
                System.out.println("Header is ---> " + request.getRequestString());
                String uri = request.getUri();
                // If the port is occupied, the returning uri could be null
                if (uri == null) { continue; }
                Response response = new Response();
                // If the folder directory is the root directory
                if (uri.equals("/")) {
                    String html = "Hello Tomcat from Chaozy";
                    response.getPrintWriter().write(html);
                }
                else{
                    String fileName = uri.replace("/", "");
                    File file = new File(Constant.rootFolder, fileName);
                    if (file.exists()){

                    }
                }
                handle200(socket, response);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handle200(Socket socket, Response response) throws IOException {
        String contentType = response.getContentType();
        String headText = Constant.response_head_200;
        headText = StrTool.format(headText, contentType);

        byte[] head = headText.getBytes();
        byte[] body = response.getBody();

        byte[] responseByte = new byte[head.length + body.length];
        System.arraycopy(head, 0, responseByte, 0, head.length);
        System.arraycopy(body, 0, responseByte, head.length, body.length);
        OutputStream os = socket.getOutputStream();
        os.write(responseByte);
        os.close();
        socket.close();
    }

}
