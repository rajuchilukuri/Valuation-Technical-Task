package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

    protected static WebDriver driver = createBrowser();

    protected static WebDriver createBrowser(){
        String browserType = System.getProperties().getProperty("browser", "chrome");
        switch (browserType){
            case "chrome":
                return createChromeDriver();
            case "firefox":
                return createFirefoxDriver();
            case "safari":
                return createSafariDriver();
            default:
                throw new RuntimeException("browserType not found");

        }
    }

    private static WebDriver createChromeDriver(){
        String headLess = System.getProperties().getProperty("headLess","false");
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(Boolean.parseBoolean(headLess));
        driver = new ChromeDriver(chromeOptions);
        return driver;
    }

    private static WebDriver createFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        driver = new FirefoxDriver(firefoxOptions);
        return driver;
    }

    private static WebDriver createSafariDriver(){
        driver = new SafariDriver();
        return driver;
    }

}
