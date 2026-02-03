package pages;

import driver.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage{
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    //elements
    private By header = By.className("app_logo");
    private By logoutButton = By.id("logout_sidebar_link");
    private By shoppingCart = By.id("shopping_cart_container");
    private By inventoryItemName = By.className("inventory_item_name");
    private By shoppingCartBadge = By.className("shopping_cart_badge");


    //elements containers actions
    private By addToCartButton(String item) {
        return By.id(item);
    }
    private By removeFromCartButton(String item) {
        return By.id(item);
    }



    //actions
    public String getHeaderText(){
        return driver.findElement(header).getText();
    }
    public boolean isLogoutDisplayed(){
        return driver.findElement(logoutButton).isDisplayed();
    }
    public boolean isShoppingCartDisplayed(){
        return driver.findElement(shoppingCart).isDisplayed();
    }
    public List<String> getInventoryListItems() {
        List<WebElement> classInventoryItems = driver.findElements(inventoryItemName);
        List<String> items = new ArrayList<>();
        for(WebElement item : classInventoryItems){
            items.add(item.getText());
        }
        return items;
    }
    public void addItemToCart(String item) {
        driver.findElement(addToCartButton(item)).click();
    }
    public void removeItemFromCart(String item) {
        driver.findElement(removeFromCartButton(item)).click();
    }
    public boolean addItemToCartButtonDisplayed(String item) {
        try {
            WaitUtils.visibilityOfElementLocated(addToCartButton(item)).isDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
        //return driver.findElement(addToCartButton(item)).isDisplayed();
    }
    public boolean removeFromCartButtonDisplayed(String item) {
        try {
            WaitUtils.visibilityOfElementLocated(removeFromCartButton(item)).isDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
        //return  driver.findElement(removeFromCartButton(item)).isDisplayed();
    }
    public boolean shoppingCartBadgeDisplayed() {
        List<WebElement> shoppingCartItems = driver.findElements(shoppingCart);
        return !shoppingCartItems.isEmpty() && shoppingCartItems.get(0).isDisplayed();
    }
    public void waitCartBadgeVisible() {
        WaitUtils.visibilityOfElementLocated(shoppingCartBadge);
    }
    public void waitCartBadgeGone() {
        WaitUtils.invisibilityOfElementLocated(shoppingCartBadge);
    }
}
