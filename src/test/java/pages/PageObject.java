package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class PageObject {
    protected final WebDriver driver;

    public static final By linkHome    = By.xpath("//a[contains(text(),'Home')]");
    public static final By linkLogin   = By.xpath("//a[contains(text(),'Login')]");
    public static final By linkSignUp  = By.xpath("//a[contains(text(),'Sign Up')]");
    public static final By linkProfile = By.xpath("//a[contains(text(),'Profile')]");
    public static final By linkLogout  = By.xpath("//a[contains(text(),'Logout')]");

    public PageObject(WebDriver driver){
        this.driver = driver;
    }

    public abstract PageObject checkPageElements(boolean authenticated);

    public PageObject goToURL(String url){
        driver.get(url);
        return this;
    }

    public PageObject clickOnElement(By byLoc){
        driver.findElement(byLoc).click();
        return this;
    }

    public PageObject checkText(String text){
        driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        return this;
    }

    public PageObject checkURL(String url){
        Assertions.assertEquals(driver.getCurrentUrl(), url);
        return this;
    }

    public void closeTab(){
        driver.close();
    }
}
