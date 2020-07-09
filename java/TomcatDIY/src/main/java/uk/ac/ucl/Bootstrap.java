package uk.ac.ucl;


import uk.ac.ucl.bean.*;

import java.util.*;


public class Bootstrap {
    public static void main(String[] args) {
        // Configured by server.xml
        Server server = new Server();
        server.start();
    }


}
