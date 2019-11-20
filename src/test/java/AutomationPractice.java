import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class AutomationPractice {


    WebDriver driver;
    WebDriverWait wait;


    @Test
    public void myTest() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\Chrome Driver\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        wait = new WebDriverWait(driver, 30);

        // ** Hover Women Category **  - done
        WebElement women = driver.findElement(By.cssSelector("a.sf-with-ul"));
        Actions action = new Actions(driver);
        action.moveToElement(women).build().perform();

        // ** Choose Summer Dresses Category ** - done
        WebElement option = driver.findElement(By.cssSelector("[href*='id_category=11']"));
        option.click();

        // ** Choose the middle dress**
        WebElement dress = driver.findElement(By.cssSelector(".product-image-container > [href*='id_product=6']"));
       /* ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dress);
        wait.until(ExpectedConditions.visibilityOf(dress));*/
        action.moveToElement(dress).build().perform();

        // **Choose QuickView button**
        WebElement quickView = driver.findElement(By.cssSelector(".quick-view[href*='id_product=6']"));
        wait.until(ExpectedConditions.visibilityOf(quickView));
        quickView.click();

        // ** Switching to PDP frame **
        driver.switchTo().frame(0);

        // ** Change quantity **
        action.moveToElement(driver.findElement(By.cssSelector("a.button-plus"))).doubleClick().perform();
        // ** Change size **
        Select size = new Select(driver.findElement(By.cssSelector("#group_1")));
        size.selectByValue("2");

        // ** Change color **
        driver.findElement(By.cssSelector("#color_8")).click();

        // ** Add to cart **
        driver.findElement(By.cssSelector("button.exclusive")).click();

        // **Exit the frame **
        driver.switchTo().defaultContent();

        // ** Switch to a new frame **
        driver.switchTo().activeElement();

        // ** Close the confirmation overlay **
        WebElement close = driver.findElement(By.cssSelector("span.cross"));
        wait.until(ExpectedConditions.elementToBeClickable(close));
        close.click();

        // ** Exiting the confirmation frame **
        driver.switchTo().defaultContent();

        // ** Hover Mini cart **
        action.moveToElement(driver.findElement(By.cssSelector("div.shopping_cart > a:nth-child(1)"))).build().perform();
        driver.switchTo().activeElement();

        // ** Assert color, size, quantity and price calculation is as expected **


       /* Assert.assertTrue("The color is not as expected", driver.findElement(By.cssSelector("div.product-atributes")).getText().contains("White"));
        Assert.assertTrue("Quantity is not as expected", driver.findElement(By.cssSelector("span.quantity")).getText().contains("3"));*/

        //action.moveToElement(driver.findElement(By.cssSelector("div.shopping_cart"))).build().perform();
    }

    /*@AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
*/
}



