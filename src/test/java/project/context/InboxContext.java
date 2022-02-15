package project.context;

import project.configuration.TestDataProperties;
import project.pages.InboxPage.InboxPage;
import project.pages.InboxPage.SendEmailDialog;
import project.pages.InboxPage.SideMenu;
import project.utils.StringUtils;

import static project.tests.BaseTest.driver;

public class InboxContext {
    public static InboxPage inboxPage = new InboxPage(driver);
    private static String randomString = StringUtils.RandomString(10);

    public static void sendEmailInFullScreen(){
        inboxPage.clickOnComposeButton();
        inboxPage.openFullScreenDialog();
        inboxPage.fillInRecipientField(TestDataProperties.getTestData("loginRec"));
        inboxPage.fillInEmailBody(randomString);
        inboxPage.clickOnSendEmailButton();
    }

    public static void sendEmailWithNoRecipient(){
        inboxPage.clickOnComposeButton();
        inboxPage.fillInEmailBody(randomString);
        inboxPage.clickOnSendEmailButton();
    }
    public static void sendEmailWithNoSubjectAndBody(){
        inboxPage.clickOnComposeButton();
        inboxPage.fillInRecipientField(TestDataProperties.getTestData("loginRec"));
        inboxPage.clickOnSendEmailButton();
        driver.switchTo().alert().accept();
    }
    public static boolean addEmailToDraftAndCountIsIncreased(){
        int [] draftsCount=new int[2];
                draftsCount[0] = InboxPage.getDraftsCount();
        inboxPage.clickOnComposeButton();
        inboxPage.fillInEmailBody(randomString);
        inboxPage.clickOnCrossButton();
        draftsCount[1] = InboxPage.getDraftsCount();
        boolean countIsIncreased = false;
        if (draftsCount[1]>draftsCount[0]){
            countIsIncreased=true;
        }
        return countIsIncreased;
    }

    public static boolean draftIsOnTopOfPageAndContainsAllEnteredData(){
        inboxPage.openFirstEmailInList();
        boolean textEqualsEntered;
        textEqualsEntered= inboxPage.getDraftsText().equals(randomString);
        return textEqualsEntered;
    }

    public static void addEmailToDraftAndClickOnUndoButton(){
        inboxPage.fillInRecipientField(TestDataProperties.getTestData("loginRec"));
        inboxPage.fillInEmailBody(randomString);
        inboxPage.clickOnSendEmailButton();
        inboxPage.clickOnUndoButton();
    }
    public static boolean emailPopupIsDisplayed(){
        return inboxPage.waitForEmailPopupOpened().isDisplayed();
    }



}
