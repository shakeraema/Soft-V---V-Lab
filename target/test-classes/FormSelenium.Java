import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import static org.openqa.selenium.Keys.CONTROL;
import static org.openqa.selenium.Keys.ENTER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FormSelenium {

    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        try {
            driver.get("https://demoqa.com/automation-practice-form");
            Thread.sleep(2000); // Wait for page to load

            driver.findElement(By.id("firstName")).sendKeys("Shakera");
            driver.findElement(By.id("lastName")).sendKeys("Ema");
            driver.findElement(By.id("userEmail")).sendKeys("shakerajannatema@gmail.com");

            driver.findElement(By.cssSelector("label[for='gender-radio-2']")).click();
            driver.findElement(By.id("userNumber")).sendKeys("01854088947");

            WebElement dob = driver.findElement(By.id("dateOfBirthInput"));
            dob.sendKeys(Keys.chord(CONTROL, "a"), "20 Oct 2001", ENTER);

            WebElement subjectInput = driver.findElement(By.id("subjectsInput"));
            subjectInput.sendKeys("English");
            subjectInput.sendKeys(Keys.ENTER);

            WebElement hobbyLabel = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hobbyLabel);

            // Upload file
            String filePath = new File("src/test/resources/ema1.png").getAbsolutePath();
            driver.findElement(By.id("uploadPicture")).sendKeys(filePath);

            WebElement address = driver.findElement(By.id("currentAddress"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", address);
            Thread.sleep(500);
            address.sendKeys("Dhaka, Bangladesh");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,300)");

            driver.findElement(By.id("react-select-3-input")).sendKeys("NCR", ENTER);
            driver.findElement(By.id("react-select-4-input")).sendKeys("Delhi", ENTER);

            driver.findElement(By.id("submit")).click();
            Thread.sleep(3000);

            // Validate modal content (optional)
            WebElement modalTitle = driver.findElement(By.id("example-modal-sizes-title-lg"));
            String message = modalTitle.getText();
            if (message.equals("Thanks for submitting the form")) {
                System.out.println("✅ Submission confirmed: " + message);
            } else {
                System.out.println("❌ Unexpected modal message: " + message);
            }

        } catch (Exception e) {
            System.out.println("❌ Test failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("🔚 Browser closed.");
        }
    }
}
