package project.tests;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import project.configuration.ConfigProperties;
import project.configuration.TestDataProperties;
import project.context.LoginContext;
import project.pages.LoginPage;
import project.utils.TestNgITestListen;

import java.util.ArrayList;

import static project.context.LoginContext.loginPage;
@Listeners(TestNgITestListen.class)
public class LoginTest extends BaseTest{

    @Test(priority = 1)
    public void elementsAreDisplayedAndClickable(){
        Assert.assertTrue(elementsAreDisplayed(loginPage.getElementsOnFirstPageToBeDisplayed()));
        Assert.assertTrue(elementsAreClickable(loginPage.getElementsOnFirstPageToBeClickable()));
        loginPage.inputLoginAndPressEnter(TestDataProperties.getTestData("login"));
        Assert.assertTrue(elementsAreDisplayed(loginPage.getElementsOnLastPageToBeDisplayed()));
        Assert.assertTrue(elementsAreClickable(loginPage.getElementsOnLastPageToBeClickable()));
    }
    @Test(priority = 3)
    public void logInWithValidCredPressingEnter(){
        LoginPage.clickOnChooseAnotherAccount();
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
        Assert.assertTrue(LoginContext.userLabelIsDisplayed());
    }
    @Test(priority = 2)
    public void logInWithValidCredClickingOnNextButtons(){
        LoginContext.logInClickingOnNextButtons(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
        Assert.assertTrue(LoginContext.userLabelIsDisplayed());
    }
    @Test(priority = 1)
    public void logInWithInvalidPwd() {
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("incorrectPassword"));
        Assert.assertTrue(LoginContext.assertLabelIsDisplayed());
    }
    @AfterMethod
    public void openNewTab(){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.get(ConfigProperties.getProperty("loginPage"));
        waitForDeRedirected();
    }
}
