package project.context;

import org.openqa.selenium.WebElement;
import project.configuration.ConfigProperties;
import project.configuration.TestDataProperties;
import project.pages.BasePage;
import project.pages.LoginPage;

import static project.tests.BaseTest.driver;

public class LoginContext {
    public static LoginPage loginPage = new LoginPage(driver);

    /**
     * logging in pressing Enter
     */
    public static void logInPressingEnter(String login, String pwd){
        loginPage.inputLoginAndPressEnter(login);
        loginPage.inputPasswordAndPressEnter(pwd);
    }

    /**
     * logging in clicking on Next button
     */
    public static void logInClickingOnNextButtons(String login, String pwd){
        loginPage.inputLoginAndClickOnNextButton(login);
        loginPage.inputPasswordAndClickOnNextButton(pwd);
    }

    /**
     *
     */
    public static void elementsAreDisplayedAndClickableOnFirstPage(){
        for (WebElement disEl : loginPage.getElementsOnFirstPageToBeDisplayed()) {
            LoginPage.isDisplayed(disEl);
        }
        for (WebElement clickEl : loginPage.getElementsOnFirstPageToBeClickable()) {
            LoginPage.isClickable(clickEl);
        }
    }

    public static void elementsAreDisplayedAndClickableOnLastPage(){
        loginPage.inputLoginAndPressEnter(TestDataProperties.getTestData("login"));
        for (WebElement disEl : loginPage.getElementsOnLastPageToBeClickable()) {
            LoginPage.isDisplayed(disEl);
        }
        for (WebElement clickEl : loginPage.getElementsOnLastPageToBeDisplayed()) {
            LoginPage.isClickable(clickEl);
        }
    }


}
