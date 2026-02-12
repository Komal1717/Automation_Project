package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class LoginPage {
    WebDriver driver ;
    WaitUtils wait;

    public LoginPage(WebDriver driver){

        this.driver = driver;
        wait = new WaitUtils(driver);

    }

    By username = By.id("user-name");
    By password = By.id("password");
    By loginBtn = By.id("login-button");
    By errorMsg = By.xpath("//h3[@data-test='error']");
    By lockedOutMessage = By.xpath("//h3[@data-test='error']");

    public void openApp() {

        driver.get("https://www.saucedemo.com/");

    }

    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        wait.waitForElementClickable(loginBtn);
        driver.findElement(loginBtn).click();




    }


    public String getErrorMessage(){
      wait.waitForElementVisible(errorMsg);
     return driver.findElement(errorMsg).getText();

    }

   public boolean getLockedOutUserMessageDisplayed(){
        wait.waitForElementVisible(lockedOutMessage);
        return driver.findElement(lockedOutMessage).isDisplayed();


   }

   public String getLockedOutUserMessage(){
        wait.waitForElementVisible(lockedOutMessage);
        return driver.findElement(lockedOutMessage).getText();
   }

}