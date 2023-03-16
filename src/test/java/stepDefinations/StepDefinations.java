package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.AboutYourCarPage;
import pageObjects.LandingPage;
import utils.Driver;
import utils.FileReaderManager;
import utils.Wait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StepDefinations extends Driver {

    LandingPage landingPage = new LandingPage(driver);
    AboutYourCarPage aboutYourCarPage = new AboutYourCarPage(driver);
    List<String> getRegistrationNum = new ArrayList<>();



    @Given("Read data from input text file")
    public void Read_data_from_input_text_file() throws IOException {
        getRegistrationNum = FileReaderManager.getInstance().getDataFile().readInputFile();
    }

    @When("Use input data in the application and compare vechicle details with output data file")
    public void Read_output_text_file_and_validate_vehicle_details() throws IOException {
        landingPage.clickAcceptAllCookies();
        for (String num : getRegistrationNum) {
            landingPage.setValueRegistrationInputBox(num);
            landingPage.clickStartValuationButton();
            String expectedValuation = FileReaderManager.getInstance().getDataFile().readOutputFile(num);
            boolean makeModelPresent = Wait.isElementPresent(driver, aboutYourCarPage.isMakeModelDisplayed());
            boolean motErrorPresent = Wait.isElementPresent(driver, aboutYourCarPage.isMotErrorDisplayed());
            if (makeModelPresent) {
                String actualValuation = aboutYourCarPage.getMakeAndModelDetail();
                actualValuation = actualValuation.replaceAll("Make/model: ", "");
                System.out.println(actualValuation);
                expectedValuation = expectedValuation.replaceAll(",", " ");
                System.out.println(expectedValuation);
                if (expectedValuation.equalsIgnoreCase(actualValuation)) {
                    System.out.println(num + " Expected and Actual values are matched");
                } else {
                    System.err.println(num + " Expected and Actual values are not matched");
                }
            } else {
                if (motErrorPresent) {
                    String actualVlaue = aboutYourCarPage.getMotCheckErrorMessage();
                    System.err.println(num + " " + actualVlaue);
                } else {
                    String actualValue = aboutYourCarPage.getSorryErrorMesage();
                    System.err.println(num + " " + actualValue);
                }
            }

            driver.get(FileReaderManager.getInstance().getConfigFileReader().getUrl());
            landingPage.clearRegNoBox();
        }
    }
}
