import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class IOSDarkLightModeTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        // Set up Desired Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 14");  // Choose your desired iPhone model
        capabilities.setCapability("platformVersion", "16.0");
        capabilities.setCapability("app", "lt://APP10160471311730037673584853"); // Provide your app URL hosted on LambdaTest
        capabilities.setCapability("userInterfaceStyle", "light"); // Start with Light Mode
        capabilities.setCapability("build", "iOS Dark/Light Mode Automation");
        capabilities.setCapability("name", "Theme Switch Test");
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("video", true); // Enable video recording of the session for debugging
//        capabilities.setCapability("automationName", "XCUITest");

        // Set LambdaTest credentials
        String username = "belalahmad";
        String accessKey = "te3oXiTcYTkJv5UgKBra3MD4ruWSjrbq56LJ2SgRlrR8wURl6n";
        String hubURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

        // Initialize Appium Driver for LambdaTest
        AppiumDriver driver = new IOSDriver(new URL(hubURL), capabilities);

        try {
            // Perform initial light mode checks
            System.out.println("Running in Light Mode...");
            Thread.sleep(3000); // Add appropriate waits for elements to load or perform actions
            // Add validations for Light Mode
            // For example: check the presence or appearance of an element

            // Switch to Dark Mode mid-test
            System.out.println("Switching to Dark Mode...");
            driver.executeScript("mobile: settings", Map.of("userInterfaceStyle", "dark"));
            Thread.sleep(3000); // Wait for theme switch to take effect
            // Add validations for Dark Mode
            // For example: check the presence or appearance of an element

            // Switch back to Light Mode if needed
            System.out.println("Switching back to Light Mode...");
            driver.executeScript("mobile: settings", Map.of("userInterfaceStyle", "light"));
            Thread.sleep(3000); // Wait for theme switch to take effect
            // Add further validations as needed

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // End the session
            driver.quit();
        }
    }
}
