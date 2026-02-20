import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.InventoryPage;
import pages.LoginPage;
import testdata.URL;
import testdata.classes.Login;

public class BaseTests {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;

    protected Login loginUser;

    @BeforeMethod
    public void beforeMethod() {
        driver = DriverFactory.getDriver();

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        driver.get(URL.MAIN_URL);

        Assert.assertEquals(loginPage.getHeader(), "Swag Labs");
    }
   /* @AfterMethod
    public void afterMethod() {
        DriverFactory.closeDriver();
    }*/

}
