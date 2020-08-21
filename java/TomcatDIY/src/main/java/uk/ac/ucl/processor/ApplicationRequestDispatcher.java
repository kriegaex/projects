package uk.ac.ucl.processor;

import uk.ac.ucl.catalina.request.Request;
import uk.ac.ucl.catalina.response.Response;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * This class is responsible for the server side redirection
 */
public class ApplicationRequestDispatcher implements RequestDispatcher {
    private String uri;

    public ApplicationRequestDispatcher(String uri) {
        if (!uri.startsWith("/")) {
            uri = "/" + uri;
            this.uri = uri;
        }
    }

    @Override
    public void forward(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        Request request = (Request) servletRequest;
        Response response = (Response) servletResponse;

        //  Change the request's target uri, the run the excute again to redirect to the
        // target uri inside the server
        request.setUri(uri);
        HttpProcessor processor = new HttpProcessor();
        processor.execute(request.getSocket(), request, response);
        request.setForwarded(true);
    }

    @Override
    public void include(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}
