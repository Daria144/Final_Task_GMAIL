package project.pages.InboxPage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.pages.BasePage;

import java.util.List;

public class InboxPage extends BasePage {

    public InboxPage(WebDriver driver){
        super(driver);
    }
    Actions actions = new Actions(driver);

    @FindBy(xpath = "//*[@placeholder=\"Пошук у пошті\"]")
    private WebElement searchField;
    @FindBy(xpath = "//label[text()='Від']/../..//input[@type=\"text\"]")
    private WebElement fromInputField;
    @FindBy(xpath = "//span[text()=\"Містить вкладений файл\"]/../..")
    private WebElement hasAttachmentOption;
    @FindBy(xpath = "(//div[@role=\"main\"]//tr[@role=\"row\"])[1]")
    private WebElement emailFirst;
    @FindBy(xpath = "//button[@data-tooltip=\"Показати параметри пошуку\"]")
    private WebElement searchOptionButton;
    @FindBy(xpath = "(//div[@role=\"main\"]//tr[@role=\"row\"])[1]/td[@data-tooltip=\"Вибрати\"]")
    private WebElement emailFirstSelectCheckbox;
    @FindBy(xpath = "//*[@aria-checked=\"mixed\"]")
    private WebElement selectedLabel;
    @FindBy(xpath = "(//*[@data-tooltip=\"Архівувати\"])[1]")
    private WebElement archiveOption;
    @FindBy(xpath = "(//*[@data-tooltip=\"Повідомити про спам\"])[1]")
    private WebElement reportSpamOption;
    @FindBy(xpath = "(//*[@data-tooltip=\"Видалити\"])[1]")
    private WebElement deleteOption;
    @FindBy(xpath = "(//*[@ data-tooltip=\"Позначити як прочитане\"])[1]")
    private WebElement markAsReadOption;
    @FindBy(xpath = "(//*[@data-tooltip=\"Відкласти\"])[1]")
    private WebElement snoozeOption;
    @FindBy(xpath = "(//*[@data-tooltip=\"Додати до завдань\"])[1]")
    private WebElement addToTasksOption;
    @FindBy(xpath = "(//*[@ data-tooltip=\"Перемістити у\"])[1]")
    private WebElement moveToOption;
    @FindBy(xpath = "(//*[@data-tooltip=\"Мітки\"])[1]")
    private WebElement labelsOption;
    @FindBy(xpath = "(//*[@data-tooltip=\"Більше\"])[1]")
    private WebElement moreOption;

    @FindBy(xpath = "//div[@class=\"z0\"]/div[@role=\"button\"]")
    private WebElement composeEmailButton;
    @FindBy(xpath = "//div[@data-message-id]//div[@dir=\"ltr\"]")
    private WebElement emailMessageBody;
    @FindBy(xpath = "(//div[@data-message-id]//div[@dir=\"ltr\"])[1]")
    private WebElement repliedEmailMessageBody;
    @FindBy(xpath = "(//span[@data-tooltip=\"Із зірочкою\"])[1]")
    private WebElement emailFirstStar;
    @FindBy(xpath = "(//span[@data-tooltip=\"Без зірочки\"])[1]")
    private WebElement emailFirstNoStar;
    @FindBy(xpath = "(//div[@role=\"main\"]//td[@role=\"gridcell\"]/div/text())[6]")
    private static String snoozedDateText;
    @FindBy(xpath = "((//div[@role=\"main\"]//tr[@role=\"row\"])[1]//div[@role=\"link\"]/div/div)[2]")
    private static WebElement snoozedDateInEmail;
//Send email dialog
    @FindBy(xpath = "//*[contains(@data-tooltip,'На весь екран')]")
    protected static WebElement fullScreen;
    @FindBy(xpath = "//*[contains(@data-tooltip,'Вийти з повноекранного режиму')]")
    protected static WebElement exitFullScreen;
    @FindBy(xpath = "//table//img[@alt=\"Згорнути\"]")
    private static WebElement minimizeOption;
    @FindBy(xpath = "//*[contains(@data-tooltip,'Зберегти та закрити')]")
    protected static WebElement crossButton;
    @FindBy(xpath = "//*[@name=\"to\"]")
    protected static WebElement recipientField;
    @FindBy(xpath = "//input[@aria-label=\"Тема\"]")
    private static WebElement emailSubject;
    @FindBy(xpath = "//*[@role=\"textbox\"]")
    protected static WebElement emailBody;
    @FindBy(xpath = "//tbody//div[contains(@data-tooltip,'Надіслати')]")
    protected static WebElement sendButton;
    @FindBy(xpath = "//div[1]/div/div[3]/div/div/div[2]/div[@role=\"button\"]")
    protected static WebElement popupOfEmailSent;
    @FindBy(xpath = "//div[@role=\"alertdialog\"]")
    protected static WebElement alertMessage;
    @FindBy(xpath = "//div[@style=\"position: absolute;\"]//div[@role=\"dialog\"]")
    protected  static WebElement emailDialog;
    @FindBy(xpath = "//*[@id=\"link_undo\"]")
    protected static  WebElement undoButton;
    @FindBy(xpath = "//form/div/div/span[@email]")
    protected static WebElement recipientFilledData;
    @FindBy(xpath = "(//div[@role=\"list\"]//tbody//span/span[@email])[1]")
    private static WebElement recipientEmail;
    @FindBy(xpath = "//form[@method=\"POST\"]/div/div//span[@email]")
    private static WebElement recipientEmailField;
    @FindBy(xpath = "(//*[@role=\"listitem\"])[1]//span[@email]")
    private static WebElement repliedEmail;
    @FindBy(xpath = "//*[@data-tooltip=\"Інші параметри надсилання\"]")
    protected static WebElement moreSendOptions;
    @FindBy(xpath = "//*[@selector=\"scheduledSend\"]")
    protected static WebElement scheduleEmail;
    @FindBy(xpath = "(//span[@role=\"heading\"]/../..//div[@role=\"menuitem\"])[1]")
    protected static WebElement tommorowMorningSchedule;
    @FindBy(xpath = "((//span[@role=\"heading\"]/../..//div[@role=\"menuitem\"])[1]/div)[2]")
    protected static WebElement scheduleDate;
    //side menu
    @FindBy(xpath = "//div[@data-tooltip=\"Вхідні\"]")
    private static WebElement inputSection;
    @FindBy(xpath = "//div[@data-tooltip=\"Вхідні\"]/div/div/div")
    private static WebElement inputCount;
    @FindBy(xpath = "//div[@data-tooltip=\"Чернетки\"]")
    protected static WebElement draftSection;
    @FindBy(xpath = "//div[@data-tooltip=\"Чернетки\"]/div/div/div")
    protected static WebElement draftCount;
    @FindBy(xpath = "(//div[@data-tooltip=\"Чернетки\"]/div/div)[1]")
    protected static WebElement draftLabel;
    @FindBy(xpath = "//div[@data-tooltip=\"Із зірочкою\"]")
    protected static WebElement staredSection;
    @FindBy(xpath = "//*[@data-tooltip=\"Заплановано\"]")
    protected static WebElement snoozedSection;
    @FindBy(xpath = "//*[@data-tooltip=\"Заплановано\"]/div/div/div")
    protected static WebElement snoozedCount;
    @FindBy(xpath = "//*[@role=\"navigation\"]//span[text()='Більше']")
    private static WebElement moreSideOption;
    @FindBy (xpath = "//*[@data-tooltip=\"Категорії\"]")
    private static WebElement categorySideOption;
    @FindBy(xpath = "//div[text()=\"Переслати як вкладений файл\"]/..")
    private static WebElement forwardAsAttachmentOption;
    @FindBy(xpath = "//a[@aria-label=\"Надіслані\"]/../../..")
    private static WebElement sentEmailSide;
    @FindBy(xpath ="(//div[@role=\"main\"]//tr[@role=\"row\"])[1]//span[text()=\"Вкладений файл:\"]/..")
    private static WebElement emailWithAttachment;
    @FindBy(xpath = "//label[text()=\"Містить вкладений файл\"]")
    private static WebElement hasAttachmentCheckbox;
    @FindBy(xpath = "//*[@data-tooltip=\"Пошук у пошті\"]")
    private static WebElement searchButtonInSearchOption;
    @FindBy(xpath = "//*[@data-tooltip=\"Відповісти\"]")
    private static WebElement replyButtonInOpenedEmail;


    public void selectFirstEmail(){
        emailFirstSelectCheckbox.click();
    }
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
    public String getEmailMessageBodyText(){
        return emailMessageBody.getText();
    }
    public String getEmailBody(){ return emailBody.getText();}
    public void clickOnUndoButton(){
        undoButton.click();
    }
    public void clickOnComposeButton(){
        composeEmailButton.click();
    }
    public boolean popupOfEmailSentIsDisplayed() {
        WebDriverWait driverWait=new WebDriverWait(driver,5);
        WebElement emailPopup = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"link_undo\"]")));
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
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement emailFirst = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("(//div[@role=\"main\"]//tr[@role=\"row\"])[1]")));
        emailFirst.click();
    }
    public WebElement waitForEmailPopupOpenedAndReturnElement(){
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement emailPopup = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//div[@role=\"dialog\"]")));
        return emailPopup;
    }
    public void waitForEmailPopupOpened(){
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement emailPopup = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//div[@role=\"dialog\"]")));
    }
    public void waitForEmailBodyOpened(){
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement email = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//div[@data-message-id]//div[@dir=\"ltr\"]")));
    }
    public String getRecipientOfEmail(){
        return recipientFilledData.getText();
    }
    public String getRecipientEmail(){
        return recipientEmail.getAttribute("email");
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
    public void waitForSchedulePopupOpened(){
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement schedulePopup = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@role=\"dialog\"]//span[@role=\"heading\"]")));

    }
    public void selectScheduleDate(){
        tommorowMorningSchedule.click();
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
    public void waitForEmailPopupIsClosed(){
        WebDriverWait wait = new WebDriverWait(driver, 5000); // 5 seconds timeout
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By
                .xpath("//div[@role=\"dialog\"]")));
    }
    public String getSnoozedDateInEmail(){
        return snoozedDateInEmail.getText();
    }
    public static WebElement getInputSection() {
        return inputSection;
    }
    public static int getInputCount() {
        return Integer.parseInt(inputCount.getText());
    }
    public static WebElement getSnoozedSection(){
        return snoozedSection;
    }
    public static WebElement getDraftSection(){
        return draftSection;
    }
    public WebElement[] getOptionsToBeClickable(){
        WebElement[] elements = new WebElement[]{
                selectedLabel,
                archiveOption,
                reportSpamOption,
                deleteOption,
                markAsReadOption,
                snoozeOption,
                addToTasksOption,
                moveToOption,
                labelsOption,
                moreOption

        };
        return elements;

    }
    public void setKeywordInSearchField(String keyword){
        searchField.click();
        searchField.sendKeys(keyword);
        searchField.sendKeys(Keys.ENTER);
    }
    public void clickOnSearchField(){
        searchField.click();
    }
    public List<WebElement> getSearchedResults(){
        List<WebElement> result=driver.findElements(By
                .xpath("//div[@role=\"main\"]//tr[@role=\"row\"]"));
        return result;
    }
    public void clickOnSearchOptionsButton(){
        searchOptionButton.click();
    }
    public void enterEmailInFromInput(String email){
        fromInputField.click();
        fromInputField.sendKeys(email);
        fromInputField.sendKeys(Keys.ENTER);
    }
    public void waitForSearchOptionPopupOpened(){
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement schedulePopup = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//label[text()='Від']/../..//input[@type=\"text\"]")));

    }
    public void waitForSearchOptionPopupClosed(){
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By
                        .xpath("//label[text()='Від']/../..//input[@type=\"text\"]")));

    }
    public List<WebElement> getEmailsSenders(){
        List<WebElement> senders=driver.findElements(By.
                xpath("//div[@role=\"main\"]//tr[@role=\"row\"]//div/span/span[@email]"));
        return senders;
    }
    public void selectHasAttachmentOption(){
        hasAttachmentOption.click();
        hasAttachmentOption.sendKeys(Keys.ENTER);
    }
    public WebElement[] emailDialogOptions(){
        WebElement[] emailDialogOptions = new WebElement[]{
                fullScreen,
                crossButton,
                minimizeOption,
                recipientField,
                emailSubject,
                emailBody,
                sendButton
        };
        return emailDialogOptions;
    }
    public void moreSideMenu(){
        moreSideOption.click();
    }

    public void clickOnCategorySideOption() {
        categorySideOption.click();
    }
    public void waitForUpdateOptionAndClickOnIt(){
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement updatesOption = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@data-tooltip=\"Оновлення\"]")));
        updatesOption.click();
    }
    public WebElement getEmailFirst(){
        return emailFirst;
    }
    public WebElement getForwardAsAttachmentOption(){
        return forwardAsAttachmentOption;
    }
    public void clickOnForwardAsAttachment(){
        forwardAsAttachmentOption.click();
    }
    public WebElement getSentEmailSide(){
        return sentEmailSide;
    }
    public String getTitleNameOfEmailWithAttachment(){
        return emailWithAttachment.getAttribute("title");
    }
    public void clickOnHasAttachmentCheckbox(){
        hasAttachmentCheckbox.click();
    }
    public void clickOnSearchButtonInOptions(){
        searchButtonInSearchOption.click();
    }
    public List<WebElement> getEmailsWithAttachment(){
        List<WebElement>emailsList=driver.findElements(By.xpath("//*[@role=\"main\"]//span[text()=\"Вкладений файл:\"]"));
        return emailsList;
    }
    public void clickOnReplyButton(){
        replyButtonInOpenedEmail.click();
    }
    public void waitForRepliedTextareaOpenedAndEnterString(String str){
        WebDriverWait driverWait=new WebDriverWait(driver,20);
        WebElement replyText = driverWait
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//div[@aria-label=\"Текст повідомлення\"]")));
        replyText.sendKeys(str);
    }
    public String getRepliedEmailAttribute(){
        return repliedEmail.getAttribute("email");
    }
    public String getRepliedEmailMessageBody(){
        return repliedEmailMessageBody.getText();
    }
    public String getRecipientEmailField(){
        return recipientEmailField.getAttribute("email");
    }
    public WebElement getDraftLabel(){
        return draftLabel;
    }
}
