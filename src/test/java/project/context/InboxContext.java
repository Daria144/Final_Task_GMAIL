package project.context;

import project.configuration.TestDataProperties;
import project.pages.InboxPage.InboxPage;
import project.utils.StringUtils;

import static project.tests.BaseTest.driver;

public class InboxContext {
    public static InboxPage inboxPage = new InboxPage(driver);
    private static String randomString = StringUtils.RandomString(10);

    public static void openEmailDialogAndFillInRequiredData(){
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        inboxPage.fillInRecipientField(TestDataProperties.getTestData("loginRec"));
        inboxPage.fillInEmailBody(randomString);
    }
    private static String[] openEmailDialogAndReturnedEnteredData(){
        String[] emailFilledData=new String[2];
        emailFilledData[0]=TestDataProperties.getTestData("loginRec");
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
    public static int[] addEmailToDraftAndReturnCount(){
        inboxPage.waitForDraftsSectionDisplayed();
        int [] draftsCount=new int[2];
                draftsCount[0] = InboxPage.getDraftsCount();
        inboxPage.clickOnComposeButton();
        inboxPage.waitForEmailPopupOpened();
        inboxPage.fillInEmailBody(randomString);
        inboxPage.clickOnCrossButton();
        draftsCount[1] = InboxPage.getDraftsCount();
        return draftsCount;
    }
    public static boolean draftContainsAllEnteredData(){
        inboxPage.openDraftSection();
        inboxPage.openFirstEmailInList();
        return inboxPage.getDraftsText().equals(randomString);
    }
    public static String[] addEmailToDraftAndClickOnUndoButton(){
        String[] filledData = openEmailDialogAndReturnedEnteredData();
        inboxPage.clickOnSendEmailButton();
        inboxPage.clickOnUndoButton();
        inboxPage.openDraftSection();
        inboxPage.openFirstEmailInList();
        inboxPage.waitForEmailPopupOpened();
        return filledData;
    }
    public static boolean emailContainsAllEnteredData(String[] data){
        boolean emailDataEquals;
        emailDataEquals=data[0].equals(inboxPage.getRecipientOfEmail());
        String body=inboxPage.getEmailBodyText();
        emailDataEquals=body.equals(data[1]);
        return emailDataEquals;
    }
    public static boolean emailPopupIsDisplayed(){
        return inboxPage.waitForEmailPopupOpened().isDisplayed();
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
    public static void openScheduleEmailPopup(){
        openEmailDialogAndFillInRequiredData();
        inboxPage.openMoreSendOptions();
        inboxPage.scheduleEmailSend();
    }
    public static boolean schedulePopupIsDisplayed(){
        return inboxPage.waitForSchedulePopupOpened().isDisplayed();
    }
    public static int[] selectScheduleDateAndReturnCount(){
        int [] scheduleCount=new int[2];
        scheduleCount[0] = InboxPage.getSnoozedCount();
        //inboxPage.scheduleEmailSend();
        inboxPage.getScheduleDateText();
        inboxPage.selectScheduleDate();
        inboxPage.waitForSnoozedPopupIsClosed();
        scheduleCount[1] = InboxPage.getSnoozedCount();
        return scheduleCount;
    }
    public static void scheduledDateIsSetCorrectly(){
        inboxPage.openSnoozedSection();
        inboxPage.getScheduleDateText();

    }



}
