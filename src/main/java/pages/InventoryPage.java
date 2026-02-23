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
    private By burgerMenuButton = By.id("react-burger-menu-btn");
    private By logoutButton = By.id("logout_sidebar_link");
    private By shoppingCart = By.id("shopping_cart_container");
    private By inventoryItemName = By.className("inventory_item_name");
    private By inventoryItemPrice = By.className("inventory_item_price");
    private By shoppingCartBadge = By.className("shopping_cart_badge");

    private By sortAscenAtoZ = By.xpath("//option[contains(@value, 'az')]");
    private By sortDescZtoA = By.xpath("//option[contains(@value, 'za')]");
    private By sortPriceLowToHigh = By.xpath("//option[contains(@value, 'lohi')]");
    private By sortPriceHighToLow = By.xpath("//option[contains(@value, 'hilo')]");


    //elements containers actions
    private By addToCartButton(String item) {
        return By.id(item);
    }
    private By removeFromCartButton(String item) {
        return By.id(item);
    }
    private By clickOnItem(String item) {
        return By.xpath("//div[contains(@class,'inventory_item_name')][contains(.,\""+item+"\")]");
    }


    //actions
    public String getHeaderText(){
        return driver.findElement(header).getText();
    }
    public boolean isBurgerMenuButtonDisplayed(){
        WaitUtils.visibilityOfElementLocated(burgerMenuButton);
        return driver.findElement(burgerMenuButton).isDisplayed();
    }
    public void clickOnBurgerMenuButton(){
        driver.findElement(burgerMenuButton).click();
    }
    public boolean isLogoutDisplayed(){
        WaitUtils.visibilityOfElementLocated(logoutButton);
        return driver.findElement(logoutButton).isDisplayed();
    }
    public void clickLogoutButton(){
        driver.findElement(logoutButton).click();
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
    public List<String> getInventoryItemPrice() {
        List<WebElement> classInventoryItems = driver.findElements(inventoryItemPrice);
        List<String> itemsPrice = new ArrayList<>();
        for (WebElement itemPrice : classInventoryItems){
            itemsPrice.add(itemPrice.getText());
        }
        return itemsPrice;
    }


    public void addItemToCart(String item) {
        WaitUtils.elementToBeClickable(addToCartButton(item)).click();
    }
    public void removeItemFromCart(String item) throws InterruptedException{

        WaitUtils.elementToBeClickable(removeFromCartButton(item));
        WaitUtils.textToBePresentInElementLocated(removeFromCartButton(item), "Remove");

        try {
            driver.findElement(removeFromCartButton(item)).click();
        }catch (Exception e){
            System.out.println("click failed: " + e.getMessage());
            throw e;
        }
    }


    //isDisplayed
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
        List<WebElement> shoppingCartItems = driver.findElements(shoppingCartBadge);
        return !shoppingCartItems.isEmpty() && shoppingCartItems.get(0).isDisplayed();
    }

    //waits
    public void waitCartBadgeVisible() {
        WaitUtils.visibilityOfElementLocated(shoppingCartBadge);
    }
    public void waitCartBadgeGone() {
        WaitUtils.invisibilityOfElementLocated(shoppingCartBadge);
    }
    public void waitRemoveButtonDisplayed(String item) {
        WaitUtils.visibilityOfElementLocated(removeFromCartButton(item));
    }

    //click on buttons
    public void clickOnRandomItem(String item) {
        driver.findElement(clickOnItem(item)).click();
    }

    public void clickOnShoppingCart(){
        driver.findElement(shoppingCart).click();
    }

    //sort elements
    public void sortItemsAtoZ () {
        driver.findElement(sortAscenAtoZ).click();
    }
    public void sortItemsZtoA() {
        driver.findElement(sortDescZtoA).click();
    }
    public void sortItemsByPriceLowToHigh() {
        driver.findElement(sortPriceLowToHigh).click();
    }
    public void sortItemsByPriceHighToLow() {
        driver.findElement(sortPriceHighToLow).click();
    }
}
