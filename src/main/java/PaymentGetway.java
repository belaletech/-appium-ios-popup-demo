import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class PaymentGetway {

    // LambdaTest credentials
    String userName = "belalahmad";
    String accessKey = "sZhRHD9cmkV1nXNWEUw7psGAxRgN84ugdKqGMforKfXJCQhIU7";
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");

    private AndroidDriver driver;
    public static String status = "passed";

    @BeforeClass
    @Parameters({"device", "version", "platform"})
    public void setUp(String device, String version, String platform) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> ltOptions = new HashMap<>();

        ltOptions.put("build", "Thumbs up Pop-Up Android App");
        ltOptions.put("name", "Thumbs up Pop-up Test");
        ltOptions.put("w3c", true);
        ltOptions.put("platformName", platform);
        ltOptions.put("deviceName", device);
        ltOptions.put("platformVersion", version);
        ltOptions.put("isRealMobile", true);
        ltOptions.put("app", "lt://APP10160552421730389705760167");

        capabilities.setCapability("lt:options", ltOptions);

        String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";
        driver = new AndroidDriver(new URL(hub), capabilities);
    }

    @Test
    public void testTheInternetHerokuApp() throws InterruptedException {
        Thread.sleep(1000000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
