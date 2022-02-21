package test.java.project.context;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import project.configuration.TestDataProperties;
import test.java.project.pages.InboxPage;
import project.utils.StringUtils;
import java.util.List;
import static test.java.project.tests.BaseTest.driver;

public class InboxContext {
    public static InboxPage inboxPage = new InboxPage(driver);
    private static String randomString = StringUtils.RandomString(10);
    private static String scheduleDate;
    static Actions builder = new Actions(driver);
    private static Logger LOG = Logger.getLogger(InboxContext.class);

    public static int getReadCount(){
        builder.moveToElement(InboxPage.getInputSection()).build().perform();
        int emailsCount = InboxPage.getInputCount();
        LOG.info("Get count of input emails: "+emailsCount);
        return emailsCount;
    }
    public static void sentEmailToCurrentUser(){
        openEmailDialogAndReturnedEnteredData(TestDataProperties.getTestData("login"));
        inboxPage.clickOnSendEmailButton();
        LOG.info("Send email to current user");
        inboxPage.waitForEmailPopupIsClosed();
    }
    public static void openEmailDialogAndFillInRequiredData(){
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        LOG.info("Open email dialog");
        inboxPage.fillInRecipientField(TestDataProperties.getTestData("loginRec"));
        inboxPage.fillInEmailBody(randomString);
        LOG.info("Fill in email with recipient: "+TestDataProperties.getTestData("loginRec")+" and body: "+randomString);
    }
    private static String[] openEmailDialogAndReturnedEnteredData(String recipient){
        String[] emailFilledData=new String[2];
        emailFilledData[0]=recipient;
        emailFilledData[1]=randomString;
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        LOG.info("Open email dialog");
        inboxPage.fillInRecipientField(emailFilledData[0]);
        inboxPage.fillInEmailBody(emailFilledData[1]);
        LOG.info("Fill in email with recipient: "+emailFilledData[0]+" and body: "+emailFilledData[1]);
        return emailFilledData;
    }
    public static void sendEmailInFullScreen(){
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        inboxPage.openFullScreenDialog();
        LOG.info("Open email dialog in full screen");
        inboxPage.fillInRecipientField(TestDataProperties.getTestData("loginRec"));
        inboxPage.fillInEmailBody(randomString);
        LOG.info("Fill in email with recipient: "+TestDataProperties.getTestData("loginRec")+" and body: "+randomString);
        inboxPage.clickOnSendEmailButton();
    }
    public static void sendEmailWithNoRecipient(){
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        inboxPage.fillInEmailBody(randomString);
        inboxPage.clickOnSendEmailButton();
        LOG.info("Fill in only email body with "+randomString+" and click on send button");
    }
    public static void sendEmailWithNoSubjectAndBody(){
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        inboxPage.fillInRecipientField(TestDataProperties.getTestData("loginRec"));
        inboxPage.clickOnSendEmailButton();
        LOG.info("Send email with bo subject and body");
        driver.switchTo().alert().accept();
        LOG.info("Alert is accepted");
    }
    public static void addEmailToDraft(){
        inboxPage.waitForDraftsSectionDisplayed();
        builder.moveToElement(inboxPage.getDraftLabel()).doubleClick().build().perform();
        LOG.info("Open draft section");
        builder.moveToElement(inboxPage.getComposeEmailButton()).click().build().perform();
        inboxPage.waitForEmailPopupOpened();
        LOG.info("Open email dialog");
        inboxPage.fillInEmailBody(randomString);
        inboxPage.clickOnCrossButton();
        inboxPage.waitForEmailPopupIsClosed();
        LOG.info("Enter email body with "+randomString+" and click on cross button");
    }
    public static void addEmailToDraftAndClickOnUndoButton(){
        inboxPage.openDraftSection();
        openEmailDialogAndReturnedEnteredData(TestDataProperties.getTestData("loginRec"));
        inboxPage.clickOnSendEmailButton();
        inboxPage.clickOnUndoButton();
        LOG.info("Send email and then click on Undo button");
        inboxPage.clickOnCrossButton();
        inboxPage.waitForEmailPopupIsClosed();
        inboxPage.openFirstEmailInList();
        LOG.info("Open first email in draft section");
    }
    public static int[] markedMessageIsAddedToMarkSection(){
        int[]emailsCount=new int[2];
        emailsCount[0]=inboxPage.getStaredEmails().size();
        LOG.info("Get stared email count:"+ emailsCount[0]);
        inboxPage.clickOnEmailFirstNoStar();
        emailsCount[1]=inboxPage.getStaredEmails().size();
        LOG.info("Get stared email count increased:"+ emailsCount[1]);
        return emailsCount;
    }
    public static int unmarkedMessageIsRemovedFromMarkSection(){
        inboxPage.clickOnEmailFirstStar();
        int emailsCount=inboxPage.getStaredEmails().size();
        LOG.info("Get stared email count decreased:"+ emailsCount);
        return emailsCount;
    }
    public static int[] getSnoozedAndDraftsCount(){
        int [] emailsCount =new int[2];
        inboxPage.waitForDraftsSectionDisplayed();
        builder.moveToElement(InboxPage.getSnoozedLabel()).perform();
        builder.moveToElement(InboxPage.getSnoozedLabel()).click().build().perform();
        emailsCount[0] = InboxPage.getSnoozedCount();
        LOG.info("Get snoozed emails count:"+ emailsCount[0]);
        builder.moveToElement(InboxPage.getDraftSection()).click().build().perform();
        emailsCount[1] = InboxPage.getDraftsCount();
        LOG.info("Get draft emails count:"+ emailsCount[1]);
        return emailsCount;
    }
    public static void openScheduleEmailPopup(){
        openEmailDialogAndFillInRequiredData();
        inboxPage.openMoreSendOptions();
        inboxPage.scheduleEmailSend();
        inboxPage.waitForSchedulePopupOpened();
        LOG.info("Open schedule popup to be opened");
    }
    public static void selectScheduleDate(){
        openScheduleEmailPopup();
        scheduleDate=inboxPage.getScheduleDateText();
        inboxPage.selectScheduleDate();
        LOG.info("Schedule email sent to tomorrow morning");
        inboxPage.waitForSnoozedPopupIsClosed();
    }
    public static boolean scheduledDateIsSetCorrectly(){
        inboxPage.openSnoozedSection();
        boolean snoozedDatesEqual=scheduleDate.contains(inboxPage.getSnoozedDateInEmail());
        LOG.info("Snoozed email is added to Snooze section");
        return snoozedDatesEqual;
    }
    public static void sendEmailInScheduleDateAndCLickOnUndoButton(){
        openScheduleEmailPopup();
        inboxPage.waitForDraftsSectionDisplayed();
        inboxPage.selectScheduleDate();
        LOG.info("Schedule email sent to tomorrow morning");
        inboxPage.clickOnUndoButton();
        LOG.info("Cancel sending scheduled email");
        inboxPage.waitForEmailPopupOpened();
    }
    public static boolean searchResultsContainsKeyword(){
        inboxPage.waitForSearchOptionPopupClosed();
        boolean keyword=false;
        inboxPage.setKeywordInSearchField(TestDataProperties.getTestData("keyword"));
        for (WebElement res : inboxPage.getSearchedResults()) {
            if(res.getText().toLowerCase().contains(TestDataProperties.getTestData("keyword"))){
                keyword=true;
                LOG.info(res.getText()+" contains "+TestDataProperties.getTestData("keyword"));
            }
        }
        return keyword;
    }
    public static void enterFromUserInSearchOptions(){
        inboxPage.clickOnSearchOptionsButton();
        inboxPage.waitForSearchOptionPopupOpened();
        LOG.info("Open search options");
        inboxPage.enterEmailInFromInput(TestDataProperties.getTestData("loginFrom"));
        inboxPage.clickOnSearchButtonInOptions();
        LOG.info("Search for emails from "+TestDataProperties.getTestData("loginFrom"));
        inboxPage.waitForEmailPopupIsClosed();
    }
    public static boolean emailSendersContainsEnteredEmail(){
        List<WebElement> emailsSenders = inboxPage.getEmailsSenders();
        boolean sendersContains=false;
        for (WebElement emails : emailsSenders) {
            if(emails.getAttribute("email").contains(TestDataProperties.getTestData("loginFrom"))){
                sendersContains=true;
                LOG.info(emails.getAttribute("email")+" contains "+TestDataProperties.getTestData("loginFrom"));

            }
        }
        return sendersContains;
    }
    public static void searchForEmailWithAttachment(){
        inboxPage.clickOnSearchField();
        inboxPage.clickOnSearchOptionsButton();
        LOG.info("Open search options");
        inboxPage.waitForSearchOptionPopupOpened();
        inboxPage.clickOnHasAttachmentCheckbox();
        LOG.info("Select emails with attachment");
        inboxPage.clickOnSearchButtonInOptions();
        inboxPage.waitForEmailPopupIsClosed();
    }
    public static boolean emailContainsAttachment(){
        boolean contains=false;
        for (WebElement el : inboxPage.getEmailsWithAttachment()) {
            if(el.getText().contains("Вкладений")){
                contains=true;
                LOG.info(el.getText()+" contains"+" Вкладений");
            }
        }
        return contains;
    }
    public static String replySentEmail(){
        inboxPage.openFirstEmailInList();
        inboxPage.clickOnReplyButton();
        inboxPage.waitForRepliedTextareaOpenedAndEnterString(randomString);
        LOG.info("Open email and enter "+randomString+" in replied body");
        inboxPage.clickOnSendEmailButton();
        inboxPage.waitForEmailPopupIsClosed();
        return randomString;
    }
    public static void openSentSideOption(){
        builder.moveToElement(inboxPage.getSentEmailSide()).perform();
        builder.moveToElement(inboxPage.getSentEmailSide()).doubleClick().build().perform();
        LOG.info("Open sent section");
        inboxPage.openFirstEmailInList();
    }
    public static boolean emailContainsRepliedEmail(){
        LOG.info(inboxPage.getRepliedEmailAttribute()+" contains "+TestDataProperties.getTestData("loginFrom"));
        return inboxPage.getRepliedEmailAttribute().contains(TestDataProperties.getTestData("loginFrom"));

    }



}
