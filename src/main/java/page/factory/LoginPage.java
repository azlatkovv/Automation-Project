package page.factory;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

 private By usernameField= By.id("//*[@id = 'defaultLoginFormUsername']");
 private By passwordField= By.id("//*[@id = 'defaultLoginFormPassword']");
 private By signInButton = By.id("nav-link-login");
 private By message ;



 /* public String getSignInMessage(){
  String singInMessageText = "";
  WebDriverWait signInMessageWait = new WebDriverWait(this.webDriver, Duration.ofMillis(1500));
  try {
   signInMessageWait.until(ExpectedConditions.visibilityOf(this.singInMessage));
   singInMessageText = this.singInMessage.getText();
  }catch(TimeoutException exception){
   System.out.println("Sign in message is not visible. Inner exception: " + exception);
   return singInMessageText;
  }
  return singInMessageText;
 }*/

}
