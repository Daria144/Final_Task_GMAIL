package project.context;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import project.configuration.TestDataProperties;
import project.pages.BasePage;
import project.pages.LoginPage;
import project.utils.ListenerTestsClass;

import static project.tests.BaseTest.driver;

public class LoginContext {
    public static LoginPage loginPage = new LoginPage(driver);
    public static Logger log= Logger.getLogger(LoginContext.class);
    /**
     * logging in pressing Enter
     */
    public static void logInPressingEnter(String login, String pwd){
        loginPage.inputLoginAndPressEnter(login);
        log.info("Login \""+login+"\" + is entered");
        loginPage.inputPasswordAndPressEnter(pwd);
        log.info("Password \""+pwd+"\" + is entered");
    }

    /**
     * logging in clicking on Next button
     */
    public static void logInClickingOnNextButtons(String login, String pwd){
        loginPage.inputLoginAndClickOnNextButton(login);
        log.info("Login \""+login+"\" + is entered");
        loginPage.inputPasswordAndClickOnNextButton(pwd);
        log.info("Password \""+pwd+"\" + is entered");
    }

    public static boolean userLabelIsDisplayed(){
        return loginPage.waitForUserLabel().isDisplayed();
    }
    public static boolean assertLabelIsDisplayed(){
        return loginPage.waitForAsserLabel().isDisplayed();
    }



}
