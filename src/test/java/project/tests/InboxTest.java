package project.tests;

import org.testng.annotations.Test;
import project.context.InboxContext;
import project.context.LoginContext;

public class InboxTest {

    @Test
    public void allElementsAreDisplayed(){
        InboxContext.allElementsAreDisplayed();
        InboxContext.allElementsAreClickable();
    }
    @Test
    public void allOptionsAreDisplayedHoveringEmail(){
        InboxContext.hoverEmail();
        InboxContext.allOptionsFromEmailAreDisplayed();
    }
    @Test
    public void emailWithAttachenet(){

    }
}
