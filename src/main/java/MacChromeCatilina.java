

import org.openqa.selenium.Alert;
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

public class MacChromeCatilina {
    private RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        // LambdaTest credentials
        String username = "satnamlocance";
        String authkey = "zUCgUEvx5jiBZpsJN36o7FLyxfT09z5p3AWnjzDo9vU7C3GWR2";
        String hub = "hub.lambdatest.com/wd/hub";

        // ChromeOptions for macOS with geolocation preferences
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("macOS Catalina"); // macOS version
        browserOptions.setBrowserVersion("latest"); // Latest Chrome version

//         Chrome-specific geolocation handling
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 2); // Deny geolocation
        prefs.put("googlegeolocationaccess.enabled", true); // Disable Google location services
        browserOptions.setExperimentalOption("prefs", prefs);

//        //auto
////        // Geolocation override through ChromeOptions
//        Map<String, Object> prefs = new HashMap<>();
//        Map<String, Object> profile = new HashMap<>();
//        Map<String, Object> contentSettings = new HashMap<>();
////
////        // Allow geolocation automatically
//        contentSettings.put("geolocation", 1);
//        profile.put("managed_default_content_settings", contentSettings);
//        prefs.put("profile", profile);
//        browserOptions.setExperimentalOption("prefs", prefs);



        // LambdaTest specific options
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "Mac Chrome Catalina");
        ltOptions.put("browserName", "Chrome");
        ltOptions.put("project", "Belal catalina");
        ltOptions.put("w3c", true); // Ensure W3C compliance
        ltOptions.put("tunnelName", "1918ef11-285f-4ba6-ba9d-abbd91522b54");
//        ltOptions.put("fixedIP","10.242.33.49");
        ltOptions.put("tunnel", true);
        ltOptions.put("autoAcceptAlerts", true);
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

//        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        driver.get("https://dev-portal.locance.net/");
        Thread.sleep(2000);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement acceptCookies=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"CybotCookiebotDialogBodyButtonAccept\"]")));
        acceptCookies.click();

        // Wait until the 'SIGN IN' button is clickable and click it to accept cookies
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/login/process' and @class='simple-button' and text()='SIGN IN']")
        ));
        signInButton.click();

        // Wait until the username field is present and enter the email
        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        usernameField.sendKeys("satnam.singh@relevancelab.com");

        // Wait until the password field is present and enter the password
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        passwordField.sendKeys("password-01");

        // Wait until the login button is clickable and click it
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/main/section/div/div/div/form/div[2]/button")
        ));
        loginButton.click();
        System.out.println("login successfully");

        Thread.sleep(3000);
        driver.get("https://dev-portal.locance.net/tools/session_id_creator");
        Thread.sleep(3000);
        System.out.println("Navigate session creater");
        // Wait until the session ID input field is present and enter the session ID
        WebElement sessionIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("session_id")));
        sessionIdField.sendKeys("SessionID123ABCD");

        System.out.println("filled the form");
        // Wait until the 'Go' button is clickable and click it
        WebElement goButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGo")));
        goButton.click();

        Thread.sleep(7000);
//        handleGeoLocationAlert();
        System.out.println("btnGo");

    }


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the driver after execution
        }
    }
}
