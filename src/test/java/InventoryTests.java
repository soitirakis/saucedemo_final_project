import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testdata.classes.Login;

import java.util.List;

import static testdata.pages.LoginTestData.HEADER_LOGIN;
import static utils.RandomGenerator.*;

public class InventoryTests extends BaseTests {
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

        Assert.assertTrue(inventoryPage.removeFromCartButtonDisplayed(itemRemoveButton), "Remove button is not displayed: " +  itemRemoveButton);
        inventoryPage.waitCartBadgeVisible();  //wait for cart badge to be visible
        Assert.assertTrue(inventoryPage.shoppingCartBadgeDisplayed(), "Cart badge is not displayed: " +  itemRemoveButton);


        inventoryPage.waitRemoveButtonDisplayed(itemRemoveButton);  //wait for the remove button to be displayed
        inventoryPage.removeItemFromCart(itemRemoveButton);  //remove the item from cart
        inventoryPage.addItemToCartButtonDisplayed(itemToAddToCart); //wait for <<add to cart>> button to be displayed

        //inventoryPage.waitCartBadgeGone();
        Assert.assertTrue(inventoryPage.addItemToCartButtonDisplayed(itemToAddToCart), "Remove button still displayed: " +  itemRemoveButton);
        //Assert.assertFalse(inventoryPage.shoppingCartBadgeDisplayed(), "Cart badge not empty");
    }
}
