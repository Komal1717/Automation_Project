package tests;


import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
;

public class LoginTest extends BaseTest {


    LoginPage loginPage;

@BeforeMethod
    public void openApplication(){
        loginPage = new LoginPage(driver);
            loginPage.openApp();



    }

    @AfterMethod
    public void refreshBrowser(){
    driver.navigate().refresh();
    }


    @Test
    public void validLoginTest(){
        loginPage.login("standard_user" , "secret_sauce");
        Boolean url = driver.getCurrentUrl().contains("inventory");
        Assert.assertTrue(url, "user not redirected to inventory page");
    }

    @Test(priority = 2)
    public void invalidPasswordTest(){
    loginPage.login("problem_user" , "wrong_pass");
    String error = loginPage.getErrorMessage();
    Assert.assertTrue(error.contains("Username and password do not match "));
    }

   @Test(priority = 3)
    public void lockedOutUserTest(){
    loginPage.login("locked_out_user" , "secret_sauce");
    Assert.assertTrue(loginPage.getLockedOutUserMessageDisplayed(),
            "Locked user error message not displayed");
    String error = loginPage.getLockedOutUserMessage();
    Assert.assertTrue(error.contains("locked out"),
            "Incorrect locked user error message");


    }
@Test(dataProvider = "getLoginData")
public void loginDataProvider(String username, String password, String expectedResult){
    loginPage.login(username , password);

    if(expectedResult.equals("success")){
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

    }

    else if(expectedResult.equals("error")){
        String errors = loginPage.getErrorMessage();
        Assert.assertTrue(errors.length() > 0 ,
                "Error message not displayed for invalid login");
    }

    }



@DataProvider(name = "getLoginData")
public Object[][] getLoginData(){

    return new Object[][]{

            {"standard_user", "secret_sauce", "success"},
            {"standard_user", "wrong_pass", "error"},
            {"locked_out_user", "secret_sauce", "error"},
            {"", "secret_sauce", "error"},
            {"standard_user", "", "error"}


    };

}



}

//gh repo clone Komal1717/Automation_Projectgi