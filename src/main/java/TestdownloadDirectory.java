import io.appium.java_client.safari.options.SafariOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class TestdownloadDirectory {
    private RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        // LambdaTest credentials
        String username = "belalahmad";
        String authkey = "07DC6fZhsIW0zC4nNc00IPj7eSqL2SVxMDx12N4SKslGihpTvq";
        String hub = "hub.lambdatest.com/wd/hub";

        // Set SafariOptions with platform and browser version
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("129");

        // Set LambdaTest specific options
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "download file and upload file");
//        ltOptions.put("browserName", "Chrome");
        ltOptions.put("project", "Belal");
        ltOptions.put("w3c", true); // Ensure W3C compliance
//        ltOptions.put("selenium_version", "4.13.0"); // Use the specific Selenium version if required
//        ltOptions.put("tunnelName","c15bf560-5a67-4dae-9847-c892bfb18677");
//        ltOptions.put("tunnel",true);
        ltOptions.put("console", true); // Enable console logs
        ltOptions.put("network", true); // Enable network logs
        ltOptions.put("debug", true); // Enable debugging if needed

        // Add ltOptions to SafariOptions
        browserOptions.setCapability("LT:Options", ltOptions);

        // Construct the URL
        String remoteUrl = "https://" + username + ":" + authkey + "@" + hub;

        // Initialize RemoteWebDriver with SafariOptions
        driver = new RemoteWebDriver(new URL(remoteUrl), browserOptions);
    }

    @Test
    public void testFormSubmission() throws InterruptedException {
        // Open the website
        driver.get("https://belaletech.github.io/testDirecotry/");
        Thread.sleep(2000);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement acceptCookies=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"downloadButton\"]")));
        acceptCookies.click();

        Thread.sleep(2000);
        // Open file in the browser
        driver.get("C:\\Users\\ltuser\\Downloads\\testdirectory.txt");
        Thread.sleep(2000);
        System.out.println("read");

        // Upload the file to another site
        driver.get("https://cgi-lib.berkeley.edu/ex/fup.html");
        WebElement addFile = driver.findElement(By.xpath("/html/body/form/input[1]"));
        Thread.sleep(1000);
        addFile.sendKeys("C:\\Users\\ltuser\\Downloads\\testdirectory.txt");

        // Wait until the 'SIGN IN' button is clickable and click it to accept cookies

        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser after tests
        }
    }
}
