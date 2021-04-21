package base;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTestCase {
    protected static WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/chromedriver");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setCapability("browserVersion", "67");
//        chromeOptions.setCapability("platformName", "Windows XP");
//        chromeOptions.addArguments("test-type");
//        chromeOptions.addArguments("--disable-extensions");
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://jupiter.cloud.planittesting.com");

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Start Shopping »")));
    }

    @org.junit.After
    public void tearDown() {
        driver.quit();
    }
}
