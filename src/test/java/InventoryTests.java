import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutStepOne;
import pages.InventoryItemPage;
import pages.ShoppingCart;
import testdata.classes.CheckoutInformation;
import testdata.classes.Login;
import testdata.classes.UserData;
import utils.Writer;

import java.util.List;

import static testdata.pages.LoginTestData.HEADER_LOGIN;
import static utils.RandomGenerator.*;

public class InventoryTests extends BaseTests {
    static InventoryItemPage inventoryItem;
    static ShoppingCart shoppingCart;
    static CheckoutStepOne checkoutStepOne;

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();

        loginUser =  new Login("standard_user");
        loginPage.authenticate(loginUser);

        Assert.assertEquals(inventoryPage.getHeaderText(), HEADER_LOGIN);
        Assert.assertTrue(inventoryPage.isShoppingCartDisplayed());
    }
    @Test
    public void addRandomItemToCartTests() throws InterruptedException{

        List<String> inventoryItemsList = inventoryPage.getInventoryListItems(); //list of article names in inventory
        int inventoryListSize = inventoryItemsList.size();
        String item = inventoryItemsList.get(generateNumber(inventoryListSize)); // generate random item from List

        System.out.println("item: " + item);

        String itemToAddToCart = generateItemToAddToCart(item);  //generate item to be added to cart <<add-to-cart-name>>
        String itemRemoveButton = generateItemToRemoveFromCart(item); //generate item to be removed from cart <<remove-item-name>>
        System.out.println("itemToClick: " + itemToAddToCart);
        System.out.println("itemRemoveButton: " + itemRemoveButton);

        inventoryPage.addItemToCart(itemToAddToCart);   //add item to cart

        Thread.sleep(2000);
        Assert.assertTrue(inventoryPage.removeFromCartButtonDisplayed(itemRemoveButton), "Remove button is not displayed: " +  itemRemoveButton);
        inventoryPage.waitCartBadgeVisible();  //wait for cart badge to be visible
        Assert.assertTrue(inventoryPage.shoppingCartBadgeDisplayed(), "Cart badge is not displayed: " +  itemRemoveButton);


        inventoryPage.waitRemoveButtonDisplayed(itemRemoveButton);  //wait for the remove button to be displayed
        inventoryPage.removeItemFromCart(itemRemoveButton);  //remove the item from cart
        Thread.sleep(2000);
        inventoryPage.addItemToCartButtonDisplayed(itemToAddToCart); //wait for <<add to cart>> button to be displayed


        inventoryPage.waitCartBadgeGone();
        Assert.assertTrue(inventoryPage.addItemToCartButtonDisplayed(itemToAddToCart), "Remove button still displayed: " +  itemRemoveButton);
        Assert.assertFalse(inventoryPage.shoppingCartBadgeDisplayed(), "Cart badge not empty");
    }

    @Test
    public void navigateToItem(){
        List<String> inventoryItemsList = inventoryPage.getInventoryListItems(); //list of article names in inventory
        int inventoryListSize = inventoryItemsList.size();
        String item = inventoryItemsList.get(generateNumber(inventoryListSize)); // generate random item from List

        System.out.println("item: " + item);
        inventoryPage.clickOnRandomItem(item);

        inventoryItem = new InventoryItemPage(driver);
        Assert.assertTrue(inventoryItem.isBackToProductsButtonDisplayed());
        Assert.assertEquals(inventoryItem.getInventoryItemName(), item);

        inventoryItem.addToCart();
        shoppingCart = new  ShoppingCart(driver);
        inventoryItem.clickOnShoppingCartButton();

        Assert.assertTrue(shoppingCart.isYourCartTitleDisplayed());

        shoppingCart.clickOnCheckoutButton();
        checkoutStepOne = new CheckoutStepOne(driver);

        Assert.assertTrue(checkoutStepOne.getCheckoutInformationTitleDisplayed());

        String firstName = generateRandomName();
        String lastName = generateRandomName();
        String zipCode = "12345";

        UserData user =  new UserData(firstName, lastName, zipCode);
        Writer.writeValidNewUser(user, "checkout_information");

        CheckoutInformation checkoutUser = new CheckoutInformation("checkout_information");
        checkoutStepOne.checkoutInformationContinueButton(checkoutUser);

    }
}
