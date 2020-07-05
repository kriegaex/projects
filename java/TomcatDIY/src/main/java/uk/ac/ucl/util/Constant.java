package uk.ac.ucl.util;

import java.io.File;

public class Constant {
    public final static String response_head_200 =
            "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: {} \r\n\r\n";

    public final static File rootFolder = new File(System.getProperty("user.dir"), "src/main/webapp");
}
