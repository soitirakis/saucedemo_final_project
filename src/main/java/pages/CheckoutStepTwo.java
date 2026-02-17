package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwo extends BasePage {
    public CheckoutStepTwo(WebDriver driver) {
        super(driver);
    }

    //elements
    private By header = By.className("title");
    private By itemName = By.className("inventory_item_name");
    private By itemDescription = By.className("inventory_item_desc");
    private By itemPrice = By.className("inventory_item_price");
    private By tax = By.className("summary_tax_label");
    private By totalSum = By.className("summary_total_label");
    private By finishButton = By.id("finish");

    //actions
    public String getHeader(){
        return driver.findElement(header).getText();
    }
    public String getItemName(){
        return driver.findElement(itemName).getText();
    }
    public String getItemDescription(){
        return driver.findElement(itemDescription).getText();
    }
    public String getItemPrice(){
        return driver.findElement(itemPrice).getText();
    }
    public String getTax(){
        return driver.findElement(tax).getText();
    }
    public String getTotalSum(){
        return driver.findElement(totalSum).getText();
    }
    public void clickFinishButton(){
        driver.findElement(finishButton).click();
    }

}
