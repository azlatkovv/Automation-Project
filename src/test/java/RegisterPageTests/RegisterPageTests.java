package RegisterPageTests;

import BasePageTests.commonMethods;
import org.testng.annotations.Test;

public class RegisterPageTests extends commonMethods {

    @Test
    public void testSuccessRegisterPageTransition(){

    }
    @Test
    public void userNameTakenMessage(){

    }
    @Test
    public void minRequiredSymbolsForUsername(){
     //Minimum 2 characters !
    }

    @Test
    public void emailFieldFillRequiredMessage (){
        //This field is required!
    }
    @Test
    public void passwordsDontMatch(){
        //Passwords do not match!
    }
    @Test
    public void minimumPasswordChars(){
        //Minimum 6 characters !
    }

    @Test
    public void passwordRules(){
        //Must contain digit and uppercase letter!
    }

    /*@Test
    public void loginTest() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.id("nav-link-login"));
        WebDriverWait explicitWait = new WebDriverWait(this.webDriver, Duration.ofSeconds(3));
        loginButton.click();
        WebElement userNameField = webDriver.findElement(By.id("defaultLoginFormUsername"));
        Assert.assertEquals(webDriver.getCurrentUrl(),
                "http://training.skillo-bg.com:4300/users/login" ,
                "Login page it's not loaded");
    }*/
}
