import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestSort {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path to your WebDriver executable

        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testProductSort() throws InterruptedException {
        // Log in with standard_user credentials
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        // Wait for the products page to load
        Thread.sleep(2000);

        // Locate the sort dropdown
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));

        // Create a Select object to interact with the dropdown
        Select select = new Select(sortDropdown);

        // Sort products by 'Price (low to high)'
        select.selectByValue("lohi");

        // Wait to see the sorted results
        Thread.sleep(2000);

        // Verify the sorting order
        List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));
        double previousPrice = 0.0;
        boolean sorted = true;
        for (WebElement priceElement : productPrices) {
            String priceText = priceElement.getText().replace("$", "");
            double currentPrice = Double.parseDouble(priceText);
            if (currentPrice < previousPrice) {
                sorted = false;
                break;
            }
            previousPrice = currentPrice;
        }

        assertTrue("Products are not sorted by price (low to high)", sorted);

        if (sorted) {
            System.out.println("Products successfully sorted by price (low to high).");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

