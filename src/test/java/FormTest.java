import java.io.File;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import static org.openqa.selenium.Keys.CONTROL;
import static org.openqa.selenium.Keys.ENTER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FormTest {

    @Test
    public void testFormSubmission() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/automation-practice-form");
        Thread.sleep(2000); // Sleep for 2 seconds
        // Fill text fields
        driver.findElement(By.id("firstName")).sendKeys("Shakera");
        driver.findElement(By.id("lastName")).sendKeys("Ema");
        driver.findElement(By.id("userEmail")).sendKeys("shakerajannatema@gmail.com");

        // Select Female by clicking label
        driver.findElement(By.cssSelector("label[for='gender-radio-2']")).click();

        driver.findElement(By.id("userNumber")).sendKeys("01854088947");

        // Set Date of Birth
        WebElement dob = driver.findElement(By.id("dateOfBirthInput"));
        dob.sendKeys(Keys.chord(CONTROL, "a"), "20 Oct 2001", ENTER);

        // Subject input (autocomplete)
        WebElement subjectInput = driver.findElement(By.id("subjectsInput"));
        subjectInput.sendKeys("English");
        subjectInput.sendKeys(Keys.ENTER);

        // Hobbies
        WebElement hobbyLabel = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hobbyLabel);

        // Upload Picture
        String filePath = new File("src/test/resources/sample.png").getAbsolutePath(); // replace with actual path
        driver.findElement(By.id("uploadPicture")).sendKeys("/Users/shakera/Downloads/Study/3-2/Soft V & V/Lab/try1/demo/src/test/resources/ema1.png");

        // Current address
        // Close modal if present
        if (driver.findElements(By.id("closeLargeModal")).size() > 0) {
            WebElement closeButton = driver.findElement(By.id("closeLargeModal"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
            
}

// Scroll and interact with currentAddress
WebElement address = driver.findElement(By.id("currentAddress"));
((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", address);
Thread.sleep(500);
address.sendKeys("Dhaka, Bangladesh");
        // Select state & city (interacting with dropdowns)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

        driver.findElement(By.id("react-select-3-input")).sendKeys("NCR");
        driver.findElement(By.id("react-select-3-input")).sendKeys(Keys.ENTER);

        driver.findElement(By.id("react-select-4-input")).sendKeys("Delhi");
        driver.findElement(By.id("react-select-4-input")).sendKeys(Keys.ENTER);

        // Submit
        driver.findElement(By.id("submit")).click();

        Thread.sleep(3000); // View confirmation
        // Close the confirmation modal
        // WebElement closeButton = driver.findElement(By.id("closeLargeModal"));
        // if (closeButton.isDisplayed()) {
        //     closeButton.click();
        // } else {
        //     System.out.println("Close button not found or not displayed.");
        // }
        // Thread.sleep(2000); // Wait for the modal to close
        driver.quit();
        System.out.println("✅ Form submission test completed.");
    }
}
