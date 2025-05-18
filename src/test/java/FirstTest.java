
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html"); 

        WebElement input = driver.findElement(By.id("my-text-id"));
        input.sendKeys("Hello world!");

        WebElement button = driver.findElement(By.className("btn-outline-primary"));
        button.click();
        try {
            Thread.sleep(5000); // Sleep for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();  
        System.out.println("Test completed");
    }
    
}
