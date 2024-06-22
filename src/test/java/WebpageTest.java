import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class WebpageTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://rozetka.com.ua/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void testSearching() {
        WebElement searchField = driver.findElement(By.className("search-form__input"));
        searchField.sendKeys("bober");

        WebElement searchButton = driver.findElement(By.className("search-form__submit"));
        searchButton.click();

        WebElement searchResults = driver.findElement(By.className("layout_with_sidebar"));
        Assertions.assertTrue(searchResults.isDisplayed());

        WebElement firstResult = searchResults.findElement(By.cssSelector("a.product-link.goods-tile__heading"));
        Assertions.assertEquals("https://rozetka.com.ua/ua/375116151/p375116151/", firstResult.getAttribute("href")); //hope it's still the same

        WebElement firstPrice = searchResults.findElement(By.cssSelector("p.ng-star-inserted > span.goods-tile__price-value"));
        String priceText = firstPrice.getText().replaceAll("[^0-9]", "");
        int price = Integer.parseInt(priceText);
        Assertions.assertEquals(828, price);
    }

    @Test
    void testNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://rozetka.com.ua/ua/trixie_4011905359106/p10556690/");

        Set<String> windowHandles = driver.getWindowHandles();
        Assertions.assertEquals(2, windowHandles.size(), "Number of open tabs is not equal to 2");

        String expectedUrl = "https://rozetka.com.ua/ua/trixie_4011905359106/p10556690/";
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());

        WebElement productTitle = driver.findElement(By.cssSelector("h1.h2.bold.ng-star-inserted"));
        Assertions.assertTrue(productTitle.isDisplayed());
    }

    @Test
    void testCartCookie() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://rozetka.com.ua/ua/trixie_4011905359106/p10556690/");

        WebElement addToCartButton = driver.findElement(By.cssSelector("button.buy-button"));
        addToCartButton.click();

        //provided 'cause it can catch the exception before the window will pop up
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement modalWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.modal__holder_show_animation")));

        Assertions.assertTrue(modalWindow.isDisplayed());

        Cookie cartCookie = driver.manage().getCookieNamed("cart-modal");
        Assertions.assertNotNull(cartCookie);

        WebElement button = driver.findElement(By.cssSelector("button[data-testid='cart-counter-increment-button']"));
        button.click();
        button.click();

        WebElement inputElement = driver.findElement(By.cssSelector("input[data-testid='cart-counter-input']"));
        String value = inputElement.getAttribute("value");
        int count = Integer.parseInt(value);
        Assertions.assertEquals(3, count);
    }
}