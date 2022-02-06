package project.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import project.configuration.ConfigProperties;

import java.time.Duration;

public abstract class BaseTest {
    public static WebDriver driver;

    @BeforeMethod
            (alwaysRun = true)
    public static void launch(){
        System.setProperty(ConfigProperties.getProperty("chromeKey"),ConfigProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigProperties.getProperty("loginPage"));
    }



    @AfterMethod
            (alwaysRun = true)
    public void turnDown(){
        driver.quit();
    }
}
