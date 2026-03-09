package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class TestCases {
    ChromeDriver driver;
    Wrappers wrappers;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */
    @Test
    public void testCase01() throws InterruptedException {

        wrappers = new Wrappers(driver);

        System.out.println("Opening Google Form");
        wrappers.openForm("https://forms.gle/wjPkzeSEk1CM7KgGA");

        Thread.sleep(5000);

        System.out.println("Entering learner name");
        wrappers.enterText(
                By.xpath("(//input[@type='text'])[1]"),
                "Crio Learner");

        Thread.sleep(1000);

        String message = wrappers.generateEpochMessage(
                "I want to be the best QA Engineer!");

        System.out.println("Entering message with epoch timestamp");
        wrappers.enterText(
                By.xpath("//textarea"),
                message);

        Thread.sleep(1000);

        wrappers.clickElement(By.id("i19"));

        Thread.sleep(1000);

        System.out.println("Selecting skills");
        wrappers.selectCheckbox("Java");
        wrappers.selectCheckbox("Selenium");
        wrappers.selectCheckbox("TestNG");

        Thread.sleep(1000);

        // Open dropdown
        wrappers.clickElement(By.xpath("//div[@role='listbox']"));

        Thread.sleep(2000);

        // Select option
        wrappers.dropDownClick("Mr");

        Thread.sleep(1000);

        String date = wrappers.getDateMinusDays(7);

        wrappers.enterText(
                By.xpath("//input[@type='date']"),
                date);

        Thread.sleep(1000);

        wrappers.enterTime("07", "30");

        Thread.sleep(1000);

        System.out.println("Submitting the form");
        wrappers.clickElement(By.xpath("//span[text()='Submit']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'vHW8K')]")));

        String successMessage = wrappers.getSuccessMessage();
        System.out.println(successMessage);
        System.out.println("Test Finished");
    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}