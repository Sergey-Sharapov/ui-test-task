package scripts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public abstract class AbstractTest {
    protected final WebDriver driver = new ChromeDriver();

    @BeforeAll
    public void init(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @AfterEach
    public void close(){
        driver.close();
    }
}
