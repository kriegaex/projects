package uk.ac.ucl.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

public class StandardServletConfig implements ServletConfig {
    private ServletContext servletContext;
    private Map<String, String> initParameters;
    private String servletName;
    public StandardServletConfig(ServletContext servletContext,
                                 Map<String, String> initParameters, String servletName) {
        this.initParameters = initParameters;
        this.servletContext = servletContext;
        this.servletName = servletName;
    }

    @Override
    public String getServletName() {
        return servletName;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public String getInitParameter(String s) {
        return initParameters.get(s);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        Set<String> keys = initParameters.keySet();
        return Collections.enumeration(keys);
    }
}
