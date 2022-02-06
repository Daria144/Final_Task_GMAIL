package project.tests;

import org.testng.annotations.Test;
import project.configuration.TestDataProperties;
import project.context.LoginContext;

public class LoginTest extends BaseTest{


    @Test
    public void allElementsAreDisplayed(){
        LoginContext.allElementsAreDisplayed();
        LoginContext.allElementsAreClickable();
    }
    @Test (groups = "LoginHPTest")
    public void logInWithValidCredPressingEnter(){
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
    }
    @Test
    public void logInWithValidCredClickingOnNextButtons(){
        LoginContext.logInClickingOnNextButtons(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("password"));
    }
    @Test (groups = "LoginNPTest")
    public void logInWithInvalidPwd() {
        LoginContext.logInPressingEnter(TestDataProperties.getTestData("login"),TestDataProperties.getTestData("incorrectPassword"));
    }
}
