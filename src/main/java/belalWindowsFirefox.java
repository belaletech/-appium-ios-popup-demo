import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class belalWindowsFirefox {
    private RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        String username = "belalahmad";
        String authkey = "wKW4T7dT73bNkNpPZmwdPUafBxJTiavujikkUVziNW02GzT4jB";
        String hub = "hub.lambdatest.com/wd/hub";

        FirefoxOptions browserOptions = new FirefoxOptions();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("permissions.default.geo", 1);

        // Setting preferences to allow geolocation permissions
        firefoxProfile.setPreference("geo.prompt.testing", true);
        firefoxProfile.setPreference("geo.prompt.testing.allow", true);
        browserOptions.setProfile(firefoxProfile);

        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "windowsFirefox");
        ltOptions.put("project", "Belal");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        String remoteUrl = "https://" + username + ":" + authkey + "@" + hub;
        driver = new RemoteWebDriver(new URL(remoteUrl), browserOptions);
    }

    @Test
    public void testGeolocation() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/geolocation?trk=public_post_comment-text");
        Thread.sleep(10000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on the "Where am I?" button to trigger geolocation request
        WebElement whereAmIButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));
        whereAmIButton.click();
        Thread.sleep(5000);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
