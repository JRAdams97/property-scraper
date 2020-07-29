package au.com.jradams.propertyscraper;

import au.com.jradams.propertyscraper.model.ConfigModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class TestBase {

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Static Fields
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    protected static WebDriver driver;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Instance Fields
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private ConfigModel config;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Pre-Test Method(s)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @BeforeClass
    public static void setupSuite() {
        System.setProperty("webdriver.chrome.driver", "/home/justin/projects/assets/property_scraper/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Before
    public void setupTest() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            URL configFile = Thread.currentThread().getContextClassLoader().getResource("main.yaml");
            ConfigModel config;

            if (configFile != null) {
                config = mapper.readValue(new File(configFile.getPath()), ConfigModel.class);

                System.out.println(config);
            }
        } catch (IOException ioe) {
            System.err.println("Exception occurred while reading configuration file. " + ioe);
        }
    }



    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void crawlDomainDotComAu() {
        driver.get("https://www.domain.com.au");
    }

    @Test
    public void crawlRealEstateDotComAu() {
    }

    @Test
    public void crawlPropertyDotComAu() {

    }

    @Test
    public void crawlHomelyDotComAu() {

    }
}
