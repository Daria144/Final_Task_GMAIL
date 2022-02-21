package test.java.project.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.Assert;
import org.testng.annotations.*;
import project.configuration.ConfigProperties;
import project.configuration.TestDataProperties;
import test.java.project.context.InboxContext;
import test.java.project.context.LoginContext;
import test.java.project.pages.InboxPage;

import java.time.Duration;
import java.util.ArrayList;
import static test.java.project.context.InboxContext.inboxPage;

public class InboxTest extends BaseTest {
    private static Logger LOG = Logger.getLogger(InboxTest.class);
    @BeforeClass
    public void userLoggedIn(){
        InboxPage.clickOnChooseUserAccount();
        LoginContext.userLabelIsDisplayed();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    }
    @AfterClass(alwaysRun = true)
    public void driverQuit(){
        LOG.info("Driver turned down");
        turnDown();
    }
    @Test(priority = 2)
    public void allEmailOptionsAreAvailable(){
        inboxPage.selectFirstEmail();
        Assert.assertTrue(elementsAreClickable(inboxPage.emailDialogOptions()),"Elements are not clickable");
        LOG.assertLog(elementsAreClickable(inboxPage.emailDialogOptions()),"Email options are not clickable");
    }
    @Test(priority = 2)
    public void allEmailPopupOptionsAreAvailable(){
        inboxPage.clickOnComposeButton();
        Assert.assertTrue(elementsAreClickable(inboxPage.getOptionsToBeClickable()));
        LOG.assertLog(elementsAreClickable(inboxPage.getOptionsToBeClickable()),"Email popup options are not clickable");

    }
    @Test
    public void searchForEmailWithKeyword(){
        inboxPage.setKeywordInSearchField(TestDataProperties.getTestData("keyword"));
        Assert.assertTrue(InboxContext.searchResultsContainsKeyword(),"Keyword is missed");
        LOG.assertLog(InboxContext.searchResultsContainsKeyword(),"Keyword is missed");

    }
    @Test
    public void enterFromUserInSearchOptions(){
        InboxContext.enterFromUserInSearchOptions();
        Assert.assertTrue(InboxContext.emailSendersContainsEnteredEmail(),"Senders are no as in search query");
        LOG.assertLog(InboxContext.emailSendersContainsEnteredEmail(),"Senders are no as in search query");
    }
    @Test
    public void emailIsSentAndThenRead(){
        int readCountStart=InboxContext.getReadCount();
        InboxContext.sentEmailToCurrentUser();
        int readCountIncreased=InboxContext.getReadCount();
        boolean countIsIncreased=readCountStart<readCountIncreased;
        Assert.assertTrue(countIsIncreased,"count is not  increased: "+readCountStart+"!<"+readCountIncreased);
        LOG.assertLog(true,"count is not  increased: "+readCountStart+"!<"+readCountIncreased);
        inboxPage.openFirstEmailInList();
        int readCountDecreased=InboxContext.getReadCount();
        boolean countIsDecreased=readCountIncreased>readCountDecreased;
        Assert.assertTrue(countIsDecreased,"count is not decreased: "+readCountIncreased+"!>"+readCountDecreased);
        LOG.assertLog(true,"count is not decreased: "+readCountIncreased+"!>"+readCountDecreased);
    }
    @Test
    public void searchForEmailWithAttachment(){
        InboxContext.searchForEmailWithAttachment();
        Assert.assertTrue(InboxContext.emailContainsAttachment(),"Emails do not contain attachment");
        LOG.assertLog(InboxContext.emailContainsAttachment(),"Emails do not contain attachment");
    }
    @Test
    public void sendEmailInFullScreen(){
        InboxContext.sendEmailInFullScreen();
        Assert.assertTrue(inboxPage.popupOfEmailSentIsDisplayed(),"Email in full screen is not sent");
        LOG.assertLog(inboxPage.popupOfEmailSentIsDisplayed(),"Email in full screen is not sent");

    }
    @Test
    public void sendEmailWithNoRecipient(){
        InboxContext.sendEmailWithNoRecipient();
        Assert.assertTrue(inboxPage.alertMessageIsDisplayed(),"Email with no recipient should not be sent");
        LOG.assertLog(inboxPage.alertMessageIsDisplayed(),"Email with no recipient should not be sent");
    }
    @Test
    public void sendEmailWithNoSubjectAndBody(){
        InboxContext.sendEmailWithNoSubjectAndBody();
        Assert.assertTrue(inboxPage.popupOfEmailSentIsDisplayed(),"Email with no subject and body is not sent");
        LOG.assertLog(inboxPage.popupOfEmailSentIsDisplayed(),"Email with no subject and body is not sent");
    }
    @Test
    public void addEmailToDraftClickingOnCrossIcon(){
        int draftCountStart=InboxContext.getSnoozedAndDraftsCount()[1];
        InboxContext.addEmailToDraft();
        int draftCount=InboxContext.getSnoozedAndDraftsCount()[1];
        boolean countIsIncreased= draftCount > draftCountStart;
        Assert.assertTrue(countIsIncreased,"Start count: "+draftCountStart+"\nActual count: "+draftCount);
        LOG.assertLog(true,"Start count: "+draftCountStart+"\nActual count: "+draftCount);
    }
    @Test
    public void sendEmailAndClickOnUndoButton(){
        int draftCountStart=InboxContext.getSnoozedAndDraftsCount()[1];
        InboxContext.addEmailToDraftAndClickOnUndoButton();
        int draftCount=InboxContext.getSnoozedAndDraftsCount()[1];
        boolean countIsIncreased=draftCount>draftCountStart;
        Assert.assertTrue(countIsIncreased,"Start count: "+draftCountStart+"\nActual count: "+draftCount);
        LOG.assertLog(true,"Start count: "+draftCountStart+"\nActual count: "+draftCount);
    }
    @Test
    public void markedMessageIsAddedToMarkSection(){
        int[] emailCount=InboxContext.markedMessageIsAddedToMarkSection();
        boolean countIsIncreased= emailCount[1] > emailCount[0];
        Assert.assertTrue(countIsIncreased,"Start count: "+emailCount[0]+"\nActual count: "+emailCount[1]);
        LOG.assertLog(true,"Start count: "+emailCount[0]+"\nActual count: "+emailCount[1]);
        int newEmailCount=InboxContext.unmarkedMessageIsRemovedFromMarkSection();
        boolean countIsDecreased=newEmailCount<emailCount[1];
        Assert.assertTrue(countIsDecreased,"Start count: "+emailCount[1]+"\nActual count: "+newEmailCount);
        LOG.assertLog(true,"Start count: "+emailCount[1]+"\nActual count: "+newEmailCount);
    }
    @Test
    public void sendEmailInScheduledTime(){
        int scheduleCountStart=InboxContext.getSnoozedAndDraftsCount()[0];
        InboxContext.selectScheduleDate();
        int scheduleCount=InboxContext.getSnoozedAndDraftsCount()[0];
        boolean countIsIncreased= scheduleCount > scheduleCountStart;
        Assert.assertTrue(countIsIncreased,"Start count: "+scheduleCountStart+"\nActual count: "+scheduleCount);
        LOG.assertLog(true,"Start count: "+scheduleCountStart+"\nActual count: "+scheduleCount);
        Assert.assertTrue(InboxContext.scheduledDateIsSetCorrectly(),"Schedule date is not set correctly");
        LOG.assertLog(true,"Schedule date is not set correctly");

    }
    @Test
    public void sendEmailInScheduleDateAndCLickOnUndoButton(){
        int[] emailsCountStart=InboxContext.getSnoozedAndDraftsCount();
        InboxContext.sendEmailInScheduleDateAndCLickOnUndoButton();
        int[] emailsCount=InboxContext.getSnoozedAndDraftsCount();
        boolean countNotChanged= emailsCountStart[0]== emailsCount[0];
        Assert.assertTrue(countNotChanged,"Count is changed");
        LOG.assertLog(true,"Count is changed");
        boolean countIsIncreased= emailsCountStart[1]< emailsCount[1];
        Assert.assertTrue(countIsIncreased,"Count is not increased");
        LOG.assertLog(true,"Count is not increased");
    }
    @Test
    public void replySentEmail(){
        InboxContext.enterFromUserInSearchOptions();
        String emailBody=InboxContext.replySentEmail();
        InboxContext.openSentSideOption();
        Assert.assertTrue(InboxContext.emailContainsRepliedEmail());
        LOG.assertLog(true,"emails do not contain replied email");
        boolean containsText=emailBody.contains(inboxPage.getRepliedEmailMessageBody());
        Assert.assertTrue(containsText);
        LOG.assertLog(true,emailBody+" does not contain email text body");
    }
    @AfterMethod
    public void openNewTab(){
        try {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));
            driver.get(ConfigProperties.getProperty("loginPageGmail"));
            LOG.info("Open new tab");
            Assert.assertTrue(LoginContext.userLabelIsDisplayed());
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
