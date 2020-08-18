package uk.ac.ucl;

import uk.ac.ucl.util.io.Zipper;

import java.io.UnsupportedEncodingException;

public class ExprimentBoard {
    private static void test() throws UnsupportedEncodingException {
        String s = "Hello DIY Tomcat from HelloServlet from :";
        byte[] compressed = Zipper.comrpess(s.getBytes("utf-8"));
        byte[] uncompressed = Zipper.uncompress(compressed);
        System.out.println(new String(uncompressed));
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        test();
    }
}
