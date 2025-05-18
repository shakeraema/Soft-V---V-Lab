
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCases {
    String url = "https://practicetestautomation.com/practice-test-login/";
    WebDriver driver = new ChromeDriver();

    @BeforeEach
    void start() {
        driver.get(url);
    }

    @AfterEach
    void end() {
        try {
            Thread.sleep(2000); // Sleep for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
        System.err.println("Test completed");
    }

    @Test

    @Order(1)
    void loginTest() throws InterruptedException {
        login("student", "Password123");
        Thread.sleep(2000);
        Assertions.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/");

        WebElement successText = driver.findElement(By
                .cssSelector("#loop-container > div > article > div.post-content > p.has-text-align-center > strong"));
        String bodyText = driver.findElement(By.cssSelector("#loop-container > div > article > div.post-header > h1"))
                .getText();
        Assertions.assertTrue(
                successText.getText().contains("Congratulations!") || bodyText.contains("Logged In Successfully"));
        WebElement logoutButton = driver.findElement(
                By.cssSelector("#loop-container > div > article > div.post-content > div > div > div > a"));
        logoutButton.click();
        Thread.sleep(2000);
        Assertions.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/practice-test-login/");
        WebElement text = driver.findElement(
                By.className(
                        "wp-block-button__link has-text-color has-background has-very-dark-gray-background-color"));
        Assertions.assertTrue(text.isDisplayed(), "Logout button should be visible");
    }

    @Test
    @Order(2)
    void negativeUsenameTest() throws InterruptedException {
        login("incorrectUser", "Password123");
        Thread.sleep(2000);
        Assertions.assertEquals("https://practicetestautomation.com/practice-test-login/", driver.getCurrentUrl());
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assertions.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
        Assertions.assertTrue(errorMessage.getText().contains("Your username is invalid!"));
    }

    @Test
    @Order(3)
    void negativePasswordTest() throws InterruptedException {
        login("student", "incorrectPassword");
        Thread.sleep(2000);
        Assertions.assertEquals("https://practicetestautomation.com/practice-test-login/", driver.getCurrentUrl());
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assertions.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
        Assertions.assertTrue(errorMessage.getText().contains("Your password is invalid!"));
    }

    

    // @Test
    // void loginErrorTest() {
    //     WebElement username = driver.findElement(By.id("user-name"));
    //     WebElement password = driver.findElement(By.id("password"));
    //     WebElement loginButton = driver.findElement(By.id("login-button"));

    //     username.sendKeys("standard_user");
    //     password.sendKeys("secret_sau");
    //     loginButton.click();
    //     WebElement errorMessage = driver.findElement(By.className("error-message-container"));
    //     Assertions.assertTrue(errorMessage.getText()
    //             .contains("Epic sadface: Username and password do not match any user in this service"));

    // }
void login(String username, String password) {
    driver.findElement(By.cssSelector("#username")).sendKeys(username);
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.cssSelector("#submit")).click();
}
}
    


// https://www.saucedemo.com/inventory.html <a class="wp-block-button__link has-text-color has-background has-very-dark-gray-background-color" href="https://practicetestautomation.com/practice-test-login/" style="color:#ffffff">Log out</a>