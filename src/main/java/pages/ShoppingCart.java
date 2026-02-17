package pages;

import driver.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCart extends BasePage{
    public ShoppingCart(WebDriver driver) {
        super(driver);
    }

    //elements
    private By yourCartTitle = By.xpath("//span[contains(@data-test,'title')][contains(.,'Your Cart')]");
    private By itemInventoryName = By.className("inventory_item_name");
    private By itemDescription = By.className("inventory_item_desc");
    private By itemPrice = By.className("inventory_item_price");

    private By checkoutButton = By.id("checkout");

    //elements containers actions
    private By removeButton (String item) {
        return By.id(item);
    }

    //actions // isDisplayed
    public boolean isYourCartTitleDisplayed() {
        return driver.findElement(yourCartTitle).isDisplayed();
    }
    public boolean setCheckoutButtonDisplayed() {
        return driver.findElement(checkoutButton).isDisplayed();
    }
    public boolean isRemoveButtonDisplayed(String item){
        List<WebElement> shoppingCartItems = driver.findElements(removeButton(item));
        return !shoppingCartItems.isEmpty() && shoppingCartItems.get(0).isDisplayed();
    }

    public void  clickOnCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }
    public void clickOnRemoveButton(String item) {
        driver.findElement(removeButton(item)).click();
    }

    public String getItemTitle() {
        return driver.findElement(itemInventoryName).getText();
    }

    public String getItemDescription() {
        return driver.findElement(itemDescription).getText();
    }
    public String getItemPrice() {
        return driver.findElement(itemPrice).getText();
    }

    //waits
    public void waitRemoveButtonGone(String item) {
        WaitUtils.invisibilityOfElementLocated(removeButton(item));
    }
}
