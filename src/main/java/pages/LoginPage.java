package pages;

import driver.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testdata.classes.Login;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //elements
    private By header = By.className("login_logo");
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");

    private By errorMessage = By.xpath("//h3");

    //actions
    public String getHeader() {
        return driver.findElement(header).getText();
    }
    public String getErrorMessage() throws InterruptedException{
        Thread.sleep(2000);
        return driver.findElement(errorMessage).getText();
    }
    public boolean isLoginDisplayed(){
        WaitUtils.visibilityOfElementLocated(loginButton);
        return driver.findElement(loginButton).isDisplayed();
    }
    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void authenticate(Login user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        clickLoginButton();
    }
}
