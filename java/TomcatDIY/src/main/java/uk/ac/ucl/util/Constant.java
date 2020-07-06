package uk.ac.ucl.util;

import java.io.File;

public class Constant {
    public final static String response_head_200 =
            "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: {} \r\n\r\n";

    public final static File rootFolder =
            new File(System.getProperty("user.dir"), "src/main/webapp");

    public final static File confServerXML =
            new File(System.getProperty("user.dir"), "conf/server.xml");

    // TODO: Unix system uses "/" as line separator in directory while Windows uses "\"
    // TODO: https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
    // TODO: Use System.properties to find out which line separator should be used in order to
    // TODO: increse portability
}
