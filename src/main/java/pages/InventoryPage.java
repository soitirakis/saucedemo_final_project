package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage{
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    //elements
    private By header = By.className("app_logo");
    private By logoutButton = By.id("logout_sidebar_link");
    private By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private By removeFromCartButton = By.id("remove-sauce-labs-backpack");

    //actions
    public String getHeaderText(){
        return driver.findElement(header).getText();
    }
    public boolean isLogoutDisplayed(){
        return driver.findElement(logoutButton).isDisplayed();
    }

}
