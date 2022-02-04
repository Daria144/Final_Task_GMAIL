package project.configuration;

import java.io.FileInputStream;
import java.io.IOException;

public class TestDataProperties {
    protected static FileInputStream fileInputStream;
    protected static java.util.Properties TESTDATA;
    static {
        try {
            //указание пути до файла с настройками
            fileInputStream = new FileInputStream("src/test/java/resources/testData.properties");
            TESTDATA = new java.util.Properties();
            TESTDATA.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            //обработка возможного исключения (нет файла и т.п.)
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace(); } } }
    public static String getTestData(String key) {
        return TESTDATA.getProperty(key); }
}
