package page.factory;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Header extends NavigationAndURLValidation{
    private final WebDriver webDriver;
    @FindBy(id = "nav-link-profile")
    private WebElement profileLink;
    @FindBy(id = "nav-link-login")
    private WebElement loginLink;
    @FindBy(id = "nav-link-home")
    private WebElement homeLink;
    @FindBy(id = "nav-link-new-post")
    private WebElement newPostLink;
    @FindBy(id = "search-bar")
    private WebElement searchBar;
    @FindBy(xpath = "//a[contains(@class, 'post-user')]")
    private List<WebElement> searchBarResults;



    public Header(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickProfileLink(){
        WebDriverWait profilePageLinkWait = new WebDriverWait(this.webDriver, Duration.ofSeconds(3));
        profilePageLinkWait.until(ExpectedConditions.elementToBeClickable(this.profileLink));
        this.profileLink.click();
    }

    public void clickLoginLink(){
        WebDriverWait loginPageLinkWait = new WebDriverWait(this.webDriver, Duration.ofSeconds(3));
        loginPageLinkWait.until(ExpectedConditions.elementToBeClickable(this.loginLink));
        this.loginLink.click();
    }

    public void clickHomeLink(){
        WebDriverWait homePageLinkWait = new WebDriverWait(this.webDriver, Duration.ofSeconds(3));
        homePageLinkWait.until(ExpectedConditions.elementToBeClickable(this.homeLink));
        this.homeLink.click();
    }

    public void findUserFromProfilePage(String username){
        searchBar.click();
        searchBar.sendKeys(username);
    }

    public boolean validateExactMatchingUsername(String username){
        boolean isUsernameMatching = false;
        for(WebElement webElement:searchBarResults){
             if(username.equals(webElement.getText())){
                 isUsernameMatching = true;
                 break;
             }
             else {
                 return false;
             }
        }
       return isUsernameMatching;
    }

    public void clickHomeLinkWithHandle(){
        waitAndClick(this.homeLink);
    }
    public void clickProfileLinkWithHandle(){
        waitAndClick(this.profileLink);
    }
    public void clickLoginLinkWithHandle(){
        waitAndClick(this.loginLink);
    }
    public void clickNewPostLinkWithHandle(){
        waitAndClick(this.newPostLink);
    }

    private void waitAndClick(WebElement element){
        try {
            WebDriverWait pageLinkWait = new WebDriverWait(this.webDriver, Duration.ofSeconds(3));
            pageLinkWait.until(ExpectedConditions.elementToBeClickable(element));
        }catch(TimeoutException exception){
            Assert.fail("Header navigation link is not found. Inner exception: " + exception);
        }
        element.click();
    }
}
