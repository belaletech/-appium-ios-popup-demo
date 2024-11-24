/* Appium 1.x */
/* import io.appium.java_client.AppiumDriver; */
/* Appium 2.x */
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.Set;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class iOSApp {
    /* Retrieve keys from https://accounts.lambdatest.com/security */
//    String userName = System.getenv("LT_USERNAME") == null ?
//            "LT_USERNAME" : System.getenv("LT_USERNAME");
//    String accessKey = System.getenv("LT_ACCESS_KEY") == null ?
//            "LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");
    /* Only used for app automation */
    /* String app_id = System.getenv("LT_APP_ID") == null ?
            "lt://proverbial-ios" : System.getenv("LT_APP_ID");
     */
//    String userName = "satnamlocance";
//    String accessKey = "zUCgUEvx5jiBZpsJN36o7FLyxfT09z5p3AWnjzDo9vU7C3GWR2";
    String userName = "belalahmad";
    String accessKey = "te3oXiTcYTkJv5UgKBra3MD4ruWSjrbq56LJ2SgRlrR8wURl6n";
    String grid_url = System.getenv("LT_GRID_URL") == null ?
            "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");

    private IOSDriver driver;
    public static String status = "passed";

    @BeforeClass
    @Parameters({"device", "version", "platform"})
    public void setUp(String device, String version, String platform) throws MalformedURLException
    {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("build", "[Build] Pop-Up Handling in iOS Web App");
        ltOptions.put("name", "[Name] Pop-Up Handling in iOS Web App");
        ltOptions.put("w3c", true);
        /* ltOptions.put("platformName", "iOS"); */
        /* ltOptions.put("deviceName", "iPhone 13"); */
        /* ltOptions.put("platformVersion", "15"); */
        ltOptions.put("platformName", platform);
        ltOptions.put("deviceName", device);
        ltOptions.put("platformVersion", version);
        ltOptions.put("isRealMobile", true);
//        ltOptions.put("tunnelName","c15bf560-5a67-4dae-9847-c892bfb18677");
        /* Device from a DC in India */
//        ltOptions.put("geoLocation", "IN");
//        ltOptions.put("network", false);
//        ltOptions.put("visual", true);
//        ltOptions.put("devicelog", true);
//        ltOptions.put("deviceOrientation", "portrait");
//        ltOptions.put("devicelog", true);
//        ltOptions.put("tunnel",true);
//        ltOptions.put("tunnelName","c15bf560-5a67-4dae-9847-c892bfb18677");
        ltOptions.put("autoAcceptAlerts", true);
        /* Browser Combination */
//        ltOptions.put("browserName", "Safari");
//        ltOptions.put("browserVersion", "latest");

        capabilities.setCapability("lt:options", ltOptions);


        String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";
        /* Appium 1.x*/
        /* driver = new AppiumDriver(new URL(hub), capabilities); */
        driver = new IOSDriver(new URL(hub), capabilities);

    }

    @Test
    public void testTheInternetHerokuApp() throws InterruptedException {
//        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

        driver.get("https://dev-portal.locance.net/");
        Thread.sleep(5000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

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

        Thread.sleep(2000);

        // Wait until the login button is clickable and click it
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/main/section/div/div/div/form/div[2]/button")
        ));
        loginButton.click();

        Thread.sleep(5000);
        driver.get("https://dev-portal.locance.net/tools/session_id_creator");
        Thread.sleep(5000);
        // Wait until the session ID input field is present and enter the session ID
        WebElement sessionIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("session_id")));
        sessionIdField.sendKeys("SessionID123ABCD");

        // Wait until the 'Go' button is clickable and click it
        WebElement goButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGo")));
        goButton.click();

        Thread.sleep(5000);







//
//            /* Validate page title */
//            String pageTitle = driver.getTitle();
//            System.out.println("Page Title: " + pageTitle);
//            Assert.assertTrue(pageTitle.contains("The Internet"));
//
//            /* Click on the "Where Am I?" button */
//            WebElement whereAmIButton = (WebElement) driver.findElement(By.cssSelector("button"));
//            whereAmIButton.click();
//            /* Can be made dynamic, blocking wait is not a good practice */
//            Thread.sleep(3000);
//
            /* Get all contexts */
            Set<String> contextNames = driver.getContextHandles();
            for (String contextName : contextNames)
            {
                System.out.println("Available context: " + contextName);
            }
//
//            /* Print the current context */
            String initialContext = driver.getContext();
            System.out.println("Initial context: " + initialContext);
            /* Can be made dynamic, blocking wait is not a good practice */
            Thread.sleep(3000);
//
            /* Switch to native app context (index 0 is native) */
            driver.context((String) contextNames.toArray()[0]);
            String nativeContext = driver.getContext();
            System.out.println("Switched to context: " + nativeContext);
            /* Can be made dynamic, blocking wait is not a good practice */
            Thread.sleep(3000);
//
            /* Click on "Allow Once" button */
            driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Allow Once']")).click();
            /* Can be made dynamic, blocking wait is not a good practice */
            Thread.sleep(3000);

            /* Switch back to WebView context (index 1 is WebView) */
            driver.context((String) contextNames.toArray()[1]);
            String webviewContext = driver.getContext();
            System.out.println("Switched back to context: " + webviewContext);
//            scrollPageToTop();
        scrollToTop();
        System.out.println("Belal");
//
//            /* Assert the context switch */
//            Assert.assertTrue(webviewContext.contains("WEBVIEW"),
//                    "Context did not switch back to WebView!");
//            System.out.println("Successfully retrieved the latitude & longitude");
//        }
//        catch (Exception e)
//        {
//            status = "failed";
//            driver.executeScript("lambda-status=failed");
//            driver.quit();
//        }
   }
    public void scrollToTop() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.8); // Start near the bottom
        int scrollEnd = (int) (dimension.getHeight() * 0.2);   // End near the top

        // Perform multiple swipes up to ensure reaching the top
        for (int i = 0; i < 5; i++) {  // Adjust this count as needed
            new TouchAction<>(driver)
                    .press(PointOption.point(0,scrollEnd ))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(0, scrollStart))
                    .release()
                    .perform();

            // Optional: Add a short delay between scrolls
        }
    }




    @AfterClass
    public void tearDown()
    {
        if (driver != null)
        {
            /* Update Status on the LambdaTest dashboard */
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}