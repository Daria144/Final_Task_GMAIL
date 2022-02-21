package project.pages;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class BasePage {
    protected static WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public static boolean isClickable(WebElement element) {
        boolean isElementClickable = true;
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            isElementClickable = false;
        }
        return isElementClickable;
    }



}


