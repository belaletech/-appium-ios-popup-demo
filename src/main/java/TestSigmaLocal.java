import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class TestSigmaLocal {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        // Use WebDriver Manager to manage the ChromeDriver binary
        WebDriverManager.chromedriver().setup();

        // Configure ChromeOptions if needed
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Start in maximized mode

        // Initialize ChromeDriver
        driver = new ChromeDriver(options);
    }

    @Test
    public void testFormSubmission() throws InterruptedException {
        // Open the website
        driver.get("https://parent.devy.skool.sg/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Scroll to the element and then click
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-container\"]/div[3]/div/div[1]/div/div/div/div/form/div[5]/div[2]/button/span[1]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton); // Using JS click
        System.out.println("Clicked login button");

        // Form fill
        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"email\"]")));
        email.sendKeys("sn2hulk+40008691@gmail.com");
        System.out.println("Entered email");

        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"password\"]")));
        password.sendKeys("sN@202401&hK=");
        System.out.println("Entered password");

        WebElement clickLoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-container\"]/div[3]/div/div[1]/div/div/div/div/form/div[7]/div/button/span[1]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickLoginButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickLoginButton);
        System.out.println("Clicked login button");

        Thread.sleep(2000);

        WebElement dropButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-container\"]/div[3]/div/div/ul/div/img")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropButton);
        System.out.println("Clicked drop button");

        WebElement secondDropButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-container\"]/div[4]/div[2]/div[2]/div/div/div[1]/div[1]/div/div[1]/div/div")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", secondDropButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", secondDropButton);
        System.out.println("Clicked second drop button");

        WebElement selectStudent = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-container\"]/div[4]/div[2]/div[2]/div/div/div[1]/div[1]/div/div[2]/div[4]/span")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectStudent);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectStudent);
        System.out.println("Selected student");

        Thread.sleep(20000);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser after tests
        }
    }
}
