import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class TestCheckout {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Set up ChromeDriver using WebDriverManager

        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testCheckoutProcess() {
        try {
            // Log in to the website
            WebElement usernameField = driver.findElement(By.id("user-name"));
            usernameField.sendKeys("standard_user");

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("secret_sauce");

            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            // Wait for  (3000 milliseconds)
            Thread.sleep(3000);

            // Verify login by checking the URL
            String expectedUrl = "https://www.saucedemo.com/inventory.html";
            assertEquals(expectedUrl, driver.getCurrentUrl());

            // Add products to the cart
            WebElement firstProductAddToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            firstProductAddToCartButton.click();

            WebElement secondProductAddToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
            secondProductAddToCartButton.click();

            // Click the cart icon
            WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
            cartIcon.click();

            // Wait for 5 seconds to ensure the cart page loads
            Thread.sleep(3000);

            // Verify the cart items
            WebElement firstCartItem = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
            WebElement secondCartItem = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']"));

            assertEquals("Sauce Labs Backpack", firstCartItem.getText());
            assertEquals("Sauce Labs Bike Light", secondCartItem.getText());

            // Click the checkout button
            WebElement checkoutButton = driver.findElement(By.id("checkout"));
            checkoutButton.click();

            // Wait for the checkout page to load
            Thread.sleep(3000);

            // Enter checkout information
            WebElement firstNameField = driver.findElement(By.id("first-name"));
            firstNameField.sendKeys("John");

            WebElement lastNameField = driver.findElement(By.id("last-name"));
            lastNameField.sendKeys("Doe");

            WebElement postalCodeField = driver.findElement(By.id("postal-code"));
            postalCodeField.sendKeys("12345");

            WebElement continueButton = driver.findElement(By.id("continue"));
            continueButton.click();

            // Wait for the overview page to load
            Thread.sleep(3000);

            // Click the finish button to complete the checkout
            WebElement finishButton = driver.findElement(By.id("finish"));
            finishButton.click();

            // Wait for the confirmation page to load
            Thread.sleep(3000);

            // Verify the checkout completion
            WebElement confirmationMessage = driver.findElement(By.xpath("//h2[text()='THANK YOU FOR YOUR ORDER']"));
            assertEquals("THANK YOU FOR YOUR ORDER", confirmationMessage.getText());

            System.out.println("Test Passed: Checkout process was completed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Failed: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
