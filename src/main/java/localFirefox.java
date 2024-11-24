import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class localFirefox {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        // Set up WebDriverManager for Firefox
        WebDriverManager.firefoxdriver().setup();

        // Create Firefox profile and set geolocation preferences
        FirefoxProfile firefoxProfile = new FirefoxProfile();
//        firefoxProfile.setPreference("permissions.default.geo", 1); // 1 = Allow geolocation
//        firefoxProfile.setPreference("geo.prompt.testing", true); // Enable testing geolocation prompt
//        firefoxProfile.setPreference("geo.prompt.testing.allow", true); // Automatically allow

        // Apply profile to Firefox options
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(firefoxProfile);

        // Initialize the Firefox driver with the options
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
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
        System.out.println("Belal");

        // Wait for the latitude and longitude to display
        Thread.sleep(7000); // Adjust this if necessary for slower loading times
    }

//    @AfterTest
//    public void tearDown() {
//        // Close the browser after the test
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
