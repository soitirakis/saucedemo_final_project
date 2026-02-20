package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryItemPage extends BasePage{
    public InventoryItemPage(WebDriver driver) {
        super(driver);
    }

    //elements
    private By backToProductsButton = By.id("back-to-products");
    private By inventoryItemName = By.xpath("//div[@data-test='inventory-item-name']");
    private By inventoryItemDescription = By.xpath("//div[@data-test='inventory-item-desc']");
    private By inventoryItemPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By addToCartButton = By.xpath("//button[@id='add-to-cart']");
    private By removeFromCartButton = By.id("remove");
    private By shoppingCart = By.xpath("//a[@data-test='shopping-cart-link']");

    //actions
    public boolean isBackToProductsButtonDisplayed() {
        return driver.findElement(backToProductsButton).isDisplayed();
    }
    public String getInventoryItemName() {
        return driver.findElement(inventoryItemName).getText();
    }
    public String getInventoryItemDescription() {
        return driver.findElement(inventoryItemDescription).getText();
    }
    public String getInventoryItemPrice() {
        return driver.findElement(inventoryItemPrice).getText();
    }
    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }
    public void removeFromCart() {
        driver.findElement(removeFromCartButton).click();
    }
    public void clickOnShoppingCartButton() {
        driver.findElement(shoppingCart).click();
    }
}
