package project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import project.configuration.ConfigProperties;
import project.pages.BasePage;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;

public class BaseTest {
    public static WebDriver driver;

    @BeforeTest
            (alwaysRun = true)
    public static void launch(){
        System.setProperty(ConfigProperties.getProperty("chromeKey"),ConfigProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(ConfigProperties.getProperty("loginPage"));
    }


//    public static void allElementsAreDisplayed(WebElement[] elements){
//        for (WebElement element : elements) {
//            Assert.assertTrue(BasePage.isDisplayed(element));
//        }
//    }
//    public static void allElementsAreClickable(WebElement[] elements){
//        for (WebElement element : elements) {
//            Assert.assertTrue(BasePage.isClickable(element));
//        }
//    }



    @AfterTest
            (alwaysRun = true)
    public void turnDown(){
        driver.quit();
    }
}
