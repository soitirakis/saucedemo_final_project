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
    private By taxLabel = By.className("summary_tax_label");
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
        String tax = driver.findElement(taxLabel).getText();
        String[] myArray = tax.split(" ");
        return myArray[1];
    }
    public String getTotalSum(){
        String sum = driver.findElement(totalSum).getText();
        String[] myArray = sum.split(" ");
        return myArray[1];
    }
    public void clickFinishButton(){
        driver.findElement(finishButton).click();
    }

}
