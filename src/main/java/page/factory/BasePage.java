package page.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


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

    public void sum(int a, int b){
        System.out.println(a+b);
    }

}

