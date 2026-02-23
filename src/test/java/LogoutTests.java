import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testdata.classes.Login;

import static testdata.pages.LoginTestData.HEADER_LOGIN;

public class LogoutTests extends BaseTests{
    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();

        loginUser =  new Login("standard_user");
        loginPage.authenticate(loginUser);

        Assert.assertEquals(inventoryPage.getHeaderText(), HEADER_LOGIN);
        Assert.assertTrue(inventoryPage.isShoppingCartDisplayed());
    }
    @Test
    public void validLogoutTest(){
        inventoryPage.clickOnBurgerMenuButton();

        Assert.assertTrue(inventoryPage.isLogoutDisplayed());
        inventoryPage.clickLogoutButton();

        Assert.assertEquals(loginPage.getHeader(), HEADER_LOGIN);
        Assert.assertTrue(loginPage.isLoginDisplayed());
    }
}
