package project.utils;


import java.nio.charset.Charset;
import java.util.Random;

public class StringUtils {
    public static String RandomString (Integer length){
        byte[] array = new byte[length]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
