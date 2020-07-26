package au.com.jradams.propertyscraper;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class TestBase {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/justin/projects/assets/property_scraper/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void crawlDomainDotComAu() {

    }

    @Test
    public void crawlRealEstateDotComAu() {
        driver.get("https://www.domain.com.au");
    }

    @Test
    public void crawlPropertyDotComAu() {

    }

    @Test
    public void crawlHomelyDotComAu() {

    }
}
