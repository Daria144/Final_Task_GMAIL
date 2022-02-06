package project.context;

import project.pages.LoginPage;

import static project.tests.BaseTest.driver;

public class LoginContext {
    public static LoginPage loginPage = new LoginPage(driver);
    public static void logInPressingEnter(String login, String pwd){
        loginPage.inputLoginAndPressEnter(login);
        loginPage.inputPasswordAndPressEnter(pwd);
    }
    public static void logInClickingOnNextButtons(String login, String pwd){
        loginPage.inputLoginAndClickOnNextButton(login);
        loginPage.inputPasswordAndClickOnNextButton(pwd);
    }

    //объединить элементы которрые надо оготобразить
    // а какие чтоб были кликабельны и передавать уже массив элементов а не каждый по отдельности
    //то есть в пейдже создать метод который возвращает массив элементов
    public static boolean allElementsAreDisplayed(){
        return true;
    }
    public static boolean allElementsAreClickable(){
        return true;
    }


}
