import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.math.BigDecimal;
public class AutomationPracticeTestCase  {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest

    public void setup() {

        // ** Navigate to Homepage **

        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\Chrome Driver\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        wait = new WebDriverWait(driver, 20);

    }

    @Test

    public void verifyRightSizeColorAndQuantity() {

        // ** Navigate to subcategory **
        HomePage homePage = new HomePage(driver);
        homePage.navigateToSubCategory(driver);

        // ** Setting quantity, size and color
        QuickView quickView = new QuickView(driver);
        quickView.goToQuickViewFrame();
        quickView.setQuantity();
        quickView.setSize();
        quickView.setColor();
        BigDecimal finalPrice = quickView.calculateSubtotalPrice();

       // ** Adds product to cart **
        quickView.addToCart();


        // ** Confirming quantity, size, color and price are right **
        MiniBasket miniBasket = new MiniBasket(driver);
        miniBasket.confirmMiniCartContent();
        BigDecimal price2 = miniBasket.getSubtotalPrice();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.product-atributes")).getText().contains("M"), "Size is not right");
        Assert.assertTrue(driver.findElement(By.cssSelector("div.product-atributes")).getText().contains("White"), "Color is not right");
        Assert.assertTrue(driver.findElement(By.cssSelector("span.quantity-formated")).getText().contains("3"), "Quantity is not right");
        Assert.assertEquals(finalPrice, price2);










    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
