

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
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

public class MacOsVentura {
    private RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        // LambdaTest credentials
        String username = "satnamlocance";
        String authkey = "zUCgUEvx5jiBZpsJN36o7FLyxfT09z5p3AWnjzDo9vU7C3GWR2";
        String hub = "hub.lambdatest.com/wd/hub";

        // ChromeOptions for macOS with geolocation preferences
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("macOS Ventura"); // macOS version
        browserOptions.setBrowserVersion("latest"); // Latest Chrome version

//        // Chrome-specific geolocation handling
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 1); // Deny geolocation
        prefs.put("googlegeolocationaccess.enabled", false); // Disable Google location services
        browserOptions.setExperimentalOption("prefs", prefs);

//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("profile.default_content_setting_values.geolocation", 0); // Allow geolocation
////        prefs.put("profile.default_content_setting_values.notifications", 1); // Block notifications
//        browserOptions.setExperimentalOption("prefs", prefs);


//        ChromeOptions options = new ChromeOptions();
//        Map < String, Object > prefs = new HashMap < String, Object > ();
//        Map < String, Object > profile = new HashMap < String, Object > ();
//        Map < String, Object > contentSettings = new HashMap < String, Object > ();

        // SET CHROME OPTIONS
        // 0 - Default, 1 - Allow, 2 - Block
//        contentSettings.put("geolocation", 1);
//        profile.put("managed_default_content_settings", contentSettings);
//        prefs.put("profile", profile);
//        options.setExperimentalOption("prefs", prefs);

        // Chrome-specific geolocation handling
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("profile.default_content_setting_values.geolocation", 2); // Allow geolocation
//        browserOptions.setExperimentalOption("prefs", prefs);

//        // Set Chrome-specific geolocation handling
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("profile.default_content_setting_values.geolocation", 2); // Allow geolocation
//        browserOptions.setExperimentalOption("prefs", prefs);
//
//// Mock Geolocation Coordinates
//        Map<String, Object> coordinates = new HashMap<>();
//        coordinates.put("latitude", 37.7749); // Example: San Francisco latitude
//        coordinates.put("longitude", -122.4194); // Example: San Francisco longitude
//        coordinates.put("accuracy", 1); // High accuracy

//// Add Geolocation Emulation
//        Map<String, Object> devToolsCommands = new HashMap<>();
//        devToolsCommands.put("setGeolocationOverride", coordinates);
//        browserOptions.setCapability("goog:chromeOptions", devToolsCommands);


        // Set ChromeOptions for geolocation handling
//        ChromeOptions options = new ChromeOptions();
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("googlegeolocationaccess.enabled", true);
//        prefs.put("profile.default_content_setting_values.geolocation", 2); // Allow geolocation
//        prefs.put("profile.default_content_setting_values.notifications", 1); // Allow notifications
//        prefs.put("profile.managed_default_content_settings.geolocation", 1); // Manage geolocation settings

//        options.setExperimentalOption("prefs", prefs);



        // LambdaTest specific options
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "Mac ventura");
        ltOptions.put("browserName", "Chrome");
        ltOptions.put("project", "Belal");
        ltOptions.put("w3c", true); // Ensure W3C compliance
        ltOptions.put("tunnelName", "1918ef11-285f-4ba6-ba9d-abbd91522b54");
//        ltOptions.put("fixedIP","10.242.33.49");
        ltOptions.put("tunnel", true);
//        ltOptions.put("autoAcceptAlerts", true);
        ltOptions.put("console", true); // Enable console logs
        ltOptions.put("network", true); // Enable network logs
        ltOptions.put("debug", true); // Enable debugging if needed

        // Add LambdaTest options to ChromeOptions
        browserOptions.setCapability("LT:Options", ltOptions);

        // Construct the URL for LambdaTest
        String remoteUrl = "https://" + username + ":" + authkey + "@" + hub;

        // Initialize RemoteWebDriver with ChromeOptions
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

        Thread.sleep(2000);
        driver.get("https://staging-portal.locance.net/tools/session_id_creator");
        Thread.sleep(3000);
        System.out.println("Navigate session creater");

        // Wait until the session ID input field is present and enter the session ID
        WebElement sessionIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("session_id")));
        sessionIdField.sendKeys("TESTUSER04");
        System.out.println("filled the user");

//        check out the watch duration
        WebElement watchDuration=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"watch\"]")));
        watchDuration.click();
//
//        //fill the watch duration
        WebElement watchElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"watchduration\"]")));
        watchElement.sendKeys("6");

        System.out.println("filled the form");
        // Wait until the 'Go' button is clickable and click it
        WebElement goButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGo")));
        goButton.click();

        Thread.sleep(1000);
//        handleGeoLocationAlert();
        System.out.println("btnGo");
        Thread.sleep(5000);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the driver after execution
        }
    }
}
