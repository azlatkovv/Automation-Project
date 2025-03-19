package RegisterPageTests;

import page.factory.Header;
import page.factory.HomePage;
import page.factory.LoginPage;
import page.factory.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends Prepare {
    //private final String PASSWORD = "TGdd7EDby83jdAC";

    @DataProvider(name="getUsers")
    public Object[][] getUsers(){
        return new Object[][]{
                {"vidko@test.com","123abc", 8335, "Successful login!"},
                {"vidko.","123abc", 8335, "Successful login!"},
                {"p0li0m","TGdd7EDby83jdAC", 5508, "Successful login!"},
                {"dbsdhsh","sdhshs", 8333, "Successful login!"}
                };
    }

    @DataProvider(name="errorMessages")
    public Object[][] errorMessages(){
        return new Object[][]{
                // username
                {"blasfsafasfabla","asfsaf", "User not found"},
                {"dbsdhsh","asdasfsaf", "Ivalid password"},
                {"dbsdhsh","", "Password cannot be empty"},
                // email
                {"blasfsafasfabla@tezt.xom","asfsaf", "User not found"},
                {"blasfsafasfabla@.xom","asfsaf", "User not found"},
                {"blasfsafasfabla@","asfsaf", "User not found"},
                {"vidko@test.com","asdasfsaf", "Ivalid password"},
                {"viasfasfasfsadko@teafasfasfst.com","", "Password cannot be empty"},
                // valid for both email and username
                {"","TGdd7EDby83jdAC", "UsernameOrEmail cannot be empty"},
                {"","", "UsernameOrEmail cannot be empty"},
        };
    }

    @Test(dataProvider = "getUsers")
    public void LoginUser(String username, String password, int userId, String signInMessage){
        WebDriver webDriver = getDriver();
        // initial page objects definition
        LoginPage login = new LoginPage(webDriver);
        ProfilePage profile = new ProfilePage(webDriver);
        Header header = new Header(webDriver);
        HomePage home = new HomePage(webDriver);

        login.navigateTo();

        // validate initial loading of page
        Assert.assertTrue(login.isUrlLoaded(),"The login page is not loaded");

        // validate the login form is loaded
        Assert.assertEquals(login.getSignInElementText(),"Sign in", "Login form is not loaded");

        // fill login form
        login.populatePassword(password);
        login.populateUsername(username);
        login.clickSignIn();

        // check the sing in message
        login.onSignInMessage(signInMessage);

        // validate home page is loaded after login
        Assert.assertTrue(home.isUrlLoaded(), "Home page is not loaded");

        // navigate to profile page
        header.clickProfileLinkWithHandle();

        // validate profile page is loaded
        Assert.assertTrue(profile.isUrlLoaded(userId),"The user profile page is not loaded");

        // validate username is correct
        Assert.assertTrue(profile.isUsernameAsExpected(username), "The username is not as expected");
    }

    @Test(dataProvider = "errorMessages")
    //@Parameters({"username","password","message"})
    //@Test
    public void LoginErrorMessages(String username, String password, String signInMessageExpected){
        WebDriver webDriver = getDriver();
        // initial page objects definition
        LoginPage login = new LoginPage(webDriver);

        login.navigateTo();

        // validate initial loading of page
        Assert.assertTrue(login.isUrlLoaded(),"The login page is not loaded");

        // validate the login form is loaded
        Assert.assertEquals(login.getSignInElementText(),"Sign in", "Login form is not loaded");

        // fill login form
        login.populatePassword(password);
        login.populateUsername(username);
        login.clickSignIn();

        // check the sing in message
        String singInMessageActual = login.getSignInMessage();

        // Final validation of the test
        Assert.assertEquals(singInMessageActual, signInMessageExpected, "Sing in error message is not as expected");
    }

    @Test
    public void LoginUserPathVerification() {
        WebDriver webDriver = getDriver();
        // initial page objects definition
        LoginPage login = new LoginPage(webDriver);
        Header header = new Header(webDriver);
        HomePage home = new HomePage(webDriver);

        home.navigateTo();
        Assert.assertTrue(home.isUrlLoaded(),"The home page is not loaded");

        header.clickLoginLinkWithHandle();
        // validate initial loading of page
        Assert.assertTrue(login.isUrlLoaded(),"The login page is not loaded");

        // validate the login form is loaded
        Assert.assertEquals(login.getSignInElementText(),"Sign in", "Login form is not loaded");
    }
}
