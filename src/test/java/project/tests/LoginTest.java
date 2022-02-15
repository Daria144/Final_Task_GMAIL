package project.tests;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import project.configuration.TestDataProperties;
import project.context.LoginContext;
import project.pages.LoginPage;

import static project.context.LoginContext.loginPage;

public class LoginTest extends BaseTest{

    @Test
    public void elementsAreDisplayedAndClickable(){
        Assert.assertTrue(LoginContext.elementsAreDisplayed(loginPage.getElementsOnFirstPageToBeDisplayed()));
        Assert.assertTrue(LoginContext.elementsAreClickable(loginPage.getElementsOnFirstPageToBeClickable()));
        loginPage.inputLoginAndPressEnter(TestDataProperties.getTestData("login"));
        Assert.assertTrue(LoginContext.elementsAreDisplayed(loginPage.getElementsOnLastPageToBeDisplayed()));
        Assert.assertTrue(LoginContext.elementsAreClickable(loginPage.getElementsOnLastPageToBeClickable()));
    }

    @Test
    public void logInWithValidCredPressingEnter(){
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
        Assert.assertTrue(LoginContext.userLabelIsDisplayed());
    }
    @Test
    public void logInWithValidCredClickingOnNextButtons(){
        LoginContext.logInClickingOnNextButtons(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
        Assert.assertTrue(LoginContext.userLabelIsDisplayed());

    }
    @Test
    public void logInWithInvalidPwd() {
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("incorrectPassword"));
        Assert.assertTrue(LoginContext.assertLabelIsDisplayed());
    }

}
