import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryItemPage;
import testdata.classes.ItemDetails;
import testdata.classes.Login;

import static testdata.pages.LoginTestData.HEADER_LOGIN;

public class ItemPageTests extends BaseTests {
    static ItemDetails itemDetails;
    static InventoryItemPage  inventoryItemPage;

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();

        loginUser = new Login("standard_user");
        loginPage.authenticate(loginUser);

        Assert.assertEquals(inventoryPage.getHeaderText(), HEADER_LOGIN);
        Assert.assertTrue(inventoryPage.isShoppingCartDisplayed());
    }
    @Test
    public void testItemPage() {
        itemDetails = new ItemDetails("item_details");
        inventoryItemPage = new InventoryItemPage(driver);

        inventoryPage.clickOnRandomItem(itemDetails.getTitle());

        Assert.assertTrue(inventoryItemPage.isBackToProductsButtonDisplayed());
        Assert.assertEquals(inventoryItemPage.getInventoryItemName(),  itemDetails.getTitle());
        Assert.assertEquals(inventoryItemPage.getInventoryItemDescription(), itemDetails.getDescription());
        Assert.assertEquals(inventoryItemPage.getInventoryItemPrice(),  itemDetails.getPriceTag()+itemDetails.getPrice());
    }
}