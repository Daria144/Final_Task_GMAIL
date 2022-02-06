package project.context;

import project.pages.InboxPage;
import project.pages.LoginPage;

import static project.tests.BaseTest.driver;

public class InboxContext {
    public static InboxPage inboxPage = new InboxPage(driver);

    //объединить элементы которрые надо оготобразить
    // а какие чтоб были кликабельны и передавать уже массив элементов а не каждый по отдельности
    //то есть в пейдже создать метод который возвращает массив элементов
    public static boolean allElementsAreDisplayed(){
        return true;
    }
    public static boolean allElementsAreClickable(){
        return true;
    }
    public static void hoverEmail(){
        inboxPage.hoverElement();
    };
    public static boolean allOptionsFromEmailAreDisplayed(){
        return true;
    }
    public static boolean emailWithAttachementIsDownloaded(){

        return false;
    }

}
