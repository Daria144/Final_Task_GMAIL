package project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InboxPage {
    public WebDriver driver;

    public InboxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "(//tr[@role=\"row\"])[1]")
    private WebElement sentEmail;
    @FindBy(xpath = "(//tr[@role=\"row\"])[1]//div[@role=\"checkbox\"]")
    private WebElement sentEmailCheckbox;
    @FindBy(xpath = "(//tr[@role=\"row\"])[1]//span[@aria-label=\"Not starred\"]")
    private WebElement sentEmailStar;
    @FindBy(xpath = "(//tr[@role=\"row\"])[1]//*[@data-tooltip=\"Archive\"]")
    private WebElement archiveOption;
    @FindBy(xpath = "(//tr[@role=\"row\"])[1]//*[@data-tooltip=\"Delete\"]")
    private WebElement deleteOption;
    @FindBy(xpath = "(//tr[@role=\"row\"])[1]//*[@data-tooltip=\"Mark as unread\"]")
    private WebElement markAsUnreadOption;
    @FindBy(xpath = "(//tr[@role=\"row\"])[1]//*[@data-tooltip=\"Snooze\"]")
    private WebElement snoozeOption;

    @FindBy(xpath = "//div[@class=\"z0\"]/div[@role=\"button\"]")
    private WebElement composeEmailButton;
    @FindBy(xpath = "//*[@name=\"to\"]")
    private WebElement recipientField;
    @FindBy(xpath = "//*[@role=\"textbox\"]")
    private WebElement emailBody;
    //@FindBy(xpath = "//*[@data-tooltip=\"Send \u202A(⌘Enter)\u202C\"]")
    private WebElement sendEmailButton;
    @FindBy(xpath = "//div[@data-tooltip-align=\"r\"]/div//span")
    private WebElement inboxMessageCount;
    @FindBy(xpath = "//td[@role=\"gridcell\"]//img[@src]")
    private WebElement attachedFileInInbox;
    @FindBy(xpath = "//td[@role=\"gridcell\"]//img[@src]/../../../..")
    private WebElement emailWithAttachedFile;
    @FindBy(xpath ="//*[@data-tooltip=\"Завантажити\"]")
    private WebElement downloadFileButton;
    @FindBy(xpath = "(//table[@role=\"presentation\"]//span[@role=\"link\"] )[1]")
    private WebElement replyButton;
    @FindBy(xpath = "(//table[@role=\"presentation\"]//span[@role=\"link\"] )[2]")
    private WebElement forwardButton;
    @FindBy(xpath = "//input[@aria-label=\"Пошук у пошті\"]")
    private WebElement searchInput;



    /*
    @FindBy(xpath = "")
    private WebElement ;
     */



    public void hoverElement(){}
    public static void openEmail(){}
    public static boolean attachementIsDownloaded(){
        return false;
    }
    public static boolean emailIsReplyed(){
        return false;
    }
    public static boolean emailIsForwarded(){
        return false;
    }




}
