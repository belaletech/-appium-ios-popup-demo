import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class ZupeeIosNative {

    String userName = "belalahmad";
    String accessKey = "te3oXiTcYTkJv5UgKBra3MD4ruWSjrbq56LJ2SgRlrR8wURl6n";
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");

    private IOSDriver driver;
    public static String status = "passed";

    @BeforeClass
    @Parameters({"device", "version", "platform"})
    public void setUp(String device, String version, String platform) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();

        // Adding provided capabilities
        ltOptions.put("build", "[Build] ZupeeIosNative");
        ltOptions.put("name", "[Name] ZupeeIosNative Test");
        ltOptions.put("w3c", true);
        ltOptions.put("w3c", true);
        ltOptions.put("platformName", platform);
        ltOptions.put("deviceName", device);
        ltOptions.put("platformVersion", version);
        ltOptions.put("isRealMobile", true);
        ltOptions.put("fixedUDID","00008120-000675502E0B401E");
        ltOptions.put("autoAcceptAlerts", true);
        ltOptions.put("geoLocation", "IN");
        ltOptions.put("latitude", "28.6139");
        ltOptions.put("longitude", "77.2090");
        ltOptions.put("device_log", true);
        ltOptions.put("idleTimeout", 300);
        ltOptions.put("network", false);
        ltOptions.put("video", true);
        ltOptions.put("visual", false);
        ltOptions.put("enableNetworkThrottling", false);
        ltOptions.put("nativeWebScreenshot", true);
        ltOptions.put("isAppAutomate", true);
        ltOptions.put("resolution", "1290 x 2796 px");
        ltOptions.put("app", "lt://APP10160351951731753092182120");

        capabilities.setCapability("lt:options", ltOptions);

        String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";
        driver = new IOSDriver(new URL(hub), capabilities);
    }

    @Test
    public void testTheInternetHerokuApp() throws InterruptedException {
        Thread.sleep(2000);
        scrollToTop();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type=\"XCUIElementTypeOther\" and ./parent::*[@name=\"languageRightArrow\"]]/*[@type=\"XCUIElementTypeOther\"]/*[2]")));
        btn1.click();
        WebElement btn2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name=\"enter amount here\"]")));
        btn2.sendKeys("00000000");
        Thread.sleep(10000);
    }

    public void scrollToTop() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.8); // Start near the bottom
        int scrollEnd = (int) (dimension.getHeight() * 0.2);   // End near the top

        // Perform multiple swipes up to ensure reaching the top
        for (int i = 0; i < 5; i++) {  // Adjust this count as needed
            new TouchAction<>(driver)
                    .press(PointOption.point(0, scrollEnd))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(0, scrollStart))
                    .release()
                    .perform();

            // Optional: Add a short delay between scrolls
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            /* Update Status on the LambdaTest dashboard */
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
