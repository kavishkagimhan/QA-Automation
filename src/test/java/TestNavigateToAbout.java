import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class TestNavigateToAbout {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Set up ChromeDriver using WebDriverManager

        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testNavigateToAboutPage() {
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

            // Open the menu
            WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
            menuButton.click();

            // Wait for the menu to load
            Thread.sleep(3000);

            // Click the "About" link
            WebElement aboutLink = driver.findElement(By.id("about_sidebar_link"));
            aboutLink.click();

            // Wait for the "About" page to load
            Thread.sleep(3000);

            // Verify the "About" page by checking the URL or an element on the page
            String expectedAboutUrl = "https://saucelabs.com/";
            assertEquals(expectedAboutUrl, driver.getCurrentUrl());

            System.out.println("Test Passed: Successfully navigated to the 'About' page.");

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

