package uk.ac.ucl.util.core;

import java.io.IOException;
import java.net.*;

/**
 * Including methods used for web connection
 */
public class WebTool {
    /**
     * Test if the port is used locally via TCP protocol
     * @param port
     * @return
     */
    static public boolean isPortUsable(int port){
        try(ServerSocket ss = new ServerSocket(port);
        ) {
            // The reason to used this method is here:
            // https://stackoverflow.com/questions/23123395/what-is-the-purpose-of-setreuseaddress-in-serversocket
            ss.setReuseAddress(true);
            return false;
        } catch (IOException e) {
            return true;
        }

    }

    public static void main(String[] args) {
        System.out.println(isPortUsable(8080));
    }
}