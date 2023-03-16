package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutYourCarPage extends BasePage{

    public AboutYourCarPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='sc-kgTSHT fIwRFX']/p[2]")
    private WebElement checkMakeAndModelDetail;

    @FindBy(xpath = "//div[contains(text(),'Our history checks show the MOT has expired.')]")
    private WebElement motCheckErrorMessage;

    @FindBy(xpath = "//div[contains(text(),'Sorry')]")
    private WebElement sorryCheckErrorMessage;

    public WebElement isMakeModelDisplayed() {
        return checkMakeAndModelDetail;
    }
    public WebElement isMotErrorDisplayed() {
        return motCheckErrorMessage;
    }

    public String getMakeAndModelDetail() {
        return checkMakeAndModelDetail.getText();
    }

    public String getMotCheckErrorMessage() {
        motCheckErrorMessage.isDisplayed();
        return motCheckErrorMessage.getText();
    }

    public String getSorryErrorMesage() {
        sorryCheckErrorMessage.isDisplayed();
        return sorryCheckErrorMessage.getText();
    }



}
