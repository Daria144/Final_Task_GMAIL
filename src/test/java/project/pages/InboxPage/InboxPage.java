package project.pages.InboxPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.pages.BasePage;

import java.util.List;

public class InboxPage extends BasePage {

    public InboxPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "(//div[@role=\"main\"]//tr[@role=\"row\"])[1]")
    private WebElement emailFirst;
    @FindBy(xpath = "//div[@class=\"z0\"]/div[@role=\"button\"]")
    private WebElement composeEmailButton;
    @FindBy(xpath = "//div[@data-message-id]//div[@dir=\"ltr\"]\n")
    private WebElement draftMessageBody;
    @FindBy(xpath = "(//span[@data-tooltip=\"Із зірочкою\"])[1]")
    private WebElement emailFirstStar;
    @FindBy(xpath = "(//span[@data-tooltip=\"Без зірочки\"])[1]")
    private WebElement emailFirstNoStar;
    @FindBy(xpath = "(//div[@role=\"main\"]//td[@role=\"gridcell\"]/div/text())[6]")
    private static String snoozedDateText;
    //SendEmailDialog
    @FindBy(xpath = "//*[contains(@data-tooltip,'На весь екран')]")
    protected static WebElement fullScreen;
    @FindBy(xpath = "//*[contains(@data-tooltip,'Вийти з повноекранного режиму')]")
    protected static WebElement exitFullScreen;
    @FindBy(xpath = "//*[contains(@data-tooltip,'Зберегти та закрити')]")
    protected static WebElement crossButton;
    @FindBy(xpath = "//*[@name=\"to\"]")
    protected static WebElement recipientField;
    @FindBy(xpath = "//*[@role=\"textbox\"]")
    protected static WebElement emailBody;
    @FindBy(xpath = "//tbody//div[contains(@data-tooltip,'Надіслати')]")
    protected static WebElement sendButton;
    @FindBy(xpath = "//div[1]/div/div[3]/div/div/div[2]/div[@role=\"button\"]")
    protected static By popupOfEmailSent;
    @FindBy(xpath = "//div[@role=\"alertdialog\"]")
    protected static WebElement alertMessage;
    @FindBy(xpath = "//div[@style=\"position: absolute;\"]//div[@role=\"dialog\"]")
    protected  static WebElement emailDialog;
    @FindBy(xpath = "//*[@id=\"link_undo\"]")
    protected static  WebElement undoButton;
    @FindBy(xpath = "//form/div/div/span[@email]")
    protected static WebElement recipientFilledData;
    @FindBy(xpath = "//*[@data-tooltip=\"Інші параметри надсилання\"]")
    protected static WebElement moreSendOptions;
    @FindBy(xpath = "//*[@selector=\"scheduledSend\"]")
    protected static WebElement scheduleEmail;
    @FindBy(xpath = "(//span[@role=\"heading\"]/../..//div[@role=\"menuitem\"])[1]")
    protected static WebElement tomorrowMorningSchedule;
    @FindBy(xpath = "((//span[@role=\"heading\"]/../..//div[@role=\"menuitem\"])[1]/div)[2]")
    protected static WebElement scheduleDate;
    //SideMenu
    @FindBy(xpath = "//div[@data-tooltip=\"Чернетки\"]")
    protected static WebElement draftSection;
    @FindBy(xpath = "//div[@data-tooltip=\"Чернетки\"]/div/div/div")
    protected static WebElement draftCount;
    @FindBy(xpath = "//div[@data-tooltip=\"Із зірочкою\"]")
    protected static WebElement staredSection;
    @FindBy(xpath = "//*[@data-tooltip=\"Заплановано\"]")
    protected static WebElement snoozedSection;
    @FindBy(xpath = "//*[@data-tooltip=\"Заплановано\"]/div/div/div")
    protected static WebElement snoozedCount;

    public void openFullScreenDialog(){
        fullScreen.click();
    }
    public void exitFullScreenDialog(){
        exitFullScreen.click();
    }
    public void fillInRecipientField(String login){
        recipientField.click();
        recipientField.sendKeys(login);
        //recipientField.sendKeys();
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
        return draftMessageBody.getText();
    }
    public void clickOnUndoButton(){
        undoButton.click();
    }
    public void clickOnComposeButton(){
        composeEmailButton.click();
    }
    public boolean popupOfEmailSentIsDisplayed() {
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement emailPopup = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[1]/div/div[3]/div/div/div[2]/div[@role=\"button\"]")));
        return emailPopup.isDisplayed();
    }
    public boolean alertMessageIsDisplayed(){
        return alertMessage.isDisplayed();
    }
    public WebElement waitForDraftsSectionDisplayed(){
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement draftSection = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//div[@data-tooltip=\"Чернетки\"]")));
        return draftSection;
    }
    public static int getDraftsCount() {
        String countText=draftCount.getText();
            return Integer.parseInt(countText);

    }
    public void openFirstEmailInList(){
        emailFirst.click();
    }
    public WebElement waitForEmailPopupOpened(){
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement emailPopup = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//div[@role=\"dialog\"]")));
        return emailPopup;
    }
    public String getRecipientOfEmail(){
        return recipientFilledData.getText();
    }
    public String getEmailBodyText(){
        return emailBody.getText();
    }
    public void clickOnEmailFirstStar(){
        emailFirstStar.click();
    }
    public void clickOnEmailFirstNoStar(){
        emailFirstNoStar.click();
    }
    public void openDraftSection(){
        draftSection.click();
    }
    public void openStaredSection(){
        staredSection.click();
    }
    public List<WebElement> getStaredEmails(){
        List<WebElement> staredEmailList = driver.findElements(By
                .xpath("//tbody//span[@aria-label=\"Із зірочкою\"]"));
        return staredEmailList;
    }
    public void openMoreSendOptions(){
        moreSendOptions.click();
    }
    public void scheduleEmailSend(){
        scheduleEmail.click();
    }
    public WebElement waitForSchedulePopupOpened(){
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement schedulePopup = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@role=\"dialog\"]//span[@role=\"heading\"]")));
        return schedulePopup;
    }
    public void selectScheduleDate(){
        tomorrowMorningSchedule.click();
    }
    public String getScheduleDateText(){
        return scheduleDate.getText();
    }
    public void openSnoozedSection(){
        snoozedSection.click();
    }
    public static int getSnoozedCount() {
        return Integer.parseInt(snoozedCount.getText());
    }
    public static String getSnoozedDateText(){
        return snoozedDateText;
    }
    public void waitForSnoozedPopupIsClosed(){
        WebDriverWait wait = new WebDriverWait(driver, 5000); // 5 seconds timeout
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By
                .xpath("//*[@role=\"dialog\"]//span[@role=\"heading\"]")));
    }



}
