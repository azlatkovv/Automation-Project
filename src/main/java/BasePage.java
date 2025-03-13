import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class BasePage {
 public static WebDriver webDriver;

 public void setWebDriver(WebDriver webDriver){
     BasePage.webDriver =  webDriver;
 }

    protected WebElement find(By locator){
     return webDriver.findElement(locator);
    }

    protected void click(By locator){
     find(locator).click();
    }

    protected void setText(By locator, String text){
        find(locator).sendKeys(text);
    }


}

