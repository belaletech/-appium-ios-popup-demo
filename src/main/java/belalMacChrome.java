import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class belalMacChrome {
    private WebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        // LambdaTest credentials
        String username = "belalahmad";
        String authkey = "07DC6fZhsIW0zC4nNc00IPj7eSqL2SVxMDx12N4SKslGihpTvq";
        String hub = "hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("macOS Sonoma");
        browserOptions.setBrowserVersion("latest");

//         Geolocation preferences
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("profile.default_content_setting_values.geolocation", 1); // Deny geolocation
//        prefs.put("googlegeolocationaccess.enabled", true); // Enable Google location services
//        browserOptions.setExperimentalOption("prefs", prefs);



        // LambdaTest specific options
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "MacChrome other ulr");
        ltOptions.put("project", "Belal");
        ltOptions.put("w3c", true); // Use W3C mode
//        ltOptions.put("autoAcceptAlerts", true);
        ltOptions.put("console", true); // Enable console logs
        browserOptions.setCapability("LT:Options", ltOptions);

        // Initialize the RemoteWebDriver
        String remoteUrl = "https://" + username + ":" + authkey + "@" + hub;
        driver = new RemoteWebDriver(new URL(remoteUrl), browserOptions);

    }

    @Test
    public void testFormSubmission() throws InterruptedException {
        // Open the website
        driver.get("https://the-internet.herokuapp.com/geolocation");

        // Validate page title
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);
        Assert.assertTrue(pageTitle.contains("The Internet"));

        // Click the "Where am I?" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement whereAmIButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div/button")));
        whereAmIButton.click();
        System.out.println("clicked");
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("lambda-perform-keyboard-events:{TAB}");
        Thread.sleep(1000);
        System.out.println("cross");
        ((JavascriptExecutor) driver).executeScript("lambda-perform-keyboard-events:{TAB}");
        Thread.sleep(1000);
        System.out.println("deny");
        ((JavascriptExecutor) driver).executeScript("lambda-perform-keyboard-events:{TAB}");
        Thread.sleep(1000);
        System.out.println("allow");

        ((JavascriptExecutor) driver).executeScript("lambda-perform-keyboard-events:ENTER");
        System.out.println("press enter");

////        ((JavascriptExecutor) driver).executeScript("lambda-perform-keyboard-events:{ENTER}");
//        Thread.sleep(1000);
//        System.out.println("third");
//        Actions actions = new Actions(driver);
//        actions.moveToElement(whereAmIButton).click().perform();

//        Thread.sleep(1000);

        // Handle the geolocation alert
//        try {
//            // Switch to the alert
//            Alert alert = driver.switchTo().alert();
//            // Accept the alert (grant geolocation permission)
//            alert.accept();
//            System.out.println("Geolocation alert accepted.");
//        } catch (NoAlertPresentException e) {
//            System.out.println("No geolocation alert present.");
//        }

        // Wait for geolocation result

        // Click the "Where am I?" button
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement whereAmIButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"content\"]/div/button")));
//        whereAmIButton.click();
//        System.out.println("clicked");
//
//        // Wait for a moment before sending keyboard events
//        Thread.sleep(2000);

        // Use Actions class to simulate TAB key press
//        Actions actions = new Actions(driver);
//        actions.sendKeys(Keys.TAB).perform();
//        System.out.println("TAB Pressed");
//
//        Thread.sleep(1000);
//
//        // Simulate another TAB key press
//        actions.sendKeys(Keys.TAB).perform();
//        System.out.println("TAB Pressed Again");
//
//        Thread.sleep(1000);
//
//        // Simulate ENTER key press
//        actions.sendKeys(Keys.RETURN).perform();
//        System.out.println("ENTER Pressed");

        // Optional: You can add more steps if needed
//        Thread.sleep(1000);




        Thread.sleep(80000);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the driver after execution
        }
    }
}
