package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCart extends BasePage{
    public ShoppingCart(WebDriver driver) {
        super(driver);
    }

    //elements
    private By yourCartTitle = By.xpath("//span[contains(@data-test,'title')][contains(.,'Your Cart')]");
    private By itemInventoryName = By.xpath("//div[contains(@data-test,'inventory-item-name')][contains(.,'')]");

    private By checkoutButton = By.id("checkout");

    //actions
    public boolean isYourCartTitleDisplayed() {
        return driver.findElement(yourCartTitle).isDisplayed();
    }
    public void  clickOnCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }

    //TODO
    //check item details match the clicked item details
}
