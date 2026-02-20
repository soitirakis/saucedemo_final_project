import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutStepOne;
import pages.ShoppingCart;
import testdata.classes.*;

import static testdata.pages.CheckoutStepOneTestData.*;
import static testdata.pages.LoginTestData.HEADER_LOGIN;
import static utils.RandomGenerator.generateItemToAddToCart;

public class UserInformationTests extends BaseTests{
    static ShoppingCart shoppingCart;
    static ItemDetails itemDetails;
    static CheckoutStepOne checkoutStepOne;
    static CheckoutInformation userInformation;

    @BeforeMethod
    public void  beforeMethod() {
        super.beforeMethod();

        loginUser = new Login("standard_user");
        loginPage.authenticate(loginUser);

        Assert.assertEquals(inventoryPage.getHeaderText(), HEADER_LOGIN);
        Assert.assertTrue(inventoryPage.isShoppingCartDisplayed());

        itemDetails = new ItemDetails("item_details");
        shoppingCart = new ShoppingCart(driver);
        checkoutStepOne = new CheckoutStepOne(driver);

        String itemToAddToCart = generateItemToAddToCart(itemDetails.getTitle());
        inventoryPage.addItemToCart(itemToAddToCart);

        inventoryPage.clickOnShoppingCart();
        shoppingCart.clickOnCheckoutButton();
    }
    @Test
    public void missingUserInformationTest() throws InterruptedException
    {
        userInformation = new CheckoutInformation("user_information_missing");
        checkoutStepOne.checkoutInformationContinueButton(userInformation);

        Assert.assertTrue(checkoutStepOne.getCheckoutInformationTitleDisplayed());
        Assert.assertEquals(checkoutStepOne.getErrorMessage(), ERROR_FIRST_NAME_MISSING );
    }
    @Test
    public void missingLastNameTest() throws InterruptedException
    {
        userInformation = new CheckoutInformation("user_information_missing_last_name");
        checkoutStepOne.checkoutInformationContinueButton(userInformation);

        Assert.assertTrue(checkoutStepOne.getCheckoutInformationTitleDisplayed());
        Assert.assertEquals(checkoutStepOne.getErrorMessage(), ERROR_LAST_NAME_MISSING );
    }
    @Test
    public void  missingPostCodeTest() throws InterruptedException
    {
        userInformation = new CheckoutInformation("user_information_missing_postcode");
        checkoutStepOne.checkoutInformationContinueButton(userInformation);

        Assert.assertTrue(checkoutStepOne.getCheckoutInformationTitleDisplayed());
        Assert.assertEquals(checkoutStepOne.getErrorMessage(), ERROR_POSTCODE_MISSING );
    }
}
