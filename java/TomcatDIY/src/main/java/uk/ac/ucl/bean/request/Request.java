package uk.ac.ucl.bean.request;

import org.apache.commons.codec.DecoderException;

import org.apache.logging.log4j.core.pattern.LineSeparatorPatternConverter;
import uk.ac.ucl.context.Context;
import uk.ac.ucl.bean.conf.Service;
import uk.ac.ucl.util.MiniBrowser;
import uk.ac.ucl.util.core.ArrayUtil;
import uk.ac.ucl.util.core.StrUtil;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.InetSocketAddress;
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
    private Map<String, String> headerMap;

    public Request(Socket socket, Service service) throws IOException {
        this.socket = socket;
        this.service = service;
        this.paramMap = new HashMap<>();
        this.headerMap = new HashMap<>();

        parseHttpRequest();
        if (StrUtil.isEmpty(requestString)) { return; }
        parseUri();
        parseContext();
        parseMethod();
        parseHeaders(requestString);

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

    @Override
    public String getHeader(String s) {
        if (s == null) {
            return null;
        }
        return headerMap.get(s);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        Set<String> keys = headerMap.keySet();
        return Collections.enumeration(keys);
    }

    @Override
    public int getIntHeader(String s) {
        String header = headerMap.get(s);
        if (header == null){ return -1; }
        try {
            return Integer.parseInt(header);
        }
        catch (NumberFormatException e){
            return 0;
        }
    }

    private void parseHeaders(String requestString) throws IOException {
        StringReader reader = new StringReader(requestString);
        List<String> lines = new ArrayList<>();

        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        while (line != null) {
            lines.add(line);
            line = br.readLine();
        }
        br.close();
        reader.close();
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            if (line.length() == 0) { break; }
            String[] name_header = line.split(":");
            headerMap.put(name_header[0], name_header[1]);
        }

    }
    private void parseParameters() throws DecoderException {
        if (this.getMethod().equals("GET")) {
            String url = StrUtil.subBetween(requestString, " ");

            if (url.contains("?")){
                queryString = StrUtil.subAfter(url, "?");
            }

        }
        else if (this.getMethod().equals("POST")) {
            queryString = StrUtil.subAfter(requestString, "\r\n\r\n");
        }
        if (queryString == null || queryString.length() == 0) {
            return ;
        }
        // queryString = new String(Hex.decodeHex(queryString));
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
        if (temp.contains("?")) {
            temp = StrUtil.subBefore(temp, "?");
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

    // Code below from : https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServletRequest.html
    @Override
    public String getLocalAddr() {
        return socket.getLocalAddress().getHostAddress();
    }

    @Override
    public String getLocalName() {
        return socket.getLocalAddress().getHostName();
    }

    @Override
    public int getLocalPort() {
        return socket.getLocalPort();
    }

    @Override
    public String getProtocol() {
        return "HTTP:/1.1";
    }

    @Override
    public String getRemoteAddr() {
        InetSocketAddress address = (InetSocketAddress) socket.getRemoteSocketAddress();
        return StrUtil.subAfter(address.getAddress().toString(), "/");
    }

    @Override
    public String getRemoteHost() {
        InetSocketAddress address = (InetSocketAddress) socket.getRemoteSocketAddress();
        return address.getHostName();
    }

    @Override
    public int getRemotePort() {
        return socket.getPort();
    }

    @Override
    public String getScheme() {
        return "http";
    }

    @Override
    public String getServerName() {
        return getHeader("Host").trim();
    }

    @Override
    public int getServerPort() {
        return getLocalPort();
    }

    @Override
    public String getContextPath() {
        String path = this.context.getPath();
        if (path.equals("/")){
            return "";
        }
        return path;
    }

    @Override
    public String getRequestURI() {
        return uri;
    }

    @Override
    public StringBuffer getRequestURL() {
        StringBuffer url = new StringBuffer();
        String scheme = this.getScheme();
        int port = this.getServerPort();

        url.append(scheme);
        url.append(port);
        url.append(this.getServerName());
        if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
            url.append(':');
            url.append(port);
        }
        url.append(getRequestURI());
        return url;
    }

    @Override
    public String getServletPath() {
        return uri;
    }
}

