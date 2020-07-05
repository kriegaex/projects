
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.ac.ucl.util.MiniBrowser;
import uk.ac.ucl.util.core.StrUtil;
import uk.ac.ucl.util.core.TimeUtil;
import uk.ac.ucl.util.core.WebUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestTomcat {
    private static int port = 18080;
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

    @Test
    public void testHelloTomcat() {
        String html = getContentString("/");
        Assert.assertEquals(html,"Hello Tomcat from Chaozy");
    }

    @Test
    public void testaHtml() {
        String html = getContentString("/hello.html");

        Assert.assertEquals(html,"This is my first HTML file in DIY tomcat.");
    }

    private String getContentString(String uri) {
        String url = StrUtil.format("http://{}:{}{}", ip,port,uri);
        String content = MiniBrowser.getContentString(url);
        return content;
    }

    @Test
    public void testTimeConsumeHtml() throws InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(20, 20, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10));
        TimeUtil clock = new TimeUtil();

        for(int i = 0; i<3; i++){
            threadPool.execute(new Runnable(){
                public void run() {
                    getContentString("/sleep.html");
                }
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);

        long duration = clock.interval();
        System.out.println(duration + "ms");
        Assert.assertTrue(duration < 3000);
    }

}
