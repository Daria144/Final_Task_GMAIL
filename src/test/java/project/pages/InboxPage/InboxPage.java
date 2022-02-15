package project.pages.InboxPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.pages.BasePage;

import static project.pages.InboxPage.SendEmailDialog.*;
import static project.pages.InboxPage.SideMenu.*;

public class InboxPage extends BasePage {

    public InboxPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "(//tr[@role=\"row\"])[1]")
    private WebElement emailFirst;
    @FindBy(xpath = "//div[@class=\"z0\"]/div[@role=\"button\"]")
    private WebElement composeEmailButton;
    @FindBy(xpath = "//div[@data-message-id]//div[@dir=\"ltr\"]\n")
    private WebElement draftMessage;
    @FindBy(xpath = "//*[@id=\"link_undo\"]")
    private WebElement undoButton;
    /*   @FindBy(xpath = "(//tr[@role=\"row\"])[1]//div[@role=\"checkbox\"]")
//    private WebElement sentEmailCheckbox;
//    @FindBy(xpath = "(//tr[@role=\"row\"])[1]//span[@aria-label=\"Not starred\"]")
//    private WebElement sentEmailStar;
//    @FindBy(xpath = "//*[@name=\"to\"]")
//    private WebElement recipientField;
//    @FindBy(xpath = "//*[@role=\"textbox\"]")
//    private WebElement emailBody;
//    //@FindBy(xpath = "//*[@data-tooltip=\"Send \u202A(⌘Enter)\u202C\"]")
//    private WebElement sendEmailButton;
//    @FindBy(xpath = "//div[@data-tooltip-align=\"r\"]/div//span")
//    private WebElement inboxMessageCount;
//    @FindBy(xpath = "//td[@role=\"gridcell\"]//img[@src]")
//    private WebElement attachedFileInInbox;
//    @FindBy(xpath = "//td[@role=\"gridcell\"]//img[@src]/../../../..")
//    private WebElement emailWithAttachedFile;
//    @FindBy(xpath ="//*[@data-tooltip=\"Завантажити\"]")
//    private WebElement downloadFileButton;
//    @FindBy(xpath = "(//table[@role=\"presentation\"]//span[@role=\"link\"] )[1]")
//    private WebElement replyButton;
//    @FindBy(xpath = "(//table[@role=\"presentation\"]//span[@role=\"link\"] )[2]")
//    private WebElement forwardButton;
//    @FindBy(xpath = "//input[@aria-label=\"Пошук у пошті\"]")
//    private WebElement searchInput;searchInput*/
    //SendEmailDialog page
    @FindBy(xpath = "//*[contains(@data-tooltip,'На весь екран')]")
    private  WebElement fullScreen;
    @FindBy(xpath = "//*[contains(@data-tooltip,'Вийти з повноекранного режиму')]")
    private  WebElement exitFullScreen;
    @FindBy(xpath = "//*[contains(@data-tooltip,'Зберегти та закрити')]")
    private  WebElement crossButton;
    @FindBy(xpath = "//*[@name=\"to\"]")
    private  WebElement recipientField;
    @FindBy(xpath = "//*[@role=\"textbox\"]")
    private  WebElement emailBody;
    @FindBy(xpath = "//tbody//div[contains(@data-tooltip,'Надіслати')]")
    private  WebElement sendButton;
    @FindBy(xpath = "//div[1]/div/div[3]/div/div/div[2]/div[@role=\"button\"]")
    private  WebElement popupOfEmailSent;
    @FindBy(xpath = "//div[@role=\"alertdialog\"]")
    private  WebElement alertMessage;
    @FindBy(xpath = "//div[@style=\"position: absolute;\"]//div[@role=\"dialog\"]")
    private   WebElement emailDialog;

    public void openFullScreenDialog(){
        fullScreen.click();
    }
    public void exitFullScreenDialog(){
        exitFullScreen.click();
    }
    public void fillInRecipientField(String login){
        recipientField.sendKeys(login);
        recipientField.sendKeys();
    }
    public void fillInEmailBody(String body){
        emailBody.sendKeys(body);
    }
    public void clickOnSendEmailButton(){
        sendButton.click();
    }
    public void clickOnCrossButton(){
        crossButton.click();
    }
    public String getDraftsText(){
        return draftMessage.getText();
    }
    public void clickOnUndoButton(){
        undoButton.click();
    }
    public void clickOnComposeButton(){
        composeEmailButton.click();
    }
    public boolean popupOfEmailSentIsDisplayed() {
        return popupOfEmailSent.isDisplayed();
    }
    public boolean alertMessageIsDisplayed(){
        return alertMessage.isDisplayed();
    }
    public static int getDraftsCount() {
        return Integer.parseInt(draftEl.getText());
    }
    public void openFirstEmailInList(){
        emailFirst.click();
    }
    public boolean emailDialogIsOpened(){
        return emailDialog.isDisplayed();
    }

    public WebElement waitForEmailPopupOpened(){
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement emailPopup = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//div[@role=\"dialog\"]")));
        return emailPopup;
    }


}
