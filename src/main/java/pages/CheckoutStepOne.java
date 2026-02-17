package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testdata.classes.CheckoutInformation;

public class CheckoutStepOne extends BasePage {
    public CheckoutStepOne(WebDriver driver) {
        super(driver);
    }
    //elements
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By zipCodeInput = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By errorMessage = By.xpath("//h3[contains(@data-test, 'error')]");
    private By checkoutInformationTitle = By.xpath("//span[contains(@data-test, 'title')][contains(.,'Checkout: Your Information')]");

    //actions
    public boolean getCheckoutInformationTitleDisplayed() {
        return driver.findElement(checkoutInformationTitle).isDisplayed();
    }
    public String getErrorMessage() throws InterruptedException {
        return driver.findElement(errorMessage).getText();
    }

    public void addFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }
    public void addLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }
    public void addZipCode(String zipCode) {
        driver.findElement(zipCodeInput).sendKeys(zipCode);
    }
    public void continueButtonClick() {
        driver.findElement(continueButton).click();
    }

    public void checkoutInformationContinueButton(CheckoutInformation userInfo) {
        addFirstName(userInfo.getFirstName());
        addLastName(userInfo.getLastName());
        addZipCode(userInfo.getZipCode());
        continueButtonClick();
    }
}
