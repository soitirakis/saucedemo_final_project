package pages;

import driver.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        WaitUtils.elementToBeClickable(addToCartButton(item)).click();
        //driver.findElement(addToCartButton(item)).click();
    }
    public void removeItemFromCart(String item) throws InterruptedException{
        System.out.println("Click remove: " +item);

        WaitUtils.elementToBeClickable(removeFromCartButton(item));

        try {
            driver.findElement(removeFromCartButton(item)).click();
        }catch (Exception e){
            System.out.println("click failed: " + e.getMessage());
            throw e;
        }

        //trying to catch the exceptions if unable to click
       /* WebElement el = WaitUtils.elementToBeClickable(removeFromCartButton(item));

        System.out.println("REMOVE id: " + item);
        System.out.println("Displayed: " + el.isDisplayed());
        System.out.println("Enabled: " + el.isEnabled());
        System.out.println("text: " + el.getText());

        try {
            el.click();
        }catch (Exception e){
            System.out.println("Click failed: " + e.getClass() + "->" + e.getMessage());
            throw e;
        }*/

        //WaitUtils.stalenessOf(removeFromCartButton(item));
        System.out.println("Clicked remove");
        //driver.findElement(removeFromCartButton(item)).click();

    }
    public String getRemoveButtonText(String item) {
        return driver.findElement(removeFromCartButton(item)).getText();
    }
    public boolean addItemToCartButtonDisplayed(String item) {
        try {
            WaitUtils.visibilityOfElementLocated(addToCartButton(item)).isDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public boolean removeFromCartButtonDisplayed(String item) {
        try {
            WaitUtils.visibilityOfElementLocated(removeFromCartButton(item)).isDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
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
    public void waitRemoveButtonDisplayed(String item) {
        WaitUtils.visibilityOfElementLocated(removeFromCartButton(item));
    }
}
