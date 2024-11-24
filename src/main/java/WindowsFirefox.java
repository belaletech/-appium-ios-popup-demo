import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class WindowsFirefox {
    private RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        // LambdaTest credentials
        String username = "satnamlocance";
        String authkey = "zUCgUEvx5jiBZpsJN36o7FLyxfT09z5p3AWnjzDo9vU7C3GWR2";
        String hub = "hub.lambdatest.com/wd/hub";

        // Set FirefoxOptions with platform and browser version
        FirefoxOptions browserOptions = new FirefoxOptions();

        // Adding a Firefox profile to handle geolocation permissions
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("permissions.default.geo", 1); // 1 - Allow geolocation, 2 - Block
        firefoxProfile.setPreference("geo.prompt.testing", true);
        firefoxProfile.setPreference("geo.prompt.testing.allow", true);

        browserOptions.setProfile(firefoxProfile); // Set the profile in options

        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");

        // Set LambdaTest specific options
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "windowsFirefox");
        ltOptions.put("project", "Belal");
        ltOptions.put("w3c", true); // Ensure W3C compliance
        ltOptions.put("tunnelName", "3b6daad1-8c88-4e0a-85cc-c5a11b2a226d");
        ltOptions.put("tunnel", true);
        ltOptions.put("console", true); // Enable console logs
        ltOptions.put("network", true); // Enable network logs
        ltOptions.put("debug", true); // Enable debugging if needed

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
        Thread.sleep(3000);
        WebElement goButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGo")));
        goButton.click();

        Thread.sleep(5000);
        System.out.println("btnGo");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser after tests
        }
    }
}
