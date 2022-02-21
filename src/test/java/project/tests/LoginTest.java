package project.tests;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.Assert;
import org.testng.annotations.*;
import project.configuration.ConfigProperties;
import project.configuration.TestDataProperties;
import project.context.LoginContext;
import project.pages.LoginPage;
import project.utils.ListenerTestsClass;
import java.util.ArrayList;
import static project.context.LoginContext.loginPage;
@Listeners(ListenerTestsClass.class)
public class LoginTest extends BaseTest{
    private static Logger LOG = Logger.getLogger(LoginTest.class);
    @Test(groups = "LoginTest")
    public void elementsAreDisplayedAndClickable(){
        Assert.assertTrue(elementsAreDisplayed(loginPage.getElementsOnFirstPageToBeDisplayed()));
        LOG.info("Elements from First Page are displayed");
        Assert.assertTrue(elementsAreClickable(loginPage.getElementsOnFirstPageToBeClickable()));
        LOG.info("Elements from First Page are clickable");
        loginPage.inputLoginAndPressEnter(TestDataProperties.getTestData("login"));
        Assert.assertTrue(elementsAreDisplayed(loginPage.getElementsOnLastPageToBeDisplayed()));
        LOG.info("Elements from Second Page are displayed");
        Assert.assertTrue(elementsAreClickable(loginPage.getElementsOnLastPageToBeClickable()));
        LOG.info("Elements from Second Page are clickable");
    }
    @Test(priority = 1,groups = "LoginTest")
    public void logInWithValidCredPressingEnter(){
        LoginPage.clickOnChooseAnotherAccount();
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
        Assert.assertTrue(LoginContext.userLabelIsDisplayed(),"User is not logged in");
        LOG.assertLog(LoginContext.userLabelIsDisplayed(),"User should be logged in");
    }
    @Test(groups = "LoginTest")
    public void logInWithValidCredClickingOnNextButtons(){
        LoginContext.logInClickingOnNextButtons(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
        Assert.assertTrue(LoginContext.userLabelIsDisplayed());
        LOG.assertLog(LoginContext.userLabelIsDisplayed(),"User IS NOT logged in");
    }
    @Test(groups = "LoginTest")
    public void logInWithInvalidPwd() {
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("incorrectPassword"));
        Assert.assertTrue(LoginContext.assertLabelIsDisplayed());
        LOG.assertLog(LoginContext.assertLabelIsDisplayed(),"Assert label is not displayed");
    }
    @AfterMethod(groups = "LoginTest")
    public void openNewTab(){
        try{
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.get(ConfigProperties.getProperty("loginPage"));
        waitForDeRedirected();
        } catch (UnhandledAlertException f) {
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (NoAlertPresentException e) {
                e.printStackTrace();
            }
        }

    }
}
