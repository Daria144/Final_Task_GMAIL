package project.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
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
    @BeforeClass
    public void userIsLoggedIn(){
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
        Assert.assertTrue(LoginContext.userLabelIsDisplayed());
    }
    @Test
    public void allEmailOptionsAreAvailable(){
        inboxPage.selectFirstEmail();
        Assert.assertTrue(elementsAreClickable(inboxPage.emailDialogOptions()));
    }
    @Test
    public void allEmailPopupOptionsAreAvailable(){
        inboxPage.clickOnComposeButton();
        Assert.assertTrue(elementsAreClickable(inboxPage.getOptionsToBeClickable()));
    }
    @Test
    public void searchForEmailWithKeyword(){
        inboxPage.setKeywordInSearchField(TestDataProperties.getTestData("keyword"));
        Assert.assertTrue(InboxContext.searchResultsContainsKeyword());
    }
    @Test
    public void enterFromUserInSearchOptions(){
        InboxContext.enterFromUserInSearchOptions();
        Assert.assertTrue(InboxContext.emailSendersContainsEnteredEmail());
    }
    @Test
    public void emailIsSentAndThenRead(){
        int readCountStart=InboxContext.getReadCount();
        String[] filledData=InboxContext.sentEmailToCurrentUser();
        int readCountIncreased=InboxContext.getReadCount();
        boolean countIsIncreased=readCountStart<readCountIncreased;
        Assert.assertTrue(countIsIncreased,"count is increased");
        Assert.assertTrue(InboxContext.emailContainsAllEnteredData(filledData));
        int readCountDecreased=InboxContext.getReadCount();
        boolean countIsDecreased=readCountIncreased>readCountDecreased;
        Assert.assertTrue(countIsDecreased,"count is decreased");
    }
    @Test
    public void searchForEmailWithAttachment(){
        InboxContext.searchForEmailWithAttachment();
        Assert.assertTrue(InboxContext.emailContainsAttachment());
    }
    @Test
    public void sendEmailInFullScreen(){
        InboxContext.sendEmailInFullScreen();
        Assert.assertTrue(inboxPage.popupOfEmailSentIsDisplayed());

    }
    @Test
    public void sendEmailWithNoRecipient(){
        InboxContext.sendEmailWithNoRecipient();
        Assert.assertTrue(inboxPage.alertMessageIsDisplayed());
    }
    @Test
    public void sendEmailWithNoSubjectAndBody(){
        InboxContext.sendEmailWithNoSubjectAndBody();
        Assert.assertTrue(inboxPage.popupOfEmailSentIsDisplayed());
    }
    @Test
    public void addEmailToDraftClickingOnCrossIconAndOpenIt(){
        int draftCountStart=InboxContext.getSnoozedAndDraftsCount()[1];
        String emailBody = InboxContext.addEmailToDraftAndReturnBody();
        int draftCount=InboxContext.getSnoozedAndDraftsCount()[1];
        boolean countIsIncreased= draftCount > draftCountStart;
        Assert.assertTrue(countIsIncreased);
        Assert.assertTrue(InboxContext.draftContainsAllEnteredData(emailBody));
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
        int scheduleCountStart=InboxContext.getSnoozedAndDraftsCount()[0];
        InboxContext.selectScheduleDate();
        int scheduleCount=InboxContext.getSnoozedAndDraftsCount()[0];
        boolean countIsIncreased= scheduleCount > scheduleCountStart;
        Assert.assertTrue(countIsIncreased);
        Assert.assertTrue(InboxContext.scheduledDateIsSetCorrectly());
    }
    @Test
    public void sendEmailInScheduleDateAndCLickOnUndoButton(){
        int[] emailsCountStart=InboxContext.getSnoozedAndDraftsCount();
        InboxContext.sendEmailInScheduleDateAndCLickOnUndoButton();
        int[] emailsCount=InboxContext.getSnoozedAndDraftsCount();
        boolean countNotChanged= emailsCountStart[0]== emailsCount[0];
        Assert.assertTrue(countNotChanged);
        Assert.assertTrue(InboxContext.emailPopupIsDisplayed());
        boolean countIsIncreased= emailsCountStart[1]< emailsCount[1];
        Assert.assertTrue(countIsIncreased);
    }
    @Test
    public void replySentEmail(){
        InboxContext.enterFromUserInSearchOptions();
        String emailBody=InboxContext.replySentEmail();
        //Assert.assertTrue(inboxPage.popupOfEmailSentIsDisplayed());
        InboxContext.openSentSideOption();
        Assert.assertTrue(InboxContext.emailContainsRepliedEmail());
        boolean containsText=emailBody.contains(inboxPage.getRepliedEmailMessageBody());
        Assert.assertTrue(containsText);


    }
    @BeforeMethod
    public void openNewTab(){
        try {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));
            driver.get(ConfigProperties.getProperty("loginPage"));
            Assert.assertTrue(LoginContext.userLabelIsDisplayed());
        } catch (UnhandledAlertException f) {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                System.out.println("Alert data: " + alertText);
                alert.accept();
            } catch (NoAlertPresentException e) {
                e.printStackTrace();
            }
        }
    }
}
