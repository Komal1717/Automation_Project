package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

        protected WebDriver driver;

        @BeforeClass
        public void setup() {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--disable-notifications");
//            options.addArguments("--disable-save");
//            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-extensions");
            options.addArguments("--incognito");
            options.addArguments("--user-data-dir=/tmp/temporary-profile");

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentails_enable_service", false); // credential enable service
            prefs.put("profile.password_manager_enabled", false); // no saved pass suggestion
            prefs.put("autofill.profile_enabled" , false);//autofill
            prefs.put("autofill.credit_card_enabled" , false);//save card details

            options.setExperimentalOption("prefs" , prefs);

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }

        @AfterClass
        public void tearDown() {
            driver.quit();
        }





    }



