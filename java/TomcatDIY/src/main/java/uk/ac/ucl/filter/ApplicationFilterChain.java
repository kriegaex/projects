package uk.ac.ucl.filter;

import uk.ac.ucl.util.core.ArrayUtil;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

public class ApplicationFilterChain implements FilterChain {
    private Filter[] filters;
    private Servlet servlet;
    int pos;

    public ApplicationFilterChain(List<Filter> filters, Servlet servlet) {
        this.filters = new Filter[filters.size()];
        for (int i = 0; i < filters.size(); i++) {
            this.filters[i] = filters.get(i);
        }

        this.servlet = servlet;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
        if (pos < this.filters.length) {
            Filter filter = this.filters[pos++];
            filter.doFilter(servletRequest, servletResponse, this);

        }
        else{
            servlet.service(servletRequest, servletResponse);
        }
    }
}
