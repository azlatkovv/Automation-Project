package BasePageTests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class commonMethods {

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



    }



