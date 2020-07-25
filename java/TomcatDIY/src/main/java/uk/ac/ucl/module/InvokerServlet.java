package uk.ac.ucl.module;

import uk.ac.ucl.bean.Context;
import uk.ac.ucl.bean.request.Request;
import uk.ac.ucl.bean.response.Response;
import uk.ac.ucl.util.Constant;
import uk.ac.ucl.util.core.ReflectUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * InvokerServlet provides service to servlet mapping
 */
public class InvokerServlet extends HttpServlet {
    private static InvokerServlet instance = new InvokerServlet();

    public static InvokerServlet getInstance() {return instance; }

    public void service(HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse) {
        Request request = (Request) httpServletRequest;
        Response response = (Response) httpServletResponse;
        String uri = request.getUri();
        Context context = request.getContext();
        String servletClassName = context.getServletClassName(uri);
        System.out.println("servletClassName: " + servletClassName);
        try {
            Class servletClass = context.getWebappClassLoader().loadClass(servletClassName);
            // No need to check if servletObject is null, this is checked in ReflectUtil
            Object servletObject = ReflectUtil.getInstance(servletClassName);
            System.out.println("servletObject: " + servletObject);
            System.out.println("servletClass: " + servletClass);
            System.out.println("servletClass's classLoader:" + servletClass.getClassLoader());

            // The types of arguments of service() is ServletRequest and ServletResponse
            // They have to be casted to these two types to match corresponding invoke method
            ReflectUtil.invoke(servletObject,
                    "service", (ServletRequest) request, (ServletResponse) response);

            response.setStatus(Constant.code_200);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
