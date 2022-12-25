package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageObject {
    public static final By header             = By.xpath("//h3[contains(text(),'Login')]");
    public static final By inputEmail         = By.xpath("//input[@name='email']");
    public static final By inputPassword      = By.xpath("//input[@name='password']");
    public static final By checkboxRememberMe = By.xpath("//label[contains(string(),'Remember me')]/input");
    public static final By buttonLogin        = By.xpath("//button[contains(text(),'Login')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage load() {
        driver.get("http://localhost:5000/login");
        return this;
    }

    public LoginPage checkPageElements(boolean authenticated){
        driver.findElement(header);
        driver.findElement(inputEmail);
        driver.findElement(inputPassword);
        driver.findElement(checkboxRememberMe);
        driver.findElement(buttonLogin);
        driver.findElement(linkHome);
        if(authenticated) {
            driver.findElement(linkProfile);
            driver.findElement(linkLogout);
        }else {
            driver.findElement(linkLogin);
            driver.findElement(linkSignUp);
        }
        return this;
    }

    public LoginPage inputLoginData(String email, String pswd, boolean rememberMe) {
        driver.findElement(inputEmail).sendKeys(email);
        driver.findElement(inputPassword).sendKeys(pswd);
        if(rememberMe)
            driver.findElement(checkboxRememberMe).click();
        return new LoginPage(driver);
    }
}
