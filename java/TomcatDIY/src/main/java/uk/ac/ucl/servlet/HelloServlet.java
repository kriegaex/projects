package uk.ac.ucl.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        doGet((HttpServletRequest) req, (HttpServletResponse) res);
//    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try{
            response.getWriter().println("Hello world, " +
                    "this is the first servlet on this Tomcat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
