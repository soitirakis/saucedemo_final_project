package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutComplete extends BasePage{
    public CheckoutComplete(WebDriver driver) {
        super(driver);
    }

    //elements
    private By pageHeader = By.className("title");
    private By orderHeader = By.xpath("//h2");
    private By confirmationText = By.className("complete-text");
    private By backHomeButton = By.id("back-to-products");

    //actions
    public String getPageHeader() {
        return driver.findElement(pageHeader).getText();
    }
    public String getOrderHeader() {
        return driver.findElement(orderHeader).getText();
    }
    public String getConfirmationText() {
        return driver.findElement(confirmationText).getText();
    }
    public boolean backHomeButtonDisplayed() {
        return driver.findElement(backHomeButton).isDisplayed();
    }
}
