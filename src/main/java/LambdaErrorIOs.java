import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class  LambdaErrorIOs {

    String userName = "belalahmad";
    String accessKey = "07DC6fZhsIW0zC4nNc00IPj7eSqL2SVxMDx12N4SKslGihpTvq";
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");

    private IOSDriver driver;
    public static String status = "passed";

    @BeforeClass
    @Parameters({"device", "version", "platform"})
    public void setUp(String device, String version, String platform) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> ltOptions = new HashMap<>();

        // Capabilities
        ltOptions.put("appProfiling", true);
//        ltOptions.put("appium:app", "stock");
        ltOptions.put("app","lt://APP10160301621732272758226453");
        ltOptions.put("appium:autoAcceptAlerts", false);
        ltOptions.put("appium:automationName", "XCUITest");
        ltOptions.put("appium:bundleID", "com.vorwerk.cookidoo.test");
//        ltOptions.put("appium:deviceName", "iPhone.*");
        ltOptions.put("appium:language", "en");
        ltOptions.put("appium:locale", "en_GB");
        ltOptions.put("appium:newCommandTimeout", "60");
//        ltOptions.put("appium:platformVersion", "18");
        ltOptions.put("appium:privateCloud", false);
        ltOptions.put("platformName", platform);
        ltOptions.put("deviceName", device);
        ltOptions.put("platformVersion", version);
        ltOptions.put("appiumVersion", "2.2.1");
        ltOptions.put("build", "IOS-TEST-v.28868446.474755666");
        ltOptions.put("devicelog", true);
        ltOptions.put("isRealMobile", true);
//        ltOptions.put("fixedIP","00008103-0004149E227B001E");
        ltOptions.put("lt_timezone", "Cairo");
        ltOptions.put("name", "My Recipes - Created Collection - Displaying recipes in created Collection");
        ltOptions.put("network", false);
//        ltOptions.put("platformName", "ios");
        ltOptions.put("project", "Cookidoo-iOS-App");
        ltOptions.put("region", "EU");
        ltOptions.put("resignApp", true);
        ltOptions.put("nativeWebScreenshot", true);
        ltOptions.put("loggingPrefs", new HashMap<String, String>() {{
            put("browser", "ALL");
            put("driver", "ALL");
            put("server", "ALL");
        }});
        ltOptions.put("performance", false);
        ltOptions.put("resolution", "1179 x 2556 px");
        ltOptions.put("video", true);
        ltOptions.put("visual", false);
        ltOptions.put("w3c", true);

        capabilities.setCapability("lt:options", ltOptions);

        String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";
        driver = new IOSDriver(new URL(hub), capabilities);
    }


    @Test
    public void testTheInternetHerokuApp() throws InterruptedException {
        Thread.sleep(8000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Sm automating");

//
//        WebElement el1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@value=\"SIGN IN OR CREATE ACCOUNT\"]")));
//        System.out.println("1. signup");
//        el1.click();
        scrollToTop();
        Thread.sleep(5000);


    }


    public void scrollToTop() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.8); // Start near the bottom
        int scrollEnd = (int) (dimension.getHeight() * 0.2);   // End near the top

        for (int i = 0; i < 5; i++) {
            new TouchAction<>(driver)
                    .press(PointOption.point(0, scrollStart))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(0, scrollEnd))
                    .release()
                    .perform();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
