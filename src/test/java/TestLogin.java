import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class TestLogin {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Set up ChromeDriver using WebDriverManager
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testLogin() {
        try {
            // Locate the username field
            WebElement usernameField = driver.findElement(By.id("user-name"));
            usernameField.sendKeys("standard_user");

            // Locate the password field
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("secret_sauce");

            // Locate the login button and click it
            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            // Wait for 3 minutes (180000 milliseconds)
            Thread.sleep(6000);

            // Assert that the login was successful by checking the URL or an element on the landing page
            String expectedUrl = "https://www.saucedemo.com/inventory.html";
            assertEquals(expectedUrl, driver.getCurrentUrl());

            System.out.println("Test Passed: Login was successful.");

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
