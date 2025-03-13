package page.factory;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage{
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/register";
    private final WebDriver webDriver;
    @FindBy(xpath = "//*[@formcontrolname ='username']")
    protected WebElement usernameField;
    @FindBy(xpath = "/html/body/app-root/div[2]/app-register/div/div/form/div[1]/span")
    protected WebElement usernameErrorField;
    @FindBy(xpath = "//*[@formcontrolname ='email']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@formcontrolname ='birthDate']")
    private WebElement birthDayField;
    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordField;
    @FindBy(xpath = "formcontrolname='confirmPassword']")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "formcontrolname='publicInfo']")
    private WebElement publicInfoField;
    @FindBy(id ="sign-in-button")
    private WebElement signInButton;


    public RegisterPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }



    public boolean isUrlLoaded(){
        WebDriverWait explicitWait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        try{
            explicitWait.until(ExpectedConditions.urlToBe(PAGE_URL));
        }catch(TimeoutException ex) {
            return false;
        }
        return true;
    }

    public String usernameFieldRequiredError(){
        this.usernameField.click();
        this.emailField.click();
       return this.usernameErrorField.getText();
        //return error;
    }
}
