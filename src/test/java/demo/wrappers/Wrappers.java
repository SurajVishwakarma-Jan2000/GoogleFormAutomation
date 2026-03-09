package demo.wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    ChromeDriver driver;

    public Wrappers(ChromeDriver driver) {
        this.driver = driver;
    }

    // Open URL
    public void openForm(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Enter text
    public void enterText(By locator, String value) {
        try {
            WebElement element = driver.findElement(locator);

            element.sendKeys(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Click element
    public void clickElement(By locator) {
        try {

            WebElement element = driver.findElement(locator);
            element.click();

        } catch (Exception e) {

            try {
                WebElement element = driver.findElement(locator);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Select checkbox
    public void selectCheckbox(String option) {
        try {
            clickElement(By.xpath("//span[normalize-space()='" + option + "']"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Dropdown selection
    public void dropDownClick(String dropDownText) {
        try {

            WebElement element = driver.findElement(
                    By.xpath("//div[contains(@class,'QXL7Te')]//span[text()='" + dropDownText
                            + "']"));

            element.click();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Generate epoch message
    public String generateEpochMessage(String text) {

        long epochTime = Instant.now().getEpochSecond();
        return text + " " + epochTime;
    }

    // Date minus days
    public String getDateMinusDays(int days) {

        LocalDate date = LocalDate.now().minusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        return date.format(formatter);
    }

    // Enter time
    public void enterTime(String hour, String minute) {

        try {

            enterText(By.xpath("//input[@aria-label='Hour']"), hour);
            enterText(By.xpath("//input[@aria-label='Minute']"), minute);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Success message
    public String getSuccessMessage() {

        try {

            WebElement message = driver.findElement(
                    By.xpath("//div[contains(@class,'vHW8K')]"));

            return message.getText();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
