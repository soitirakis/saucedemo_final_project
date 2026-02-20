import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testdata.classes.Login;
import utils.PriceConvertor;
import utils.SortedGenerators;

import java.util.List;

import static testdata.pages.LoginTestData.HEADER_LOGIN;
import static utils.SortedGenerators.*;

public class FilterTests extends BaseTests{
    static List<String> inventoryItemsListInitial;

    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();

        loginUser =  new Login("standard_user");
        loginPage.authenticate(loginUser);

        Assert.assertEquals(inventoryPage.getHeaderText(), HEADER_LOGIN);
        Assert.assertTrue(inventoryPage.isShoppingCartDisplayed());

        inventoryItemsListInitial = inventoryPage.getInventoryListItems();

    }
    @Test
    public void sortElementsZtoATests () {
        inventoryPage.sortItemsZtoA();

        List<String> inventoryItemsListDescending = inventoryPage.getInventoryListItems(); //list of article names in inventory
        System.out.println("inventoryItemsList: " + inventoryItemsListDescending);

        Assert.assertEquals(inventoryItemsListDescending, isSortedDescending(inventoryItemsListInitial), "List is NOT sorted descending");

        inventoryPage.sortItemsAtoZ();

        List<String> inventoryItemsListAscending = inventoryPage.getInventoryListItems(); //list of article names in inventory
        System.out.println("inventoryItemsList: " + inventoryItemsListAscending);

        Assert.assertEquals(inventoryItemsListAscending, isSortedAscending(inventoryItemsListInitial), "List is NOT sorted ascending");
    }
    @Test
    public void sortItemsByPriceHighToLowTests() {
        inventoryPage.sortItemsByPriceHighToLow();
        List<String>  inventoryItemsListHighToLow = inventoryPage.getInventoryItemPrice();

        Assert.assertTrue(isPriceSortedDescending(inventoryItemsListHighToLow), "List is NOT sorted descending" );


        inventoryPage.sortItemsByPriceLowToHigh();
        List<String>  inventoryItemsListLowToHigh = inventoryPage.getInventoryItemPrice();
        Assert.assertTrue(isPriceSorted(inventoryItemsListLowToHigh), "List is NOT sorted descending" );

    }
}
