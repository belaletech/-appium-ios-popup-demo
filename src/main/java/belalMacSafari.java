import io.appium.java_client.safari.options.SafariOptions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

public class belalMacSafari {
    private RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        // LambdaTest credentials
        String username = "belalahmad";
        String authkey = "wKW4T7dT73bNkNpPZmwdPUafBxJTiavujikkUVziNW02GzT4jB";
        String hub = "hub.lambdatest.com/wd/hub";

        // Set SafariOptions with platform and browser version
        SafariOptions browserOptions = new SafariOptions();
        browserOptions.setPlatformName("macOS Sonoma"); // You can change this if not supported
        browserOptions.setBrowserVersion("17");
//        browserOptions.setPlatformName("macOS macOS Ventura");
//        browserOptions.setBrowserVersion("17");

        // Set LambdaTest specific options
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "MacSafari");
        ltOptions.put("browserName", "Safari");
        ltOptions.put("project", "Belal");
        ltOptions.put("w3c", true); // Ensure W3C compliance


        // Add ltOptions to SafariOptions
        browserOptions.setCapability("LT:Options", ltOptions);

        // Construct the URL
        String remoteUrl = "https://" + username + ":" + authkey + "@" + hub;

        // Initialize RemoteWebDriver with SafariOptions
        driver = new RemoteWebDriver(new URL(remoteUrl), browserOptions);
    }

    @Test
    public void testFormSubmission() throws InterruptedException {
        // Open the website
        driver.get("https://the-internet.herokuapp.com/geolocation?trk=public_post_comment-text");

        // Wait for the page to load and locate the "Where am I?" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement whereAmIButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));

        // Click the button to trigger geolocation
        whereAmIButton.click();

        // Wait for the latitude and longitude to display
        Thread.sleep(7000);
    }
    public void handleGeoLocationAlert() {
        try {
            // Wait for alert to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            // Accept the geolocation pop-up
            alert.accept();
            System.out.println("Geolocation pop-up accepted.");
        } catch (Exception e) {
            System.out.println("No geolocation pop-up appeared.");
        }
    }
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser after tests
        }
    }
}
