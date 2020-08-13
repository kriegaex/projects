import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.ac.ucl.util.MiniBrowser;
import uk.ac.ucl.util.core.StrUtil;
import uk.ac.ucl.util.core.WebUtil;

import java.util.HashMap;
import java.util.Map;

public class WebApplicationTest {
    private static int port = 18081;
    private static String ip = "127.0.0.1";
    @BeforeClass
    public static void beforeClass() {
        //所有测试开始前看diy tomcat 是否已经启动了
        if(!WebUtil.isPortUsable(port)) {
            System.err.println("Please start Tomcat at port " + port);
            System.exit(1);
        }
        else {
            System.out.println("Tomcat has been established, unit testing starts");
        }
    }

    private String getContentString(String uri) {
        String url = StrUtil.format("http://{}:{}{}", ip,port,uri);
        String content = MiniBrowser.getContentString(url);
        return content;
    }


    @Test
    public void testServlet() {
        String html = getContentString("/example/hello");
        Assert.assertTrue(html.contains("Hello DIY Tomcat from"));
    }

    @Test
    public void testServletSingelton() {
        String html1 = getContentString("/example/hello");
        String html2 = getContentString("/example/hello");
        Assert.assertEquals(html1, html2);
    }

    @Test
    public void testPostParam() {
        String uri = "/example/param";
        String url = StrUtil.format("http://{}:{}{}", ip, port, uri);
        Map<String, String> map = new HashMap<>();
        map.put("name", "chaozy");
        String html = MiniBrowser.getContentString(url, map, true);
        Assert.assertEquals("Post name --> chaozy", html);
    }

    @Test
    public void testGetParam() {
        String uri = "/example/param";
        String url = StrUtil.format("http://{}:{}{}", ip, port, uri);
        Map<String, String> map = new HashMap<>();
        map.put("name", "chaozy");
        String html = MiniBrowser.getContentString(url, map, false);
        Assert.assertEquals("Get name --> chaozy", html);
    }
}
