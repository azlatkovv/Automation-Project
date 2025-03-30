package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import page.factory.RegisterPage;
import java.time.Duration;


public class RegisterPageTests extends Prepare{

    @DataProvider(name="getUsers")
    public Object[][] getUsers(){
        return new Object[][]{
                {"testandy","testandy@test.com","S3V3+9RPC7F,=Dh", "S3V3+9RPC7F,=Dh", "Public info"}};
    }

    @DataProvider(name="randoMUserData")
    public Object[][] randoMUserData(){
        return new Object[][]{
                {"S3V3+9RPC7F,=Dh", "S3V3+9RPC7F,=Dh", "Public info"}};
    }

    @DataProvider(name="getNewUsers")
    public Object[][] getNewUsers(){
        return new Object[][]{
                {"testandy","testandy@test.com","svasvasv", "svasvasv", "Public info"}};
    }

    @DataProvider(name="shortPassword")
    public Object[][] shortPassword(){
        return new Object[][]{
                {"testandy","testandy@test.com","S3V", "S3V", "Public info"}};
    }

    @Test(dataProvider = "randoMUserData")
    public void testSuccessRegisterPageTransition(String password, String confirmPassword ,String publicInfo) {
         WebDriver driver = getDriver();
         RegisterPage registerPage = new RegisterPage(driver);
         registerPage.navigateTo();
         registerPage.isUrlLoaded();
         registerPage.fillRegisterForm(registerPage.generateUsername(8,13), registerPage.generateEmail(registerPage.generateUsername(8, 13)),password, confirmPassword , publicInfo);
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         if(!wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all"))){
             Assert.assertEquals(registerPage.getCurrentURL(),"http://training.skillo-bg.com:4300/posts/all", "Register failed!");
         }
    }

    @Test
    public void userNameEmptyFieldMessage(){
        WebDriver driver = getDriver();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        Assert.assertEquals(registerPage.usernameFieldRequiredError(),
               "This field is required!", "Error message is incorrect!");
    }

    @Test(dataProvider = "getUsers")
    public void userNameTakenMessage(String username, String email,String password, String confirmPassword ,String publicInfo){
        WebDriver driver = getDriver();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.fillRegisterForm(username, email, password, confirmPassword , publicInfo);
        registerPage.onSignInMessage("Username taken");
    }

    @Test
    public void minRequiredSymbolsForUsername(){
        WebDriver driver = getDriver();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.fillUsernameField("a");
        Assert.assertEquals(registerPage.usernameFieldRequiredError(),
                "Minimum 2 characters !", "Error message is incorrect!");
        registerPage.fillEmailField("");
    }

    @Test
    public void emailFieldFillRequiredMessage (){
        WebDriver driver = getDriver();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.fillEmailField("a");
        registerPage.clearEmailField();

        Assert.assertEquals(registerPage.getEmailFieldError(),
                "This field is required!", "Error message is incorrect!");
    }

    @Test(dataProvider = "getUsers")
    public void passwordsDoNotMatch(String username, String email,String password, String confirmPassword ,String publicInfo){
        WebDriver driver = getDriver();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.fillPasswordField(password);
        registerPage.fillConfirmPasswordField(publicInfo);
        Assert.assertEquals(registerPage.getConfirmPasswordFieldError(),
                "Passwords do not match!", "Error message is incorrect!");
    }

    @Test(dataProvider = "shortPassword")
    public void minimumPasswordChars(String username, String email,String password, String confirmPassword ,String publicInfo){
        WebDriver driver = getDriver();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.fillPasswordField(password);
        Assert.assertEquals(registerPage.getPasswordFieldError(),
                "Minimum 6 characters !", "Error message is incorrect!");
    }

    @Test(dataProvider = "getNewUsers")
    public void passwordRules(String username, String email,String password, String confirmPassword ,String publicInfo){
        WebDriver driver = getDriver();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.fillPasswordField(password);
        Assert.assertEquals(registerPage.getPasswordFieldError(),
                "Must contain digit and uppercase letter!", "Error message is incorrect!");
    }

    @Test
    public void passwordRequired(){
        WebDriver driver = getDriver();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.passwordFieldRequiredError();
        Assert.assertEquals(registerPage.passwordFieldRequiredError(),
                "This field is required!", "Error message is incorrect!");
    }

}
