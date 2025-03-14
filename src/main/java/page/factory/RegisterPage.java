package page.factory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.security.Key;
import java.time.Duration;

public class RegisterPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/register";
    private final WebDriver webDriver;
    @FindBy(xpath = "//*[@formcontrolname ='username']")
    protected WebElement usernameField;
    @FindBy(xpath = "/html/body/app-root/div[2]/app-register/div/div/form/div[1]/span")
    protected WebElement usernameErrorField;
    @FindBy(xpath = "/html/body/app-root/div[2]/app-register/div/div/form/div[2]/span")
    protected WebElement emailErrorField;
    @FindBy(xpath = "//*[@formcontrolname ='email']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@formcontrolname ='birthDate']")
    private WebElement birthDayField;
    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@formcontrolname='confirmPassword']")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//*[@formcontrolname='publicInfo']")
    private WebElement publicInfoField;
    @FindBy(id ="sign-in-button")
    private WebElement signInButton;
    @FindBy(xpath = "//*[@class = 'toast-error ngx-toastr ng-trigger ng-trigger-flyInOut']")
    private WebElement singInMessage;


    protected WebElement find(By locator){
        return webDriver.findElement(locator);
    }

    protected void click(By locator){
        find(locator).click();
    }

    protected void setText(By locator, String text){
        find(locator).sendKeys(text);
    }

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
    }

    public String getEmailFieldRequiredErrorText(){
       return this.emailErrorField.getText();
    }

    public void clearEmailField(){
       this.emailField.sendKeys(Keys.BACK_SPACE);
    }


    public void fillRegisterForm(String username, String email,String password, String confirmPassword ,String publicInfo){
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.confirmPasswordField.sendKeys(confirmPassword);
        this.emailField.sendKeys(email);
        this.publicInfoField.sendKeys(publicInfo);
        this.birthDayField.sendKeys("11111111");
        this.signInButton.click();
    }

    public void fillUsernameField(String username){
            this.usernameField.sendKeys(username);
    }

    public void fillEmailField(String email){
        this.emailField.sendKeys(email);
    }

    public void onSignInMessage(String message){
        WebDriverWait signInMessageWait = new WebDriverWait(this.webDriver, Duration.ofMillis(1500));
        try {
            signInMessageWait.until(
                    ExpectedConditions.textToBePresentInElement(
                            this.singInMessage
                            , message));
        }catch(TimeoutException exception){
            Assert.fail("Sign in message is not present. Inner exception: " + exception);
        }
    }

}
