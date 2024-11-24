import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class LocalWindowsChrome {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        // Set up WebDriverManager for Chrome
        WebDriverManager.chromedriver().setup();

        // Configure ChromeOptions for geolocation preferences
        ChromeOptions browserOptions = new ChromeOptions();

        // Set geolocation preferences
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 1); // Allow geolocation
        prefs.put("googlegeolocationaccess.enabled", true); // Enable Google location services
        browserOptions.setExperimentalOption("prefs", prefs);

        // Apply preferences and maximize browser
        browserOptions.addArguments("--start-maximized");

        // Initialize the Chrome driver with the options
        driver = new ChromeDriver(browserOptions);
    }

    @Test
    public void testGeolocation() throws InterruptedException {
        // Open the target website
        driver.get("https://the-internet.herokuapp.com/geolocation?trk=public_post_comment-text");

        // Wait for the page to load and locate the "Where am I?" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement whereAmIButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/button")));

        // Click the button to trigger geolocation
        whereAmIButton.click();
        System.out.println("Clicked on 'Where am I?'");

        // Wait for the latitude and longitude to display
        Thread.sleep(7000); // Adjust this if necessary for slower loading times
    }

    @AfterTest
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}
