package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends PageObject {
    public static final By header        = By.xpath("//h3[contains(text(),'Sign Up')]");
    public static final By inputEmail    = By.xpath("//input[@name='email']");
    public static final By inputName     = By.xpath("//input[@name='name']");
    public static final By inputPassword = By.xpath("//input[@name='password']");
    public static final By buttonSignUp  = By.xpath("//button[contains(text(),'Sign Up')]");

    public SignUpPage(WebDriver driver) { super(driver); }

    public SignUpPage load() {
       driver.get("http://localhost:5000/signup");
       return this;
    }

    public SignUpPage checkPageElements(boolean authenticated){
        driver.findElement(header);
        driver.findElement(inputEmail);
        driver.findElement(inputName);
        driver.findElement(inputPassword);
        driver.findElement(buttonSignUp);
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

    public SignUpPage inputSignUpData(String email, String pswd, String name) {
        driver.findElement(inputEmail).sendKeys(email);
        driver.findElement(inputPassword).sendKeys(pswd);
        driver.findElement(inputName).sendKeys(name);
        return new SignUpPage(driver);
    }
}
