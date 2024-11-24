import io.appium.java_client.safari.options.SafariOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class MacFireFox {
    private RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        // LambdaTest credentials
        String username = "satnamlocance";
        String authkey = "zUCgUEvx5jiBZpsJN36o7FLyxfT09z5p3AWnjzDo9vU7C3GWR2";
        String hub = "hub.lambdatest.com/wd/hub";

        // Set FirefoxOptions with platform and browser version
        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName("macOS Sonoma");
        browserOptions.setBrowserVersion("131");

        // Enable geolocation in Firefox
        HashMap<String, Object> firefoxProfile = new HashMap<>();
        firefoxProfile.put("geo.enabled", true); // Enable geolocation in Firefox
        firefoxProfile.put("geo.prompt.testing", true); // Automatically grant geolocation permission
        firefoxProfile.put("geo.prompt.testing.allow", true); // Allow location sharing
        firefoxProfile.put("geo.provider.network.url", "data:application/json,{\"location\": {\"lat\": 37.7749, \"lng\": -122.4194}, \"accuracy\": 100.0}"); // Set a custom geolocation (San Francisco in this case)

        // Apply Firefox profile settings
        browserOptions.addPreference("geo.enabled", true);
        browserOptions.addPreference("geo.prompt.testing", true);
        browserOptions.addPreference("geo.prompt.testing.allow", true);
        browserOptions.addPreference("geo.provider.network.url", "data:application/json,{\"location\": {\"lat\": 37.7749, \"lng\": -122.4194}, \"accuracy\": 100.0}");

        // Set LambdaTest specific options
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "MacFireFox_GeoTest");
        ltOptions.put("browserName", "Firefox");
        ltOptions.put("project", "Belal");
        ltOptions.put("w3c", true); // Ensure W3C compliance
        ltOptions.put("tunnelName", "d3ec552c-f6a7-4d63-9ae4-549b3108f114");
        ltOptions.put("tunnel", true); // If using tunnel
        ltOptions.put("console", true); // Enable console logs
        ltOptions.put("network", true); // Enable network logs
        ltOptions.put("debug", true); // Enable debugging

        // Add ltOptions to FirefoxOptions
        browserOptions.setCapability("LT:Options", ltOptions);

        // Construct the URL
        String remoteUrl = "https://" + username + ":" + authkey + "@" + hub;

        // Initialize RemoteWebDriver with FirefoxOptions
        driver = new RemoteWebDriver(new URL(remoteUrl), browserOptions);
    }

    @Test
    public void testFormSubmission() throws InterruptedException {
        // Open the website
        driver.get("https://staging-portal.locance.net/");
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"CybotCookiebotDialogBodyButtonAccept\"]")));
        acceptCookies.click();

        // Wait until the 'SIGN IN' button is clickable and click it
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/login/process' and @class='simple-button' and text()='SIGN IN']")
        ));
        signInButton.click();

        // Wait until the username field is present and enter the email
        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        usernameField.sendKeys("satsingh@gmail.com");

        // Wait until the password field is present and enter the password
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        passwordField.sendKeys("Password001");

        // Wait until the login button is clickable and click it
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/main/section/div/div/div/form/div[2]/button")
        ));
        loginButton.click();
        System.out.println("login successfully");

        Thread.sleep(3000);
        driver.get("https://staging-portal.locance.net/tools/session_id_creator");
        Thread.sleep(3000);
        System.out.println("Navigate session creater");

        // Wait until the session ID input field is present and enter the session ID
        WebElement sessionIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("session_id")));
        sessionIdField.sendKeys("SessionID123ABCD");

//        check out the watch duration
        WebElement watchDuration=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"watch\"]")));
        watchDuration.click();

        //fill the watch duration
        WebElement watchElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"watchduration\"]")));
        watchElement.sendKeys("6");

        System.out.println("filled the form");
        // Wait until the 'Go' button is clickable and click it
        WebElement goButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGo")));
        goButton.click();

        Thread.sleep(5000);
        System.out.println("btnGo");
        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser after tests
        }
    }
}
