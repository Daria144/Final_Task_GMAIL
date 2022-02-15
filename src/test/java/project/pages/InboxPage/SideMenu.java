package project.pages.InboxPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideMenu {
    public WebDriver driver;

    public SideMenu(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//div[@data-tooltip=\"Чернетки\"]/div/div/div")
    protected static WebElement draftEl;



}
