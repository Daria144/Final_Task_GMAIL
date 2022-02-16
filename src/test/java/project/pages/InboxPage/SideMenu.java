package project.pages.InboxPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.pages.BasePage;

public class SideMenu extends BasePage {

    public SideMenu(WebDriver driver) {
        super(driver); }


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




}
