package project.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.Assert;
import org.testng.annotations.*;
import project.configuration.ConfigProperties;
import project.configuration.TestDataProperties;
import project.context.InboxContext;
import project.context.LoginContext;
import project.pages.InboxPage;

import java.time.Duration;
import java.util.ArrayList;
import static project.context.InboxContext.inboxPage;

public class InboxTest extends BaseTest {
    private static Logger LOG = Logger.getLogger(InboxTest.class);
    @BeforeClass(groups = "InboxTest")
    public void userLoggedIn(){
        InboxPage.clickOnChooseUserAccount();
        LoginContext.userLabelIsDisplayed();
    }
    @AfterClass(alwaysRun = true,groups = "InboxTest")
    public void driverQuit(){
        turnDown();
        LOG.info("Driver turned down");
    }
    @Test(priority = 2,groups = "InboxTest")
    public void allEmailOptionsAreAvailable(){
        inboxPage.selectFirstEmail();
        LOG.assertLog(elementsAreDisplayed(inboxPage.emailDialogOptions()),"Email options are not displayed");
    }
    @Test(priority = 2,groups = "InboxTest")
    public void allEmailPopupOptionsAreAvailable(){
        inboxPage.clickOnComposeButton();
        LOG.assertLog(elementsAreDisplayed(inboxPage.getOptionsToBeClickable()),"Email popup options are not clickable");

    }
    @Test(groups = "InboxTest")
    public void searchForEmailWithKeyword(){
        inboxPage.setKeywordInSearchField(TestDataProperties.getTestData("keyword"));
        LOG.assertLog(InboxContext.searchResultsContainsKeyword(),"Keyword is missed");

    }
    @Test(groups = "InboxTest")
    public void enterFromUserInSearchOptions(){
        InboxContext.enterFromUserInSearchOptions();
        LOG.assertLog(InboxContext.emailSendersContainsEnteredEmail(),"Senders are no as in search query");
    }
    @Test(groups = "InboxTest")
    public void emailIsSentAndThenRead(){
        int readCountStart=InboxContext.getReadCount();
        InboxContext.sentEmailToCurrentUser();
        int readCountIncreased=InboxContext.getReadCount();
        boolean countIsIncreased=readCountStart<readCountIncreased;
        LOG.assertLog(true,"count is not  increased: "+readCountStart+"!<"+readCountIncreased);
        inboxPage.openFirstEmailInList();
        int readCountDecreased=InboxContext.getReadCount();
        boolean countIsDecreased=readCountIncreased>readCountDecreased;
        LOG.assertLog(true,"count is not decreased: "+readCountIncreased+"!>"+readCountDecreased);
    }
    @Test(groups = "InboxTest")
    public void searchForEmailWithAttachment(){
        InboxContext.searchForEmailWithAttachment();
        LOG.assertLog(InboxContext.emailContainsAttachment(),"Emails do not contain attachment");
    }
    @Test(groups = "InboxTest")
    public void sendEmailInFullScreen(){
        InboxContext.sendEmailInFullScreen();
        LOG.assertLog(inboxPage.popupOfEmailSentIsDisplayed(),"Email in full screen is not sent");

    }
    @Test(groups = "InboxTest")
    public void sendEmailWithNoRecipient(){
        InboxContext.sendEmailWithNoRecipient();
        LOG.assertLog(inboxPage.alertMessageIsDisplayed(),"Email with no recipient should not be sent");
    }
    @Test(groups = "InboxTest")
    public void sendEmailWithNoSubjectAndBody(){
        InboxContext.sendEmailWithNoSubjectAndBody();
        LOG.assertLog(inboxPage.popupOfEmailSentIsDisplayed(),"Email with no subject and body is not sent");
    }
    @Test(groups = "InboxTest")
    public void addEmailToDraftClickingOnCrossIcon(){
        int draftCountStart=InboxContext.getSnoozedAndDraftsCount()[1];
        InboxContext.addEmailToDraft();
        int draftCount=InboxContext.getSnoozedAndDraftsCount()[1];
        boolean countIsIncreased= draftCount > draftCountStart;
        LOG.assertLog(countIsIncreased,"Start count: "+draftCountStart+"\nActual count: "+draftCount);
    }
    @Test(groups = "InboxTest")
    public void sendEmailAndClickOnUndoButton(){
        int draftCountStart=InboxContext.getSnoozedAndDraftsCount()[1];
        InboxContext.addEmailToDraftAndClickOnUndoButton();
        int draftCount=InboxContext.getSnoozedAndDraftsCount()[1];
        boolean countIsIncreased=draftCount>draftCountStart;
        LOG.assertLog(countIsIncreased,"Start count: "+draftCountStart+"\nActual count: "+draftCount);
    }
    @Test(groups = "InboxTest")
    public void markedMessageIsAddedToMarkSection(){
        int[] emailCount=InboxContext.markedMessageIsAddedToMarkSection();
        boolean countIsIncreased= emailCount[1] > emailCount[0];
        LOG.assertLog(countIsIncreased,"Start count: "+emailCount[0]+"\nActual count: "+emailCount[1]);
        int newEmailCount=InboxContext.unmarkedMessageIsRemovedFromMarkSection();
        boolean countIsDecreased=newEmailCount<emailCount[1];
        LOG.assertLog(countIsDecreased,"Start count: "+emailCount[1]+"\nActual count: "+newEmailCount);
    }
    @Test(groups = "InboxTest")
    public void sendEmailInScheduledTime(){
        int scheduleCountStart=InboxContext.getSnoozedAndDraftsCount()[0];
        InboxContext.selectScheduleDate();
        int scheduleCount=InboxContext.getSnoozedAndDraftsCount()[0];
        boolean countIsIncreased= scheduleCount > scheduleCountStart;
        LOG.assertLog(countIsIncreased,"Start count: "+scheduleCountStart+"\nActual count: "+scheduleCount);
        LOG.assertLog(InboxContext.scheduledDateIsSetCorrectly(),"Schedule date is not set correctly");

    }
    @Test(groups = "InboxTest")
    public void sendEmailInScheduleDateAndCLickOnUndoButton(){
        int[] emailsCountStart=InboxContext.getSnoozedAndDraftsCount();
        InboxContext.sendEmailInScheduleDateAndCLickOnUndoButton();
        int[] emailsCount=InboxContext.getSnoozedAndDraftsCount();
        boolean countNotChanged= emailsCountStart[0]== emailsCount[0];
        LOG.assertLog(countNotChanged,"Count is changed");
        boolean countIsIncreased= emailsCountStart[1]< emailsCount[1];
        LOG.assertLog(countIsIncreased,"Count is not increased");
    }
    @Test(groups = "InboxTest")
    public void replySentEmail(){
        InboxContext.enterFromUserInSearchOptions();
        String emailBody=InboxContext.replySentEmail();
        InboxContext.openSentSideOption();
        LOG.assertLog(InboxContext.emailContainsRepliedEmail(),"emails do not contain replied email");
        boolean containsText=emailBody.contains(inboxPage.getRepliedEmailMessageBody());
        LOG.assertLog(containsText,emailBody+" does not contain email text body");
    }
    @AfterMethod(groups = "InboxTest")
    public void openNewTab(){
        try {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));
            driver.get(ConfigProperties.getProperty("loginPageGmail"));
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
