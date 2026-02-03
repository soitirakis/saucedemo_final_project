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
    public void addRandomItemToCartTests() {
        List<String> inventoryItemsList = inventoryPage.getInventoryListItems();
        int inventoryListSize = inventoryItemsList.size();
        String item = inventoryItemsList.get(generateNumber(inventoryListSize));

        System.out.println("item: " + item);

        String itemToAddToCart = generateItemToAddToCart(item);
        String itemRemoveButton = generateItemToRemoveFromCart(item);
        System.out.println("itemToClick: " + itemToAddToCart);
        System.out.println("itemRemoveButton: " + itemRemoveButton);

        inventoryPage.addItemToCart(itemToAddToCart);

        Assert.assertTrue(inventoryPage.removeFromCartButtonDisplayed(itemRemoveButton));
        Assert.assertTrue(inventoryPage.shoppingCartBadgeDisplayed());
    }
}
