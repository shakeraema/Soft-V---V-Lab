import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SuceTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    @Order(1)
    @DisplayName("Login Test")
    void loginTest() throws InterruptedException {
        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000); // Sleep for 2 seconds

        // Validate successful login
        String pageTitle = driver.getTitle();
        assertEquals("Swag Labs", pageTitle);
        // Validate URL
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);
        // Validate login button is not present
        boolean isLoginButtonPresent = driver.findElements(By.id("login-button")).isEmpty();
        assertEquals(true, isLoginButtonPresent);
    }

    @Test
    @Order(2)
    @DisplayName("Add Item to Cart Test")
    void addItemToCartTest() throws InterruptedException {
        // Login
        login("standard_user", "secret_sauce");
        Thread.sleep(2000); // Sleep for 2 seconds
        // Add item to cart
        addItemToCart(0); // Add the first item to cart
        Thread.sleep(2000); // Sleep for 2 seconds
        // Validate item added to cart
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        assertEquals("1", cartCount);

    }

    @RepeatedTest(1)
    @Order(3)
    @DisplayName("Remove Item from Cart Test")
    void removeItemFromCartTest() throws InterruptedException {
        // Login
        login("standard_user", "secret_sauce");
        Thread.sleep(2000); // Sleep for 2 seconds
        // Remove item from cart
        // driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.cssSelector(".btn_inventory")).click();
        Thread.sleep(2000); // Sleep for 2 seconds
        driver.findElement(By.cssSelector(".btn_inventory")).click();
        Thread.sleep(2000); // Sleep for 2 seconds
        driver.findElement(By.cssSelector(".btn_inventory")).click();
        Thread.sleep(2000); // Sleep for 2 seconds
        // Validate item removed from cart
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        assertEquals("1", cartCount);

    }
    @Test
    @Order(4)
    @DisplayName("Invalid Login Test")
    void invalidTest() throws InterruptedException {
        login("invalid_user", "invalid_password");
        Thread.sleep(2000); // Sleep for 2 seconds
        // Validate error message
        WebElement errorMessage = driver.findElement(
                By.cssSelector("#login_button_container > div > form > div.error-message-container.error > h3"));
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                errorMessage.getText());
    }

    @Test
    @Order(5)
    void lockuserTest() throws InterruptedException {
        // Login
        login("locked_out_user", "secret_sauce");
        Thread.sleep(2000); // Sleep for 2 seconds
        // Validate error message
        WebElement errorMessage = driver.findElement(
                By.cssSelector("#login_button_container > div > form > div.error-message-container.error > h3"));
        assertEquals("Epic sadface: Sorry, this user has been locked out.", errorMessage.getText());

    }

    @Test
    @Order(6)

    void checkoutTest() throws InterruptedException {
        // Login
        login("standard_user", "secret_sauce");
        Thread.sleep(2000); // Sleep for 2 seconds
        // Add item to cart
        addtoCart(0); // Add the first item to cart
        addtoCart(1);
        addtoCart(2);
        // addItemToCart(1); // Add the second item to cart
        Thread.sleep(2000); // Sleep for 2 seconds
        // Click on cart icon
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000); // Sleep for 2 seconds
        // Click on checkout button
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(2000); // Sleep for 2 seconds
        // Fill in checkout information
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        Thread.sleep(2000); // Sleep for 2 seconds
        // Click on continue button
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000); // Sleep for 2 seconds
        // Validate URL
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/checkout-step-two.html", currentUrl);

        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000); // Sleep for 2 seconds
        // Validate checkout completion
        WebElement successMessage = driver.findElement(By.className("complete-header"));
        assertTrue(successMessage.isDisplayed(), "Success message is not displayed");
        assertEquals("Thank you for your order!", successMessage.getText());
        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(2000); // Sleep for 2 seconds
        // Validate checkout overview
        String pageTitle = driver.getTitle();
        assertEquals("Swag Labs", pageTitle);
        // Validate URL
        String currentUrlnow = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentUrlnow);
    }

    void login(String username, String password) {
        // Login
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    void addItemToCart(int itemIndex) {
        // Add item to cart
        driver.findElement(By.cssSelector(".btn_inventory")).click();
    }

    void addtoCart(int itemIndex) {
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector(".btn_inventory"));
        assertTrue(itemIndex < addToCartButtons.size(), "Item index is out of bounds");
        WebElement addToCartButton = addToCartButtons.get(itemIndex);
        addToCartButton.click();

    }

    @AfterEach
    void tearDown() {
        try {
            Thread.sleep(2000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Close the browser
        driver.quit();
    }
}