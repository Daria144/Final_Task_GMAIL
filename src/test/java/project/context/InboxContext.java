package project.context;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import project.configuration.TestDataProperties;
import project.pages.InboxPage.InboxPage;
import project.utils.StringUtils;

import java.util.List;

import static project.tests.BaseTest.driver;

public class InboxContext {
    public static InboxPage inboxPage = new InboxPage(driver);
    private static String randomString = StringUtils.RandomString(10);
    private static String scheduleDate;
    static Actions builder = new Actions(driver);

    public static int getReadCount(){
        builder.moveToElement(InboxPage.getInputSection()).build().perform();
        int emailsCount = InboxPage.getInputCount();
        return emailsCount;
    }
    public static String[] sentEmailToCurrentUser(){
        String[] filledData = openEmailDialogAndReturnedEnteredData(TestDataProperties.getTestData("login"));
        inboxPage.clickOnSendEmailButton();
        inboxPage.waitForEmailPopupIsClosed();
        return filledData;
    }
    public static void openEmailDialogAndFillInRequiredData(){
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        inboxPage.fillInRecipientField(TestDataProperties.getTestData("loginRec"));
        inboxPage.fillInEmailBody(randomString);
    }
    private static String[] openEmailDialogAndReturnedEnteredData(String recipient){
        String[] emailFilledData=new String[2];
        emailFilledData[0]=recipient;
        emailFilledData[1]=randomString;
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        inboxPage.fillInRecipientField(emailFilledData[0]);
        inboxPage.fillInEmailBody(emailFilledData[1]);
        return emailFilledData;
    }
    public static void sendEmailInFullScreen(){
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        inboxPage.openFullScreenDialog();
        inboxPage.fillInRecipientField(TestDataProperties.getTestData("loginRec"));
        inboxPage.fillInEmailBody(randomString);
        inboxPage.clickOnSendEmailButton();
    }
    public static void sendEmailWithNoRecipient(){
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        inboxPage.fillInEmailBody(randomString);
        inboxPage.clickOnSendEmailButton();
    }
    public static void sendEmailWithNoSubjectAndBody(){
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        inboxPage.fillInRecipientField(TestDataProperties.getTestData("loginRec"));
        inboxPage.clickOnSendEmailButton();
        driver.switchTo().alert().accept();
    }
    public static String addEmailToDraftAndReturnBody(){
        inboxPage.waitForDraftsSectionDisplayed();
        inboxPage.openDraftSection();
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        inboxPage.fillInEmailBody(randomString);
        inboxPage.clickOnCrossButton();
        inboxPage.waitForEmailPopupIsClosed();
        return randomString;
    }
    public static boolean draftContainsAllEnteredData(String body){
        inboxPage.openFirstEmailInList();
        boolean contains=inboxPage.getEmailBody().contains(body);
        return contains;
    }
    public static String[] addEmailToDraftAndClickOnUndoButton(){
        inboxPage.openDraftSection();
        String[] filledData = openEmailDialogAndReturnedEnteredData(TestDataProperties.getTestData("loginRec"));
        inboxPage.clickOnSendEmailButton();
        inboxPage.clickOnUndoButton();
        inboxPage.waitForEmailPopupOpened();
        inboxPage.clickOnCrossButton();
        inboxPage.waitForEmailPopupIsClosed();
        inboxPage.openFirstEmailInList();
        return filledData;
    }
    public static boolean emailContainsAllEnteredData(String[] data){
        //inboxPage.waitForEmailBodyOpened();
        boolean emailDataEquals;
        emailDataEquals=data[0].equals(inboxPage.getRecipientEmailField());
        String body=inboxPage.getEmailBody();
        emailDataEquals=body.equals(data[1]);
        return emailDataEquals;
    }
    public static boolean emailPopupIsDisplayed(){
        return inboxPage.waitForEmailPopupOpenedAndReturnElement().isDisplayed();
    }
    public static int[] markedMessageIsAddedToMarkSection(){
        int[]emailsCount=new int[2];
        emailsCount[0]=inboxPage.getStaredEmails().size();
        inboxPage.clickOnEmailFirstNoStar();
        emailsCount[1]=inboxPage.getStaredEmails().size();
        return emailsCount;
    }
    public static int unmarkedMessageIsRemovedFromMarkSection(){
        inboxPage.clickOnEmailFirstStar();
        int emailsCount=inboxPage.getStaredEmails().size();
        return emailsCount;
    }
    public static int[] getSnoozedAndDraftsCount(){
        int [] emailsCount =new int[2];
        builder.moveToElement(InboxPage.getSnoozedSection()).perform();
        emailsCount[0] = InboxPage.getSnoozedCount();
        builder.moveToElement(InboxPage.getDraftSection()).perform();
        emailsCount[1] = InboxPage.getDraftsCount();
        return emailsCount;
    }
    public static void openScheduleEmailPopup(){
        openEmailDialogAndFillInRequiredData();
        inboxPage.openMoreSendOptions();
        inboxPage.scheduleEmailSend();
        inboxPage.waitForSchedulePopupOpened();
    }
    public static void selectScheduleDate(){
        openScheduleEmailPopup();
        scheduleDate=inboxPage.getScheduleDateText();
        inboxPage.selectScheduleDate();
        inboxPage.waitForSnoozedPopupIsClosed();
    }
    public static boolean scheduledDateIsSetCorrectly(){
        inboxPage.openSnoozedSection();
        boolean snoozedDatesEqual=scheduleDate.contains(inboxPage.getSnoozedDateInEmail());
        return snoozedDatesEqual;
    }
    public static void sendEmailInScheduleDateAndCLickOnUndoButton(){
        openScheduleEmailPopup();
        inboxPage.waitForDraftsSectionDisplayed();
        inboxPage.selectScheduleDate();
        inboxPage.clickOnUndoButton();
        inboxPage.waitForEmailPopupOpened();
    }
    public static boolean searchResultsContainsKeyword(){
        boolean keyword=false;
        inboxPage.setKeywordInSearchField(TestDataProperties.getTestData("keyword"));
        for (WebElement res : inboxPage.getSearchedResults()) {
            if(res.getText().toLowerCase().contains(TestDataProperties.getTestData("keyword"))){
                keyword=true;
            }
        }
        return keyword;
    }
    public static void enterFromUserInSearchOptions(){
        inboxPage.clickOnSearchOptionsButton();
        inboxPage.waitForSearchOptionPopupOpened();
        inboxPage.enterEmailInFromInput(TestDataProperties.getTestData("loginFrom"));
        inboxPage.clickOnSearchButtonInOptions();
        inboxPage.waitForEmailPopupIsClosed();
    }
    public static boolean emailSendersContainsEnteredEmail(){
        List<WebElement> emailsSenders = inboxPage.getEmailsSenders();
        boolean sendersContains=false;
        for (WebElement emails : emailsSenders) {
            if(emails.getAttribute("email").contains(TestDataProperties.getTestData("loginFrom"))){
                sendersContains=true;
            }
        }
        return sendersContains;
    }
    public static void searchForEmailWithAttachment(){
        inboxPage.clickOnSearchField();
        inboxPage.clickOnSearchOptionsButton();
        inboxPage.waitForSearchOptionPopupOpened();
        inboxPage.clickOnHasAttachmentCheckbox();
        inboxPage.clickOnSearchButtonInOptions();
        inboxPage.waitForEmailPopupIsClosed();
    }
    public static boolean emailContainsAttachment(){
        boolean contains=false;
        for (WebElement el : inboxPage.getEmailsWithAttachment()) {
            if(el.getText().contains("Вкладений")){
                contains=true;
            }
        }
        return contains;
    }
    public static String replySentEmail(){
        inboxPage.openFirstEmailInList();
        inboxPage.clickOnReplyButton();
        inboxPage.waitForRepliedTextareaOpenedAndEnterString(randomString);
        inboxPage.clickOnSendEmailButton();
        return randomString;
    }
    public static void openSentSideOption(){
        builder.moveToElement(inboxPage.getSentEmailSide()).build().perform();
        builder.moveToElement(inboxPage.getSentEmailSide()).click().build().perform();
        inboxPage.openFirstEmailInList();
    }
    public static boolean emailContainsRepliedEmail(){
        return inboxPage.getRepliedEmailAttribute().contains(TestDataProperties.getTestData("loginFrom"));
    }



}
