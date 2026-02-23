import org.testng.Assert;
import org.testng.annotations.Test;
import testdata.classes.Login;

import static testdata.pages.LoginTestData.*;

public class LoginTests extends BaseTests{
    @Test
    public void validLoginTest(){
        loginUser =  new Login("standard_user");
        loginPage.authenticate(loginUser);

        Assert.assertTrue(inventoryPage.isShoppingCartDisplayed());
        Assert.assertEquals(inventoryPage.getHeaderText(), "Swag Labs");
        Assert.assertTrue(inventoryPage.isBurgerMenuButtonDisplayed());

        inventoryPage.clickOnBurgerMenuButton();
        Assert.assertTrue(inventoryPage.isLogoutDisplayed());
    }
    @Test
    public void lockedOutUserTest() throws InterruptedException{
        loginUser =  new Login("locked_out_user");
        loginPage.authenticate(loginUser);

        Assert.assertEquals(loginPage.getHeader(), HEADER_LOGIN);
        Assert.assertEquals(loginPage.getErrorMessage(), LOCKED_OUT_USER);
        Assert.assertTrue(loginPage.isLoginDisplayed());
    }
    @Test
    public void missingUserTest() throws InterruptedException{
        loginUser =  new Login("missing_data_user");
        loginPage.authenticate(loginUser);

        Assert.assertEquals(loginPage.getHeader(), HEADER_LOGIN);
        Assert.assertEquals(loginPage.getErrorMessage(), MISSING_USER);
        Assert.assertTrue(loginPage.isLoginDisplayed());
    }
}
