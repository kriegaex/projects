package uk.ac.ucl.bean.request;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import uk.ac.ucl.context.Context;
import uk.ac.ucl.bean.conf.Service;
import uk.ac.ucl.util.MiniBrowser;
import uk.ac.ucl.util.core.ArrayUtil;
import uk.ac.ucl.util.core.StrUtil;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Request extends BasicRequest {
    private String requestString;
    private String uri;
    private String method;
    private Socket socket;
    private Context context;
    private Service service;

    private String queryString;
    private Map<String, String[]> paramMap;

    public Request(Socket socket, Service service) throws IOException {
        this.socket = socket;
        this.service = service;
        this.paramMap = new HashMap<>();
        parseHttpRequest();
        if (StrUtil.isEmpty(requestString)) { return; }
        parseUri();
        parseContext();
        parseMethod();

        if (!"/".equals(context.getPath())) {
            uri.substring(context.getPath().length());
            if (uri.equals("")) {
                uri = "/";
            }
        }
        try {
            parseParameters();
        } catch (DecoderException e) {
            e.printStackTrace();
        }
    }

    /**
     * You should only use this method when you are sure the parameter has only one value.
     * If the parameter might have more than one value, use getParameterValues.
     * @param s
     * @return
     */
    @Override
    public String getParameter(String s) {
        String[] para = paramMap.get(s);
        if (para != null && para.length != 0) {
            return para[0];
        }
        return null;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Set<String> keys = paramMap.keySet();
        return Collections.enumeration(keys);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return paramMap;
    }

    @Override
    public String[] getParameterValues(String s) {
        return paramMap.get(s);
    }

    private void parseParameters() throws DecoderException {
        if (this.getMethod().equals("GET")) {
            String url = StrUtil.subBetween(requestString, " ");
            if (url.contains("?")){
                queryString = StrUtil.subAfter(url, "?");
            }
        }
        else if (this.getMethod().equals("POST")) {
            queryString = StrUtil.subAfter(requestString, "");
            System.out.println("THIS queryString from method POST --> " + queryString);
        }

        if (queryString == null) {
            return ;
        }
        queryString = new String(Hex.decodeHex(requestString));
        System.out.println("queryString from request --> " + queryString);
        String[] parameterValues = queryString.split("&");
        if (parameterValues != null) {
            for (String paramterValue : parameterValues) {
                String[] nameValues = paramterValue.split("=");
                String name = nameValues[0];
                String value = nameValues[1];
                String[] values = paramMap.get(name);
                if (values == null) {
                    values = new String[]{value};
                }
                else{
                    values = (String[]) ArrayUtil.append(values, value);
                }
                paramMap.put(name, values);
            }
        }
    }

    private void parseMethod() {
        method = StrUtil.subBefore(requestString, " ");
    }

    private void parseHttpRequest() throws IOException {
        InputStream inputStream = socket.getInputStream();

        // Browser will send keep-alive connection,
        // So unless browser terminates the connection by itself,
        // the server will not receive the terminate signal (-1)
        byte[] bytes = MiniBrowser.readBytes(inputStream, false);
        requestString = new String(bytes, StandardCharsets.UTF_8);
    }

    private void parseUri() {
        String temp;
        temp = StrUtil.subBetween(requestString, " ");
        if (!temp.contains("?")) {
            StrUtil.subBefore(temp, "?");
        }
        uri = temp;
    }


    private void parseContext() {
        String path;

        path = StrUtil.subBetween(uri, "/");
        path = "/" + path;
        context = service.getEngine().getDefaultHost().getContext(path);

    }

    @Override
    public String getMethod() {
        return method; }

    public String getRequestString() { return requestString; }

    public String getUri() { return uri; }

    public Context getContext() { return context; }

    public ServletContext getServletContext() { return context.getServletContext(); }

    public String getRealPath(String path) {
        return getServletContext().getRealPath(path);
    }

}

