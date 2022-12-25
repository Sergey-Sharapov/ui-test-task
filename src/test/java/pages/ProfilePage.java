package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends PageObject {
    public static final By greeting = By.xpath("//h1[contains(text(),'Welcome, ')]");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage load() {
        driver.get("http://localhost:5000/profile");
        return this;
    }

    @Override
    public ProfilePage checkPageElements(boolean authenticated){
        driver.findElement(greeting);
        driver.findElement(linkHome);
        driver.findElement(linkProfile);
        driver.findElement(linkLogout);
        return this;
    }
}
