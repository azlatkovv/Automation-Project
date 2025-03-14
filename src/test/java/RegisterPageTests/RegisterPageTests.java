package RegisterPageTests;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.factory.RegisterPage;


public class RegisterPageTests  {
    WebDriver webDriver;

    @BeforeMethod
    public void setUp(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://training.skillo-bg.com:4300/users/register");
    }
    @AfterMethod
    public void tearDown(){
        webDriver.quit();
    }

    @Test
    public void testSuccessRegisterPageTransition(){
     // Successful register (popup)
    }

    @DataProvider(name="getUsers")
    public Object[][] getUsers(){
        return new Object[][]{
                {"testandy","testandy@test.com","S3V3+9RRPC7F,=Dh", "S3V3+9RRPC7F,=Dh", "Public info"}};
    }

    @Test
    public void userNameEmptyFieldMessage(){
        RegisterPage registerPage1 = new RegisterPage(webDriver);
        registerPage1.isUrlLoaded();
        Assert.assertEquals(registerPage1.usernameFieldRequiredError(),
               "This field is required!", "Error message is incorrect!");

    }

    @Test(dataProvider = "getUsers")
    public void userNameTakenMessage(String username, String email,String password, String confirmPassword ,String publicInfo){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.isUrlLoaded();
        registerPage.fillRegisterForm(username, email, password, confirmPassword , publicInfo);
        registerPage.onSignInMessage("Username taken");
    }

    @Test
    public void minRequiredSymbolsForUsername(){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.fillUsernameField("a");
        Assert.assertEquals(registerPage.usernameFieldRequiredError(),
                "Minimum 2 characters !", "Error message is incorrect!");
        registerPage.fillEmailField("");
    }

    @Test
    public void emailFieldFillRequiredMessage (){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.fillEmailField("a");
        registerPage.clearEmailField();

        Assert.assertEquals(registerPage.getEmailFieldRequiredErrorText(),
                "This field is required!", "Error message is incorrect!");
    }

    @Test
    public void passwordsDoNotMatch(){
        //Passwords do not match!
    }

    @Test
    public void minimumPasswordChars(){
        //Minimum 6 characters !
        //sendkeys 1 symbol
    }

    @Test
    public void passwordRules(){
        //Must contain digit and uppercase letter!
        //type  symbols without Uppercase
    }

    @Test
    public void passwordRequired(){
        //This field is required!
         //click on it and click somewhere else
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
