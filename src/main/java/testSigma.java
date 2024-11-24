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

public class testSigma {
    private RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        // LambdaTest credentials
        String username = "belalahmad";
        String authkey = "jQ9OsCJftyrSYZhq6xnBHFSRliekbXO3IIU4ptFXpQhSiOZNEe";
//        String hub="hub-virginia.lambdatest.com/wd/hub";
        String hub = "hub.lambdatest.com/wd/hub";
//        String hub="hub-mumbai.lambdatest.com/wd/hub";
        // Set SafariOptions with platform and browser version
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("latest");

        // Set LambdaTest specific options
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "virginia hub testSigma new");
//        ltOptions.put("browserName", "Chrome");
        ltOptions.put("project", "Belal");
        ltOptions.put("w3c", true); // Ensure W3C compliance
        ltOptions.put("network",true);
        ltOptions.put("tunnel","true");
        ltOptions.put("tunnelName","TestSigma");
//        ltOptions.put("Provider","LW-SFO");
//        ltOptions.put("Provider","LW-UK");
//        ltOptions.put("Provider","LT-MUM");
//        ltOptions.put("fixedIP","10.81.49.134");
//        ltOptions.put("selenium_version", "4.13.0"); // Use the specific Selenium version if required

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
        driver.get("https://parent.devy.skool.sg/login");
        System.out.println("naviage");
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement loginButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-container\"]/div[3]/div/div[1]/div/div/div/div/form/div[5]/div[2]/button/span[1]")));
        loginButton.click();
        System.out.println("cliked login button");

        //form fill
        WebElement Email=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"email\"]")));
        Email.sendKeys("sn2hulk+40008691@gmail.com");
        System.out.println("send email");
        WebElement password=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"password\"]")));
        password.sendKeys("sN@202401&hK=");
        System.out.println("Filled the form");

        WebElement ClickLoginbutton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-container\"]/div[3]/div/div[1]/div/div/div/div/form/div[7]/div/button/span[1]")));
        ClickLoginbutton.click();
        System.out.println("click login button");

        Thread.sleep(2000);



        WebElement dropButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-container\"]/div[3]/div/div/ul/div/img")));
        dropButton.click();
        System.out.println("click drop btn");

        WebElement SecondDropButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-container\"]/div[4]/div[2]/div[2]/div/div/div[1]/div[1]/div/div[1]/div/div")));
        SecondDropButton.click();
        System.out.println("second drop button");

        WebElement selectStudent=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-container\"]/div[4]/div[2]/div[2]/div/div/div[1]/div[1]/div/div[2]/div[4]/span")));
        selectStudent.click();
        System.out.println("ending");
        Thread.sleep(20000);

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser after tests
        }
    }
}
