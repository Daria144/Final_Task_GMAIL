package project.pages.InboxPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SendEmailDialog {
    public WebDriver driver;

    public SendEmailDialog (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

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
    protected static WebElement popupOfEmailSent;
    @FindBy(xpath = "//div[@role=\"alertdialog\"]")
    protected static WebElement alertMessage;
    @FindBy(xpath = "//div[@style=\"position: absolute;\"]//div[@role=\"dialog\"]")
    protected  static WebElement emailDialog;




}
