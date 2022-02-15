package project.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project.configuration.TestDataProperties;
import project.context.InboxContext;
import project.context.LoginContext;
import project.pages.InboxPage.InboxPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static project.context.InboxContext.inboxPage;

public class InboxTest extends LoginTest{


    @BeforeMethod
    public void userIsLoggedIn(){
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
        Assert.assertTrue(LoginContext.userLabelIsDisplayed());
    }
    @Test
    public void sendEmailInFullScreen(){
        inboxPage.clickOnComposeButton();
        Assert.assertTrue(InboxContext.emailPopupIsDisplayed());
        InboxContext.sendEmailInFullScreen();
        Assert.assertTrue(inboxPage.popupOfEmailSentIsDisplayed());
    }
    @Test
    public void sendEmailWithNoRecipient(){
        InboxContext.sendEmailWithNoRecipient();
        Assert.assertFalse(inboxPage.popupOfEmailSentIsDisplayed());
        Assert.assertTrue(inboxPage.alertMessageIsDisplayed());
    }
    @Test
    public void sendEmailWithNoSubjectAndBody(){
        InboxContext.sendEmailWithNoSubjectAndBody();
        Assert.assertTrue(inboxPage.popupOfEmailSentIsDisplayed());
    }
    @Test
    public void addEmailToDraftClickingOnCrossIconAndOpenIt(){
        Assert.assertTrue(InboxContext.addEmailToDraftAndCountIsIncreased());
        Assert.assertTrue(InboxContext.draftIsOnTopOfPageAndContainsAllEnteredData());
    }



}
