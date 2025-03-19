package page.factory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class RegisterPage extends NavigationAndURLValidation{
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/register";
    private final WebDriver webDriver;
    @FindBy(xpath = "//*[@formcontrolname ='username']")
    protected WebElement usernameField;
    @FindBy(xpath = "//input[@formcontrolname='username']/following-sibling::span")
    protected WebElement usernameErrorField;
    @FindBy(xpath = "//input[@formcontrolname='email']/following-sibling::span")
    protected WebElement emailErrorField;
    @FindBy(xpath = "//*[@formcontrolname ='email']")
    protected WebElement emailField;
    @FindBy(xpath = "//*[@formcontrolname ='birthDate']")
    protected WebElement birthDayField;
    @FindBy(id = "defaultRegisterFormPassword")
    protected WebElement passwordField;
    @FindBy(xpath = "//input[@id='defaultRegisterFormPassword']/following-sibling::span")
    protected WebElement passwordErrorField;
    @FindBy(xpath = "//*[@formcontrolname='confirmPassword']")
    protected WebElement confirmPasswordField;
    @FindBy(xpath = "//input[@id='defaultRegisterPhonePassword']/following-sibling::span")
    protected WebElement confirmPasswordErrorField;
    @FindBy(xpath = "//*[@formcontrolname='publicInfo']")
    protected WebElement publicInfoField;
    @FindBy(id ="sign-in-button")
    protected WebElement signInButton;
    @FindBy(xpath = "//*[@class = 'toast-error ngx-toastr ng-trigger ng-trigger-flyInOut']")
    protected WebElement singInMessage;


    public RegisterPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void navigateTo(){
        super.navigateTo(webDriver,PAGE_URL);
    }

    public boolean isUrlLoaded(){
        return super.isUrlLoaded(webDriver, PAGE_URL);
    }




    public String getCurrentURL (){
       return webDriver.getCurrentUrl();
    }

    public String usernameFieldRequiredError(){
        this.usernameField.click();
        this.emailField.click();
        return this.usernameErrorField.getText();
    }

    public String passwordFieldRequiredError(){
        this.passwordField.click();
        this.confirmPasswordField.click();
        return this.passwordErrorField.getText();
    }

    public String getEmailFieldError(){
       return this.emailErrorField.getText();
    }

    public String getPasswordFieldError(){
       return this.passwordErrorField.getText();
    }

    public String getConfirmPasswordFieldError(){
       return this.confirmPasswordErrorField.getText();
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
    public void fillPasswordField(String password){
        this.passwordField.sendKeys(password);
    }
    public void fillConfirmPasswordField(String password){
        this.confirmPasswordField.sendKeys(password);
    }
    public void clearEmailField(){
        this.emailField.sendKeys(Keys.BACK_SPACE);
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

    public void cleanDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);

        Assert.assertTrue(directory.isDirectory(), "Invalid directory");

        FileUtils.cleanDirectory(directory);

        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0){
            System.out.printf("All files are deleted in Directory: %n%n", directoryPath);
        } else {
            System.out.printf("Unable to delete the files in Directory: %n%n", directoryPath);
        }
    }

}
