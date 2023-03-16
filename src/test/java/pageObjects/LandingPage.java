package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(),'Accept All')]")
    private WebElement acceptAllCookies;

    @FindBy(id ="vrm")
    private WebElement registrationInputBox;

    @FindBy(xpath = "//*[@type='submit' and @class='sc-mqi3p7-0 iPGgoA']")
    private WebElement startValuationButton;


    public void clickAcceptAllCookies() {
        acceptAllCookies.isDisplayed();
        acceptAllCookies.click();
    }

    public void setValueRegistrationInputBox(String regNo) {
        registrationInputBox.sendKeys(regNo);
    }

    public void clickStartValuationButton() {
        startValuationButton.isEnabled();
        startValuationButton.click();
    }
    public void clearRegNoBox() {
        registrationInputBox.clear();
    }
}
