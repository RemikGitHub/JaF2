package config;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import util.Data;

public class TestConfig {

    @Before
    public void setUp() {
        WebDriver driver = WebDriverSingleton.getInstance();

        //driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        driver.get(Data.URL);
    }

    @After
    public void tearDown() {
        WebDriverSingleton.quit();
    }
}
