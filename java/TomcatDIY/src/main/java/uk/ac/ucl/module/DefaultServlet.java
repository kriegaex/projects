package uk.ac.ucl.module;

import org.apache.logging.log4j.core.util.FileUtils;
import uk.ac.ucl.bean.Context;
import uk.ac.ucl.bean.request.Request;
import uk.ac.ucl.bean.response.Response;
import uk.ac.ucl.util.Constant;
import uk.ac.ucl.util.core.StrUtil;
import uk.ac.ucl.util.io.WebXMLParsing;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * DefaultServlet aims to handle static web resources
 */
public class DefaultServlet extends HttpServlet {
    private static DefaultServlet defaultServlet = new DefaultServlet();

    public static DefaultServlet getInstance() { return defaultServlet; }

    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Request request = (Request)req;
        Response response = (Response)resp;

        Context context = request.getContext();
        String uri = request.getUri();
        if (uri.equals("/500.html")) {
            throw new RuntimeException("this is a deliberately created exception");
        } else {
            // If the folder directory is the root directory
            if (uri.equals("/")) {
                uri = WebXMLParsing.getWelcomeFileName(request.getContext());
            }
            String fileName = StrUtil.subAfter(uri, "/", true);
            File file = new File(context.getDocBase(), fileName);

            if (file.exists()) {
                String extension = FileUtils.getFileExtension(file);
                String mimeType = WebXMLParsing.getMimeType(extension);
                response.setContentType(mimeType);

                response.setBody(Files.readAllBytes(file.toPath()));

                // To test multithreading
                if (fileName.equals("sleep.html")) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                response.setStatus(Constant.code_200);
            } else {
                response.setStatus(Constant.code_404);
            }
        }
    }
}