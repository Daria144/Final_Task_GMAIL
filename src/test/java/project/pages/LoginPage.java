package project.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(id = "identifierId")
    private WebElement loginInput;
    @FindBy(xpath = "//*[@id=\"password\"]//input")
    private WebElement passwordInput;

    public void inputLogin(String login){
        loginInput.sendKeys(login);
        loginInput.sendKeys(Keys.ENTER);
    }

    public void inputPassword(String pwd){
        passwordInput.sendKeys(pwd);
        passwordInput.sendKeys(Keys.ENTER);
    }

}
