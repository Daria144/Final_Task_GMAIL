package project.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import project.configuration.ConfigProperties;
import project.configuration.TestDataProperties;
import project.context.InboxContext;
import project.context.LoginContext;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static project.context.InboxContext.inboxPage;

public class InboxTest extends LoginTest{


    @BeforeTest
    public void userIsLoggedIn(){
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
        Assert.assertTrue(LoginContext.userLabelIsDisplayed());
    }
    @Test
    public void sendEmailInFullScreen(){
        InboxContext.sendEmailInFullScreen();
        Assert.assertTrue(inboxPage.popupOfEmailSentIsDisplayed());
    }
    @Test
    public void sendEmailWithNoRecipient(){
        InboxContext.sendEmailWithNoRecipient();
        //Assert.assertFalse(inboxPage.popupOfEmailSentIsDisplayed());
        Assert.assertTrue(inboxPage.alertMessageIsDisplayed());
    }
/*    @Test
    public void sendEmailWithNoSubjectAndBody(){
        InboxContext.sendEmailWithNoSubjectAndBody();
        Assert.assertTrue(inboxPage.popupOfEmailSentIsDisplayed());
    }
    @Test
    public void addEmailToDraftClickingOnCrossIconAndOpenIt(){
        int[] draftsCount = InboxContext.addEmailToDraftAndReturnCount();
        boolean countIsIncreased= draftsCount[1] > draftsCount[0];
        Assert.assertTrue(countIsIncreased);
        inboxPage.openFirstEmailInList();
        Assert.assertTrue(InboxContext.draftContainsAllEnteredData());
    }
    @Test
    public void sendEmailAndClickOnUndoButton(){
        String[] filledData=InboxContext.addEmailToDraftAndClickOnUndoButton();
        Assert.assertTrue(InboxContext.emailContainsAllEnteredData(filledData));
    }
    @Test
    public void markedMessageIsAddedToMarkSection(){
        int[] emailCount=InboxContext.markedMessageIsAddedToMarkSection();
        boolean countIsIncreased= emailCount[1] > emailCount[0];
        Assert.assertTrue(countIsIncreased);
        int newEmailCount=InboxContext.unmarkedMessageIsRemovedFromMarkSection();
        boolean countIsDecreased=newEmailCount<emailCount[1];
        Assert.assertTrue(countIsDecreased);

    }

    @Test
    public void sendEmailInScheduledTime(){
        InboxContext.openScheduleEmailPopup();
        Assert.assertTrue(InboxContext.schedulePopupIsDisplayed());
        int [] scheduleCount=InboxContext.selectScheduleDateAndReturnCount();
        boolean countIsIncreased= scheduleCount[1] > scheduleCount[0];
        Assert.assertTrue(countIsIncreased);

    }
    */
    @AfterMethod
    public void openNewTab(){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.get(ConfigProperties.getProperty("loginPage"));
    }



}
