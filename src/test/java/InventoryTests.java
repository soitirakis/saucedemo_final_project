import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testdata.classes.CheckoutInformation;
import testdata.classes.ItemDetails;
import testdata.classes.Login;
import testdata.classes.UserData;
import utils.Writer;

import java.util.List;

import static testdata.pages.CheckoutCompleteTestData.*;
import static testdata.pages.LoginTestData.HEADER_LOGIN;
import static utils.RandomGenerator.*;

public class InventoryTests extends BaseTests {
    static InventoryItemPage inventoryItem;
    static ShoppingCart shoppingCart;
    static CheckoutStepOne checkoutStepOne;
    static ItemDetails itemDetails;

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

        String itemToAddToCart = generateItemToAddToCart(item);  //generate item to be added to cart <<add-to-cart-name>>
        String itemRemoveButton = generateItemToRemoveFromCart(item); //generate item to be removed from cart <<remove-item-name>>

        inventoryPage.addItemToCart(itemToAddToCart);   //add item to cart

        Assert.assertTrue(inventoryPage.removeFromCartButtonDisplayed(itemRemoveButton), "Remove button is not displayed: " +  itemRemoveButton);
        inventoryPage.waitCartBadgeVisible();  //wait for cart badge to be visible
        Assert.assertTrue(inventoryPage.shoppingCartBadgeDisplayed(), "Cart badge is not displayed: " +  itemRemoveButton);


        inventoryPage.waitRemoveButtonDisplayed(itemRemoveButton);  //wait for the remove button to be displayed
        inventoryPage.removeItemFromCart(itemRemoveButton);  //remove the item from cart
        inventoryPage.addItemToCartButtonDisplayed(itemToAddToCart); //wait for <<add to cart>> button to be displayed


        inventoryPage.waitCartBadgeGone();
        Assert.assertTrue(inventoryPage.addItemToCartButtonDisplayed(itemToAddToCart), "Remove button still displayed: " +  itemRemoveButton);
        Assert.assertFalse(inventoryPage.shoppingCartBadgeDisplayed(), "Cart badge not empty");
    }

    @Test
    public void addCertainItemToCartTest() {
        itemDetails = new ItemDetails("item_details"); //item: Sauce Labs Backpack
        shoppingCart = new  ShoppingCart(driver);//item to be added

        String title = itemDetails.getTitle();

        String itemToAddToCart =  generateItemToAddToCart(title);
        String itemToRemove = generateItemToRemoveFromCart(title);

        inventoryPage.addItemToCart(itemToAddToCart);  //add item to cart
        inventoryPage.clickOnShoppingCart();

        Assert.assertEquals(shoppingCart.getItemTitle(), itemDetails.getTitle());  //have same title json + cart
        Assert.assertEquals(shoppingCart.getItemDescription(), itemDetails.getDescription()); //have same description
        Assert.assertEquals(shoppingCart.getItemPrice(), itemDetails.getPriceTag()+itemDetails.getPrice()); //have same price
        Assert.assertTrue(shoppingCart.setCheckoutButtonDisplayed());
        Assert.assertTrue(shoppingCart.isRemoveButtonDisplayed(itemToRemove), "Remove button is not displayed: " +  itemToRemove);


        //remove item from cart
        shoppingCart.clickOnRemoveButton(itemToRemove);
        shoppingCart.waitRemoveButtonGone(itemToRemove);
        Assert.assertFalse(shoppingCart.isRemoveButtonDisplayed(itemToRemove), "Remove button is not displayed: " +  itemToRemove);

        shoppingCart.waitItemDescriptionGone();
        Assert.assertFalse(shoppingCart.isItemDescriptionDisplayed(), "Item description is not displayed: " +  itemToRemove);
        //TODO
        //QTY empty
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

        CheckoutStepTwo checkoutStepTwo = new CheckoutStepTwo(driver);
        //TODO
        //json cu produsul title, description, tax, total.
        //assert ca valorile sunt la fel.
        //click on finish
        //checkout complete
    }

    @Test
    public void buyCertainItemTest() {
        itemDetails = new ItemDetails("item_details"); //item: Sauce Labs Backpack
        shoppingCart = new  ShoppingCart(driver);

        String title = itemDetails.getTitle();
        System.out.println("title: " + title);
        String itemToAddToCart =  generateItemToAddToCart(title);
        String itemToRemove = generateItemToRemoveFromCart(title);

        inventoryPage.addItemToCart(itemToAddToCart);  //add item to cart
        inventoryPage.clickOnShoppingCart();  //go to shopping cart

        Assert.assertTrue(shoppingCart.isYourCartTitleDisplayed());

        Assert.assertEquals(shoppingCart.getItemTitle(), itemDetails.getTitle());  //have same title json + cart
        Assert.assertEquals(shoppingCart.getItemDescription(), itemDetails.getDescription()); //have same description
        Assert.assertEquals(shoppingCart.getItemPrice(), itemDetails.getPriceTag()+itemDetails.getPrice()); //have same price
        Assert.assertTrue(shoppingCart.setCheckoutButtonDisplayed());
        Assert.assertTrue(shoppingCart.isRemoveButtonDisplayed(itemToRemove), "Remove button is not displayed: " +  itemToRemove);

        shoppingCart.clickOnCheckoutButton(); //checkout the product
        checkoutStepOne = new CheckoutStepOne(driver);

        Assert.assertTrue(checkoutStepOne.getCheckoutInformationTitleDisplayed());

        //generate the buyer
        String firstName = generateRandomName();
        String lastName = generateRandomName();
        String zipCode = "12345";

        UserData user =  new UserData(firstName, lastName, zipCode);
        Writer.writeValidNewUser(user, "checkout_information"); //write json with buyer details

        CheckoutInformation checkoutUser = new CheckoutInformation("checkout_information");
        checkoutStepOne.checkoutInformationContinueButton(checkoutUser); //checkout user

        CheckoutStepTwo checkoutStepTwo = new CheckoutStepTwo(driver);

        Assert.assertEquals(checkoutStepTwo.getHeader(), "Checkout: Overview");
        Assert.assertEquals(checkoutStepTwo.getItemName(), itemDetails.getTitle());
        Assert.assertEquals(checkoutStepTwo.getItemDescription(), itemDetails.getDescription());
        Assert.assertEquals(checkoutStepTwo.getItemPrice(), itemDetails.getPriceTag()+itemDetails.getPrice());
        Assert.assertEquals(checkoutStepTwo.getTax(), itemDetails.getPriceTag()+itemDetails.getTax());
        Assert.assertEquals(checkoutStepTwo.getTotalSum(),itemDetails.getPriceTag()+itemDetails.getTotalPrice());

        checkoutStepTwo.clickFinishButton();
        CheckoutComplete checkoutComplete = new CheckoutComplete(driver);

        Assert.assertEquals(checkoutComplete.getPageHeader(), HEADER_CHECKOUT_COMPLETE);
        Assert.assertEquals(checkoutComplete.getOrderHeader(), ORDER_HEADER);
        Assert.assertEquals(checkoutComplete.getConfirmationText(), ORDER_MESSAGE);
        Assert.assertTrue(checkoutComplete.backHomeButtonDisplayed());
    }
    @Test
    public void sortElementsZtoATests () {
        inventoryPage.sortItemsZtoA();

        List<String> inventoryItemsList = inventoryPage.getInventoryListItems(); //list of article names in inventory
        System.out.println("inventoryItemsList: " + inventoryItemsList);

    }
}
