package test.java.project.context;
import org.apache.log4j.Logger;
import test.java.project.pages.LoginPage;

import static test.java.project.tests.BaseTest.driver;

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

    public static boolean userLabelIsDisplayed(){
        return loginPage.waitForUserLabel().isDisplayed();
    }
    public static boolean assertLabelIsDisplayed(){
        return loginPage.waitForAsserLabel().isDisplayed();
    }



}
