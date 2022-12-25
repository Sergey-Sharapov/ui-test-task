package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageObject {
    public static final By header = By.xpath("//h1[contains(text(),'Test home page')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load() {
        driver.get("http://localhost:5000/");
        return this;
    }

    public HomePage checkPageElements(boolean authenticated){
        driver.findElement(header);
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
}
