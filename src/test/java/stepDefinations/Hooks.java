package stepDefinations;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import utils.Driver;
import utils.FileReaderManager;

import java.time.Duration;

public class Hooks extends Driver {

    @Before
    public void setUp() throws Throwable{
        if(driver==null){
            createBrowser();
        }
        driver.get(FileReaderManager.getInstance().getConfigFileReader().getUrl());
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()) {
            try {
                System.out.println("Failed Scenario: " + scenario.getName());
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot,"image/png","screenshot");
            } catch (WebDriverException e) {
                throw new WebDriverException(e.getMessage());
            }
        }
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();

    }

}
