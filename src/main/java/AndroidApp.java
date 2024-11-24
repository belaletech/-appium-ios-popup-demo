import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Set;

public class AndroidApp {

//    String userName = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" : System.getenv("LT_USERNAME");
//    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");
    String userName = "satnamlocance";
    String accessKey = "zUCgUEvx5jiBZpsJN36o7FLyxfT09z5p3AWnjzDo9vU7C3GWR2";
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");

    private AndroidDriver driver;
    public static String status = "passed";

    @BeforeClass
    @Parameters({"device", "version", "platform"})
    public void setUp(String device, String version, String platform) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("build", "Pop-Up Handling in Android Web App");
        ltOptions.put("name", "Pop-Up Handling in Android Web App");
        ltOptions.put("w3c", true);
        ltOptions.put("platformName", platform);
        ltOptions.put("deviceName", device);
        ltOptions.put("platformVersion", version);
        ltOptions.put("isRealMobile", true);
//        ltOptions.put("geoLocation", "IN");
//        ltOptions.put("network", false);
//        ltOptions.put("visual", true);
//        ltOptions.put("devicelog", true);
        ltOptions.put("tunnelName","8de01228-4f63-4d9c-a9fe-ab5ea2887a46");
        ltOptions.put("tunnel",true);
        ltOptions.put("browserName", "Chrome");
        ltOptions.put("browserVersion", "latest");

        capabilities.setCapability("lt:options", ltOptions);

        String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";
        driver = new AndroidDriver(new URL(hub), capabilities);
    }

    @Test
    public void testTheInternetHerokuApp() throws InterruptedException {

        driver.get("https://dev-portal.locance.net/");
        Thread.sleep(5000);

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

        Thread.sleep(3000);
        driver.get("https://dev-portal.locance.net/tools/session_id_creator");
        Thread.sleep(3000);
        // Wait until the session ID input field is present and enter the session ID
        WebElement sessionIdField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("session_id")));
        sessionIdField.sendKeys("SessionID123ABCD");

        // Wait until the 'Go' button is clickable and click it
        WebElement goButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGo")));
        goButton.click();
        Thread.sleep(5000);

        // To accept/block the popup, you need to switch the context to “NATIVE_APP“ and click on the Allow/Block button.
        driver.context("NATIVE_APP");
        Thread.sleep(1000);
        WebElement locationAllow=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//android.widget.Button[@text='Allow']")));
        locationAllow.click();
//      driver.findElement(By.xpath(".//android.widget.Button[@text='Allow']")).click();
        Thread.sleep(5000);
        System.out.println("Successfully handled location pop up for Android chrome");

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
