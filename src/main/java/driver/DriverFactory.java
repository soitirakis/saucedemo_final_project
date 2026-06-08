package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
        chromeOption.addArguments("disable-infobars"); // Disable infobars
        chromeOption.addArguments("disable-popup-blocking"); // Disable popup blocking
        chromeOption.addArguments("disable-default-apps"); // Disable default apps
        chromeOption.addArguments("no-first-run"); // Skip first run wizards
        chromeOption.addArguments("no-default-browser-check"); // Skip default browser check
        chromeOption.addArguments("--disable-search-engine-choice-screen");

        if ("true".equalsIgnoreCase(System.getProperty("headless", System.getenv("CI") != null ? "true" : "false"))) {
            chromeOption.addArguments("--headless=new");
            chromeOption.addArguments("--no-sandbox");
            chromeOption.addArguments("--disable-dev-shm-usage");
            chromeOption.addArguments("--disable-gpu");
            chromeOption.addArguments("--window-size=1920,1080");
        } else {
            chromeOption.addArguments("start-maximized");
        }

        chromeOption.addArguments("disable-notifications"); // Disable notifications
        chromeOption.addArguments("disable-extensions"); // Disable extensions
        chromeOption.addArguments("guest"); // Disable change password popup

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOption.setExperimentalOption("prefs", prefs);

        chromeOption.addArguments("--disable-save-password-bubble");
        chromeOption.addArguments("--disable-notifications");

        driver = new ChromeDriver(chromeOption);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        return driver;
    }

    public static WebDriver closeDriver() {
        driver.quit();
        return driver;
    }
}
