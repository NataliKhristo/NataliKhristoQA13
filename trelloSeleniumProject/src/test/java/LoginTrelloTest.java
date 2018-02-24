import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class LoginTrelloTest {
    FirefoxDriver wd;
    // openSite
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void loginTrelloTest() {
        openSite();
        clickLogInButton();
        enterUserName("elena.telran@yahoo.com");
        enterPassword("12345.com");
        confirmLogInButton();
    }

    @Test
    public void loginTrelloTestNotValid() {
        openSite();
        clickLogInButton();
        enterUserName("1");
        enterPassword("12345.com");
        confirmLogInButton();
    }
    @Test
    public void loginTrelloemtifieldTest() {
        openSite();
        clickLogInButton();
        enterUserName("");
        enterPassword("");
        confirmLogInButton();
    }

    private void confirmLogInButton() {
        clickLoginButton(By.id("login"));
    }

    private void enterPassword(String pwd) {
        clickLoginButton(By.id("password"));
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys(pwd);
    }

    private void enterUserName(String userName) {
        clickLoginButton(By.id("user"));
        enterUserName("user", userName);
    }

    private void clickLogInButton() {
        clickLoginButton(By.linkText("Log In"));
    }

    private void openSite() {
        wd.get("https://trello.com");
    }

    private void enterUserName(String user, String s) {
        wd.findElement(By.id(user)).clear();
        wd.findElement(By.id(user)).sendKeys(s);
    }

    private void clickLoginButton(By log_in) {
        wd.findElement(log_in).click();
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
