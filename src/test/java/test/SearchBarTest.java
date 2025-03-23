package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.factory.Header;
import page.factory.LoginPage;

import java.time.Duration;

public class SearchBarTest extends Prepare{

    @DataProvider(name="usernames")
    public Object[][] usernames(){
     return new Object[][] {
        {"andyzl"}};
}

    @Test(dataProvider = "usernames")
    public void validateUsernameIsWritten(String username) throws InterruptedException {
        WebDriver driver = getDriver();
        Header header = new Header(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.populateUsername("andyzlzlzl");
        loginPage.populatePassword("LiD6X}aJ;G#@Q$w");
        loginPage.clickSignIn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4200/posts/all"));
        header.findUserFromSearchBar(username);
        Assert.assertTrue(header.validateIsUsernameWritten(username), "User not found!");

    }
}
