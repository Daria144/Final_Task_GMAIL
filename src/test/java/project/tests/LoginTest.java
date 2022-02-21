package test.java.project.tests;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.Assert;
import org.testng.annotations.*;
import project.configuration.ConfigProperties;
import project.configuration.TestDataProperties;
import test.java.project.context.LoginContext;
import test.java.project.pages.LoginPage;
import test.java.project.utils.ListenerTestsClass;
import java.util.ArrayList;
import static test.java.project.context.LoginContext.loginPage;
@Listeners(ListenerTestsClass.class)
public class LoginTest extends BaseTest{
    private static Logger LOG = Logger.getLogger(LoginTest.class);
    @Test(enabled = false)
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
    @Test(priority = 1)
    public void logInWithValidCredPressingEnter(){
        LoginPage.clickOnChooseAnotherAccount();
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
        Assert.assertTrue(LoginContext.userLabelIsDisplayed(),"User is not logged in");
        LOG.assertLog(LoginContext.userLabelIsDisplayed(),"User should be logged in");
    }
    @Test
    public void logInWithValidCredClickingOnNextButtons(){
        LoginContext.logInClickingOnNextButtons(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
        Assert.assertTrue(LoginContext.userLabelIsDisplayed());
        LOG.assertLog(LoginContext.userLabelIsDisplayed(),"User IS NOT logged in");
    }
    @Test
    public void logInWithInvalidPwd() {
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("incorrectPassword"));
        Assert.assertTrue(LoginContext.assertLabelIsDisplayed());
        LOG.assertLog(LoginContext.assertLabelIsDisplayed(),"Assert label is nor dispalyed");
    }
    @AfterMethod
    public void openNewTab(){
        try{
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.get(ConfigProperties.getProperty("loginPage"));
        LOG.info("Open new Tab");
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
