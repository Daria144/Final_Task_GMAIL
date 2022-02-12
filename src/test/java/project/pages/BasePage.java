package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static project.tests.BaseTest.driver;

public class BasePage {
    public static boolean isDisplayed(WebElement element){
        return element.isDisplayed();
    }
    public static boolean isClickable(WebElement element){
        boolean isElementClickable=true;
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            isElementClickable=false;
        }
        return isElementClickable;



    }

    public boolean isClear(WebElement element){
        return element.getText().equals("");
    }
}
