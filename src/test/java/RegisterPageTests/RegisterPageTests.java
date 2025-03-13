package RegisterPageTests;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.factory.RegisterPage;


public class RegisterPageTests  {
    WebDriver webDriver;

    @BeforeClass
    public void setUp(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://training.skillo-bg.com:4300/users/register");
    }
    @AfterClass
    public void tearDown(){

        webDriver.quit();
    }


    @Test
    public void testSuccessRegisterPageTransition(){
     // Successful register (popup)

    }
    @Test
    public void userNameTakenMessage(){
        // Username is Taken
        //testandy
       RegisterPage registerPage = new RegisterPage(webDriver);
       registerPage.isUrlLoaded();
       Assert.assertEquals(registerPage.usernameFieldRequiredError(), "This field is required!", "Error message is incorrect!");


    }
    @Test
    public void minRequiredSymbolsForUsername(){
     //Minimum 2 characters !
        //sendkeys only 1 symbol
    }

    @Test
    public void emailFieldFillRequiredMessage (){
        //This field is required!
        //sendkeys 1 symbol and delete it
    }
    @Test
    public void passwordsDontMatch(){
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
