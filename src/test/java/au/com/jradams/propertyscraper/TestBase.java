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
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class TestBase {

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Static Fields
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final String DRIVER_PATH = "/home/justin/projects/assets/property_scraper/chromedriver";

    private static ChromeDriverService cdService;

    protected static WebDriver driver;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Instance Fields
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private ConfigModel config = new ConfigModel();

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Pre-Test Method(s)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @BeforeClass
    public static void setupSuite() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

        cdService = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(DRIVER_PATH))
                .usingAnyFreePort()
                .withVerbose(true)
                .build();

        try {
            cdService.start();
        } catch (IOException ioe) {
            System.err.println("Unable to initialize the Chrome Driver. Verify the chromedriver location. " + ioe);
        }

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("detach", true);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Before
    public void setupTest() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            URL configFile = Thread.currentThread().getContextClassLoader().getResource("main.yaml");

            if (configFile != null) {
                config = mapper.readValue(new File(configFile.getPath()), ConfigModel.class);
            }
        } catch (IOException ioe) {
            System.err.println("Exception occurred while reading configuration file. " + ioe);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Post-Test Method(s)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @AfterClass
    public static void tearDown() {
        cdService.stop();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Test Methods
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    public void crawlDomainDotComAu() {
        // Load site
        driver.get("https://www.domain.com.au");
        driver.manage().window().fullscreen();

//        // Populating suburbs
//        String suburb = config.getSuburbs().get(0);
//
//        driver.findElement(By.id("fe-pa-domain-home-typeahead-input")).sendKeys(suburb);
//        driver.findElement(By.id("fe-pa-domain-home-typeahead-item-1")).click();

        for (String suburb : config.getSuburbs()) {
            driver.findElement(By.id("fe-pa-domain-home-typeahead-input")).sendKeys(suburb);
            driver.findElement(By.id("fe-pa-domain-home-typeahead-item-1")).click();
        }

        //--- Filter Form -----------------------------------------------------
        driver.findElement(By.className("css-1ik46rb")).click();

        WebElement minSlider = driver.findElement(By.className("css-1amuulz"));
        WebElement maxSlider = driver.findElement(By.className("css-glyzqy"));

        int counter = 1;
        int sliderWidth = 610;
        int xOffset = (sliderWidth * counter) / 100;

        String minLabelVal = "$" + String.valueOf(config.getMinPrice()).substring(0, 3) + "k";
        String maxLabelVal = "$" + String.valueOf(config.getMaxPrice()).substring(0, 3) + "k";
        String priorStyleVal = driver.findElement(By.className("css-1amuulz")).getCssValue("left");
        String currentStyleVal;

        Actions action = new Actions(driver);

        while (!driver.findElement(By.className("css-1d5ihdx")).getText().contains(minLabelVal)) {
            action.moveToElement(minSlider, xOffset, 1)
                    .clickAndHold()
                    .release()
                    .perform();

            currentStyleVal = driver.findElement(By.className("css-1amuulz")).getCssValue("left");

            if (!priorStyleVal.equals(currentStyleVal)) {
                priorStyleVal = currentStyleVal;
                counter = 1;
                xOffset = (sliderWidth * counter) / 100;
            } else {
                counter++;
                xOffset = (sliderWidth * counter) / 100;
            }
        }

        xOffset = 3;

        while (!driver.findElement(By.className("css-1d5ihdx")).getText().contains(maxLabelVal)) {
            action.moveToElement(maxSlider, -xOffset, 1)
                    .clickAndHold()
                    .release()
                    .perform();

            currentStyleVal = driver.findElement(By.className("css-glyzqy")).getCssValue("left");

            if (!priorStyleVal.equals(currentStyleVal)) {
                priorStyleVal = currentStyleVal;
                xOffset = 3;
            } else {
                xOffset += 3;
            }
        }

        driver.findElement(By.xpath(MessageFormat.format(
                "//div[@class=''css-1m29ivc'']/label[@data-testid=''bedrooms_{0}'']/input", config.getBedrooms()))).click();

        driver.findElement(By.xpath(MessageFormat.format(
                "//div[@class=''css-1m29ivc'']/label[@data-testid=''Bathrooms_{0}'']/input", config.getBathrooms()))).click();

        driver.findElement(By.xpath(MessageFormat.format(
                "//div[@class=''css-1m29ivc'']/label[@data-testid=''Carparks_{0}'']/input", config.getCarparks()))).click();

        driver.findElement(By.className("css-1q4pzp2")).click();

        //--- Property Results ------------------------------------------------

         List<WebElement> properties = driver.findElements(By.xpath("//a[@class='address']"));

         for (WebElement property : properties) {
            property.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));


         }


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
