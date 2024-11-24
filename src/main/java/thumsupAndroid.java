import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
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

public class thumsupAndroid {

    // LambdaTest credentials

//    String getUserName="mobile@123";
//    String userName="mobileautoservic_w86Uka";
//    String accessKey="";
    String userName = "belalahmad";
    String accessKey = "te3oXiTcYTkJv5UgKBra3MD4ruWSjrbq56LJ2SgRlrR8wURl6n"; // Make sure to keep this secure
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");
//    String grid_url="hub.browserstack.com";
    private AndroidDriver driver;
    public static String status = "passed";

    @BeforeClass
    @Parameters({"device", "version", "platform"})
    public void setUp(String device, String version, String platform) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities(); // Create an instance of DesiredCapabilities

        // Set LambdaTest options
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "Thank you pop up");
        ltOptions.put("name", "Thumbs up Pop-up Test");
        ltOptions.put("w3c", true);
        ltOptions.put("platformName", platform);
        ltOptions.put("deviceName", device);
        ltOptions.put("platformVersion", version);
        ltOptions.put("isRealMobile", true);
        ltOptions.put("app", "lt://APP1016031721731921260741675");
//        ltOptions.put("app","bs://9307f38f4d9e492868d3df8c1b2e41486df728af");
        ltOptions.put("appProfiling",true);
        ltOptions.put("appiumVersion", "2.0"); // Specify Appium version
        ltOptions.put("autoGrantPermissions", true); // Automatically grant permissions
        ltOptions.put("visual", true); // Enable visual testing if needed
        ltOptions.put("geoLocation", "IN");
        ltOptions.put("timezone", "UTC-08:00"); // Set timezone, adjust as necessary
        ltOptions.put("devicelog", true); // Enable device logs
        ltOptions.put("network", false); // Disable network if not required
        ltOptions.put("enableNetworkThrottling", false); // Adjust as needed
        ltOptions.put("idleTimeout",600);
        ltOptions.put("newCommandTimeout", "300"); // Timeout for new commands
        ltOptions.put("console", "false"); // Adjust logging preferences as needed

        capabilities.setCapability("lt:options", ltOptions); // Set LambdaTest options in capabilities

        String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";
        driver = new AndroidDriver(new URL(hub), capabilities); // Initialize the AndroidDriver
    }

    @Test
    public void testTheInternetHerokuApp() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id=\"com.randstadrisesmart.talentmobility.auto:id/nextIcon\"]")));
        btn1.click();
        WebElement btn2=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id=\"com.randstadrisesmart.talentmobility.auto:id/nextIcon\"]")));
        btn2.click();
        WebElement btn3=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id=\"com.randstadrisesmart.talentmobility.auto:id/nextIcon\"]")));
        btn3.click();
        WebElement btn4=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id=\"com.randstadrisesmart.talentmobility.auto:id/nextIcon\"]")));
        btn4.click();
        WebElement btn5=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id=\"com.randstadrisesmart.talentmobility.auto:id/btnLargeTitle\"]")));
        btn5.click();
        WebElement btn6=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id=\"com.randstadrisesmart.talentmobility.auto:id/login_edittext_email\"]")));
        btn6.sendKeys("ivp3@yopmail.com");
        driver.hideKeyboard();

        Thread.sleep(1000);

//        driver.executeScript("mobile: shell", ImmutableMap.of("command", "input keyevent 61"));  // KEYCODE_ENTER for "Done" button

        WebElement btn7=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id=\"com.randstadrisesmart.talentmobility.auto:id/login_button_next\"]")));
        btn7.click();

        System.out.println("belal");
        WebElement btn8=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id=\"com.randstadrisesmart.talentmobility.auto:id/login_edittext_password\"]")));
        btn8.sendKeys("P@ssw0rd@123");
        driver.hideKeyboard();
        System.out.println("Ahmad");
        WebElement btn9=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id=\"com.randstadrisesmart.talentmobility.auto:id/btnLargeTitle\"]")));
        btn9.click();
        Thread.sleep(800000);

//        WebElement btn10=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id=\"com.randstadrisesmart.talentmobility.auto:id/btnLargeTitle\"]")));
//        btn10.click();
//
//        WebElement btn11=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id=\"com.randstadrisesmart.talentmobility.auto:id/btnLargeTitle\"]")));
//
//
//        btn11.click();
//        WebElement btn12=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text=\"19\"]")));
//        btn12.click();
//



//        Thread.sleep(1000000); // Replace with actual test code
//        Thread.sleep(1000000);
//        Thread.sleep(1000000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
