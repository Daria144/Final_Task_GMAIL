package project.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
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
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        driver = new ChromeDriver(dc);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(ConfigProperties.getProperty("loginPage"));
    }
    /**
     * @param elementsToBeDisplayed
     * @return true if elements are displayed
     */
    public static boolean elementsAreDisplayed(WebElement[] elementsToBeDisplayed){
        boolean allPresent=true;

        for (WebElement el : elementsToBeDisplayed) {
            try {
                el.isDisplayed();
            }
            catch (NoSuchElementException exc){
                allPresent = false;
                System.out.println("WebElement not displayed: " + el.getTagName() + " Text: " + el.getText());
            }
        }
        return allPresent;
    }

    /**
     *
     * @param elementsToBeClickable
     * @return true if elemets are clickable
     */
    public static boolean elementsAreClickable(WebElement[] elementsToBeClickable){
        boolean allPresent=true;
        for (WebElement el : elementsToBeClickable) {
            try {
                BasePage.isClickable(el);
            }
            catch (NoSuchElementException exc){
                allPresent = false;
                System.out.println("WebElement not displayed: " + el.getTagName() + " Text: " + el.getText());
            }
        }
        return allPresent;
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
