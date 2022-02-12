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

    /**
     * Elements of Login Page - first step
     */
    @FindBy(id = "identifierId")
    private WebElement loginInput;
    @FindBy(xpath = "//div[@class=\"PrDSKc\"]/button")
    private WebElement forgetLoginButton;
    @FindBy(xpath = "//div[@class=\"OIPlvf\"]//button")
    private  WebElement createAccountButton;
    @FindBy(xpath = "//*[@id=\"identifierNext\"]//button")
    private WebElement nextLoginButton;

    /**
     * Elements of Login Page - last step
     */
    @FindBy (id = "profileIdentifier")
    private WebElement profileID;
    @FindBy(xpath = "//*[@id=\"password\"]//input")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@type=\"checkbox\"]")
    private WebElement showPasswordCheckbox;
    @FindBy(xpath = "//*[@id=\"forgotPassword\"]/div/button")
    private WebElement forgetPasswordButton;
    @FindBy(xpath = "//*[@id=\"passwordNext\"]//button")
    private WebElement nextPasswordButton;

    /**
     * enter login tests
     * @param login
     */
    public void inputLoginAndPressEnter(String login){
        loginInput.sendKeys(login);
        loginInput.sendKeys(Keys.ENTER);
    }
    public void inputLoginAndClickOnNextButton(String login){
        loginInput.sendKeys(login);
        nextLoginButton.click();
    }

    /**
     * enter password tests
     * @param pwd
     */
    public void inputPasswordAndPressEnter(String pwd){
        passwordInput.sendKeys(pwd);
        passwordInput.sendKeys(Keys.ENTER);
    }
    public void inputPasswordAndClickOnNextButton(String pwd){
        loginInput.sendKeys(pwd);
        nextPasswordButton.click();
    }

    /**
     *return elements from FirstPage to be displayed
     */

    public WebElement[] getElementsOnFirstPageToBeDisplayed(){
        WebElement[] elements = new WebElement[]{
                loginInput,
                forgetLoginButton,
                createAccountButton,
                nextLoginButton

        };
        return elements;

    }

    /**
     *return elements from FirstPage to be clickable
     */

    public WebElement[] getElementsOnFirstPageToBeClickable(){
        WebElement[] elements = new WebElement[]{
                forgetLoginButton,
                createAccountButton,
                nextLoginButton

        };
        return elements;

    }

    /**
     *return elements from LastPage to be displayed
     */

    public WebElement[] getElementsOnLastPageToBeDisplayed(){
        WebElement[] elements = new WebElement[]{
                profileID,
                passwordInput

        };
        return elements;

    }

    /**
     *return elements from LastPage to be clickable
     */

    public WebElement[] getElementsOnLastPageToBeClickable(){
        WebElement[] elements = new WebElement[]{
                showPasswordCheckbox,
                forgetPasswordButton,
                nextLoginButton
        };
        return elements;

    }

}
