package RegisterPageTests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.factory.RegisterPage;


public class RegisterPageTests extends Prepare{
    WebDriver driver = getDriver();

    @DataProvider(name="getUsers")
    public Object[][] getUsers(){
        return new Object[][]{
                {"testandy","testandy@test.com","S3V3+9RPC7F,=Dh", "S3V3+9RPC7F,=Dh", "Public info"}};
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

    @Test(dataProvider = "getUsers")
    public void testSuccessRegisterPageTransition(String username, String email,String password, String confirmPassword ,String publicInfo) {
         RegisterPage registerPage = new RegisterPage(webDriver);
         registerPage.navigateTo();
         registerPage.isUrlLoaded();
        registerPage.fillRegisterForm(username, email, password, confirmPassword , publicInfo);

        Assert.assertEquals(registerPage.getCurrentURL(), "http://training.skillo-bg.com:4300/posts/all", "Register failed!");
    }

    @Test
    public void userNameEmptyFieldMessage(){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        Assert.assertEquals(registerPage.usernameFieldRequiredError(),
               "This field is required!", "Error message is incorrect!");
    }

    @Test(dataProvider = "getUsers")
    public void userNameTakenMessage(String username, String email,String password, String confirmPassword ,String publicInfo){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.fillRegisterForm(username, email, password, confirmPassword , publicInfo);
        registerPage.onSignInMessage("Username taken");
    }

    @Test
    public void minRequiredSymbolsForUsername(){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.fillUsernameField("a");
        Assert.assertEquals(registerPage.usernameFieldRequiredError(),
                "Minimum 2 characters !", "Error message is incorrect!");
        registerPage.fillEmailField("");
    }

    @Test
    public void emailFieldFillRequiredMessage (){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.fillEmailField("a");
        registerPage.clearEmailField();

        Assert.assertEquals(registerPage.getEmailFieldError(),
                "This field is required!", "Error message is incorrect!");
    }

    @Test(dataProvider = "getUsers")
    public void passwordsDoNotMatch(String username, String email,String password, String confirmPassword ,String publicInfo){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.fillPasswordField(password);
        registerPage.fillConfirmPasswordField(publicInfo);
        Assert.assertEquals(registerPage.getConfirmPasswordFieldError(),
                "Passwords do not match!", "Error message is incorrect!");
    }

    @Test(dataProvider = "shortPassword")
    public void minimumPasswordChars(String username, String email,String password, String confirmPassword ,String publicInfo){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.fillPasswordField(password);
        Assert.assertEquals(registerPage.getPasswordFieldError(),
                "Minimum 6 characters !", "Error message is incorrect!");
    }

    @Test(dataProvider = "getNewUsers")
    public void passwordRules(String username, String email,String password, String confirmPassword ,String publicInfo){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.fillPasswordField(password);
        Assert.assertEquals(registerPage.getPasswordFieldError(),
                "Must contain digit and uppercase letter!", "Error message is incorrect!");
    }

    @Test
    public void passwordRequired(){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.navigateTo();
        registerPage.isUrlLoaded();
        registerPage.passwordFieldRequiredError();
        Assert.assertEquals(registerPage.passwordFieldRequiredError(),
                "This field is required!", "Error message is incorrect!");
    }

}
