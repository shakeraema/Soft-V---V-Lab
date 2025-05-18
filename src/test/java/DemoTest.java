
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DemoTest {

    String url = "http://uitestingplayground.com/sampleapp";
    private WebDriver driver;

    @BeforeEach
    void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().window().maximize();
        try {
            Thread.sleep(2000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(1)
    void loginTest() throws InterruptedException {
        // Implement your test logic here
        login("test", "pwd");
        Thread.sleep(2000); // Sleep for 2 seconds
        WebElement successText = driver.findElement(By.id("loginstatus"));
        assertEquals("Welcome, text", successText.getText());
    }

    void login(String username, String password) {
        // Implement your login logic here
        WebElement usernameField = driver.findElement(By.cssSelector("#\\35 d5e9184-a225-4643-084b-b3fe1cde24a0"));
        WebElement passwordField = driver.findElement(By.cssSelector("#dd7f780d-2f87-6c95-8d48-cb99649428db"));
        WebElement loginButton = driver.findElement(By.id("login"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        try {
            Thread.sleep(2000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}