import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testdata.classes.CheckoutInformation;
import testdata.classes.ItemDetails;
import testdata.classes.Login;
import testdata.classes.UserData;
import utils.Writer;

import static testdata.pages.CheckoutCompleteTestData.*;
import static testdata.pages.LoginTestData.HEADER_LOGIN;
import static utils.RandomGenerator.*;
import static utils.RandomGenerator.generateRandomName;

public class EndToEndTests extends BaseTests{
    static ShoppingCart shoppingCart;
    static CheckoutStepOne checkoutStepOne;
    static ItemDetails itemDetails;
    static ItemDetails itemEmptyDetails;


    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();

        loginUser =  new Login("standard_user");
        loginPage.authenticate(loginUser);

        Assert.assertEquals(inventoryPage.getHeaderText(), HEADER_LOGIN);
        Assert.assertTrue(inventoryPage.isShoppingCartDisplayed());

    }
    @Test
    public void buyCertainItemTest() {
        itemDetails = new ItemDetails("item_product"); //item: "Sauce Labs Backpack" from json
        shoppingCart = new ShoppingCart(driver);

        String title = itemDetails.getTitle();
        System.out.println("title: " + title);
        String itemToAddToCart =  generateItemToAddToCart(title);
        String itemToRemove = generateItemToRemoveFromCart(title);

        //change itemToAddToCart to getId();
        inventoryPage.addItemToCart(itemDetails.getId());  //add item to cart
        inventoryPage.clickOnShoppingCart();  //go to shopping cart

        Assert.assertTrue(shoppingCart.isYourCartTitleDisplayed());

        Assert.assertEquals(shoppingCart.getItemTitle(), itemDetails.getTitle());  //have same title json + cart
        Assert.assertEquals(shoppingCart.getItemDescription(), itemDetails.getDescription()); //have same description
        Assert.assertEquals(shoppingCart.getItemPrice(), itemDetails.getPriceTag()+itemDetails.getPrice()); //have same price
        Assert.assertTrue(shoppingCart.setCheckoutButtonDisplayed());
        Assert.assertTrue(shoppingCart.isRemoveButtonDisplayed(itemDetails.getId()), "Remove button is not displayed: " +  itemToRemove);

        shoppingCart.clickOnCheckoutButton(); //checkout the product
        checkoutStepOne = new CheckoutStepOne(driver);

        Assert.assertTrue(checkoutStepOne.getCheckoutInformationTitleDisplayed());

        //generate the buyer
        UserData user =  new UserData(generateRandomName(), generateRandomName(), "12345");
        Writer.writeValidNewUser(user, "checkout_information"); //write json with buyer details

        CheckoutInformation checkoutUser = new CheckoutInformation("checkout_information");
        checkoutStepOne.checkoutInformationContinueButton(checkoutUser); //checkout user

        CheckoutStepTwo checkoutStepTwo = new CheckoutStepTwo(driver);

        Assert.assertEquals(checkoutStepTwo.getHeader(), "Checkout: Overview");
        Assert.assertEquals(checkoutStepTwo.getItemName(), itemDetails.getTitle());  //check item name same name from json
        Assert.assertEquals(checkoutStepTwo.getItemDescription(), itemDetails.getDescription()); //check item description same
        Assert.assertEquals(checkoutStepTwo.getItemPrice(), itemDetails.getPriceTag()+itemDetails.getPrice()); //check same price
        Assert.assertEquals(checkoutStepTwo.getTax(), itemDetails.getPriceTag()+itemDetails.getTax()); //check same tax
        Assert.assertEquals(checkoutStepTwo.getTotalSum(),itemDetails.getPriceTag()+itemDetails.getTotalPrice()); //check same total price

        checkoutStepTwo.clickFinishButton();
        CheckoutComplete checkoutComplete = new CheckoutComplete(driver);

        Assert.assertEquals(checkoutComplete.getPageHeader(), HEADER_CHECKOUT_COMPLETE);
        Assert.assertEquals(checkoutComplete.getOrderHeader(), ORDER_HEADER);
        Assert.assertEquals(checkoutComplete.getConfirmationText(), ORDER_MESSAGE);
        Assert.assertTrue(checkoutComplete.backHomeButtonDisplayed());
    }
    @Test
    public void checkoutWithEmptyCartTest() {
        itemDetails = new ItemDetails("item_product"); //item: "Sauce Labs Backpack" from json
        itemEmptyDetails = new ItemDetails("item_empty_details");
        shoppingCart = new ShoppingCart(driver);

        String title = itemDetails.getTitle();
        System.out.println("title: " + title);
        // itemToAddToCart =  generateItemToAddToCart(title);
        //String itemToRemove = generateItemToRemoveFromCart(title);

        inventoryPage.addItemToCart(itemDetails.getId());  //add item to cart
        inventoryPage.clickOnShoppingCart();  //go to shopping cart

        Assert.assertTrue(shoppingCart.isYourCartTitleDisplayed());

        Assert.assertEquals(shoppingCart.getItemTitle(), itemDetails.getTitle());  //have same title json + cart
        Assert.assertEquals(shoppingCart.getItemDescription(), itemDetails.getDescription()); //have same description
        Assert.assertEquals(shoppingCart.getItemPrice(), itemDetails.getPriceTag()+itemDetails.getPrice()); //have same price
        Assert.assertTrue(shoppingCart.setCheckoutButtonDisplayed());
        Assert.assertTrue(shoppingCart.isRemoveButtonDisplayed(itemDetails.getId()), "Remove button is not displayed: " +  itemDetails.getId());

        shoppingCart.clickOnRemoveButton(itemDetails.getId()); //remove item
        shoppingCart.waitRemoveButtonGone(itemDetails.getId());
        Assert.assertFalse(shoppingCart.isRemoveButtonDisplayed(itemDetails.getId()), "Remove button is not displayed: " +  itemDetails.getId());
        Assert.assertFalse(shoppingCart.isItemDescriptionDisplayed(), "Item description is not displayed: " +  itemDetails.getId());

        shoppingCart.clickOnCheckoutButton(); //checkout the product
        checkoutStepOne = new CheckoutStepOne(driver);

        Assert.assertTrue(checkoutStepOne.getCheckoutInformationTitleDisplayed());

        //generate the buyer
        UserData user =  new UserData(generateRandomName(), generateRandomName(), "12345");
        Writer.writeValidNewUser(user, "checkout_information"); //write json with buyer details

        CheckoutInformation checkoutUser = new CheckoutInformation("checkout_information");
        checkoutStepOne.checkoutInformationContinueButton(checkoutUser); //checkout user

        CheckoutStepTwo checkoutStepTwo = new CheckoutStepTwo(driver);

        Assert.assertEquals(checkoutStepTwo.getHeader(), "Checkout: Overview");
        Assert.assertEquals(checkoutStepTwo.getTax(), itemEmptyDetails.getPriceTag()+itemEmptyDetails.getTax()); //check same tax
        Assert.assertEquals(checkoutStepTwo.getTotalSum(),itemEmptyDetails.getPriceTag()+itemEmptyDetails.getTotalPrice()); //check same total price

        checkoutStepTwo.clickFinishButton();
        CheckoutComplete checkoutComplete = new CheckoutComplete(driver);

        Assert.assertEquals(checkoutComplete.getPageHeader(), HEADER_CHECKOUT_COMPLETE);
        Assert.assertEquals(checkoutComplete.getConfirmationText(), ORDER_ERROR_MESSAGE);
        Assert.assertTrue(checkoutComplete.backHomeButtonDisplayed());
    }
}
