import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class Autozone {

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
        ltOptions.put("build", "[Build] AutoZone");
        ltOptions.put("name", "[Name] AutoZone");
        ltOptions.put("platformName", platform);
        ltOptions.put("deviceName", device);
        ltOptions.put("platformVersion", version);
        ltOptions.put("isRealMobile", true);
        ltOptions.put("autoAcceptAlerts", true);
//        ltOptions.put("resignApp",false);
        ltOptions.put("network", false);
//        ltOptions.put("video", true);
        ltOptions.put("visual", false);
//        ltOptions.put("enableNetworkThrottling", false);
//        ltOptions.put("nativeWebScreenshot", true);
//        ltOptions.put("isAppAutomate", true);
//        ltOptions.put("resolution", "1290 x 2796 px");
        ltOptions.put("app", "lt://APP10160301621732113415644423");
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
