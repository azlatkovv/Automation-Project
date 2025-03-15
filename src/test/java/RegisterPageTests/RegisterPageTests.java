package RegisterPageTests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import page.factory.RegisterPage;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


public class RegisterPageTests {
    WebDriver webDriver;
    public static final String TEST_RESOURCES_DIR = "src\\resources\\";
    public static final String SCREENSHOT_DIR = TEST_RESOURCES_DIR.concat("screenshot\\");

    @BeforeSuite
    public void cleanDir() throws IOException {
        RegisterPage registerpage = new RegisterPage(webDriver);
        registerpage.cleanDirectory("src\\resources\\screenshots");

    }
    @BeforeMethod
    public void setUp (){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://training.skillo-bg.com:4300/users/register");
    }

    @BeforeClass
    protected void setupTestSuite() throws IOException {
}

    @AfterMethod
    public void takeFailedTestScreenshot(ITestResult testResult){
      if(ITestResult.FAILURE == testResult.getStatus()){
          TakesScreenshot screenshot = (TakesScreenshot) webDriver;
          File source = screenshot.getScreenshotAs(OutputType.FILE);
          File destination = new File(System.getProperty("user.dir") + "/src/resources/screenshots/(" +
                  LocalDate.now()+ testResult.getName() + ".png");
          try{
              FileHandler.copy(source, destination);
          }
          catch (IOException e){
              System.out.println("Screenshot located at " + destination);
          }
      }
    }




    @AfterMethod
    public void tearDown(){
        webDriver.quit();
    }

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
    public void testSuccessRegisterPageTransition(String username, String email,String password, String confirmPassword ,String publicInfo){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.fillRegisterForm(username, email, password, confirmPassword , publicInfo);
        Assert.assertEquals(registerPage.getCurrentURL(), "http://training.skillo-bg.com:4300/posts/all", "Register failed!");
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

        Assert.assertEquals(registerPage.getEmailFieldError(),
                "This field is required!", "Error message is incorrect!");
    }

    @Test(dataProvider = "getUsers")
    public void passwordsDoNotMatch(String username, String email,String password, String confirmPassword ,String publicInfo){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.fillPasswordField(password);
        registerPage.fillConfirmPasswordField(publicInfo);
        Assert.assertEquals(registerPage.getConfirmPasswordFieldError(),
                "Passwords do not match!", "Error message is incorrect!");
    }

    @Test(dataProvider = "shortPassword")
    public void minimumPasswordChars(String username, String email,String password, String confirmPassword ,String publicInfo){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.fillPasswordField(password);
        Assert.assertEquals(registerPage.getPasswordFieldError(),
                "Minimum 6 characters !", "Error message is incorrect!");
    }

    @Test(dataProvider = "getNewUsers")
    public void passwordRules(String username, String email,String password, String confirmPassword ,String publicInfo){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.fillPasswordField(password);
        Assert.assertEquals(registerPage.getPasswordFieldError(),
                "Must contain digit and uppercase letter!", "Error message is incorrect!");
    }

    @Test
    public void passwordRequired(){
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.passwordFieldRequiredError();
        Assert.assertEquals(registerPage.passwordFieldRequiredError(),
                "This field is required!", "Error message is incorrect!");
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
