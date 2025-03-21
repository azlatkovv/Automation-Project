package RegisterPageTests;

import com.sun.source.tree.AssertTree;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.factory.Header;
import page.factory.LoginPage;
import page.factory.ProfilePage;
import page.factory.RegisterPage;

import java.time.Duration;

public class SearchBarTest extends Prepare{

    @DataProvider(name="usernames")
    public Object[][] usernames(){
     return new Object[][] {
        {"andyzl"}};
}

    @Test(dataProvider = "usernames")
    public void validateUserExisting(String username) throws InterruptedException {
        WebDriver driver = getDriver();
        Header header = new Header(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
      //  driver.navigate().to("http://training.skillo-bg.com:4300/users/login");
        loginPage.populateUsername("andyzlzlzl");
        loginPage.populatePassword("LiD6X}aJ;G#@Q$w");
        loginPage.clickSignIn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4200/posts/all"));

        driver.navigate().to("http://training.skillo-bg.com:4200/users/9312");
        Thread.sleep(10000);
        header.findUserFromProfilePage(username);
        Thread.sleep(10000);
        Assert.assertTrue(header.validateExactMatchingUsername(username), "User not found!");

    }
}
