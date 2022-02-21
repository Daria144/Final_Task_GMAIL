package project.tests;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import project.configuration.ConfigProperties;
import project.pages.BasePage;

import java.time.Duration;
public class BaseTest {
    public static WebDriver driver;
    public static Logger log= Logger.getLogger(BaseTest.class);
    @BeforeSuite
            (alwaysRun = true)
    public static void launch(){
        System.setProperty(ConfigProperties.getProperty("chromeKey"),ConfigProperties.getProperty("chromedriver"));
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        driver = new ChromeDriver(dc);
        driver.manage().window().maximize();
        log.info("Maximize window size");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.get(ConfigProperties.getProperty("loginPage"));
        log.info("Login to Gmail");
        waitForDeRedirected();
    }
    public static void waitForDeRedirected(){
        WebElement signInButton=driver.findElement(By.xpath("//a[@data-action=\"sign in\"]"));
        signInButton.click();
        WebElement dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Google\"]")));
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
    @AfterSuite
            (alwaysRun = true)
    public void turnDown(){
        driver.quit();
    }
}
