import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;

public class QuickView {

    WebDriver driver;
    WebDriverWait wait;

    // ** Web Elements **

    By plpItem = By.cssSelector(".product-image-container > [href*='id_product=6']");
    By quickViewButton = By.cssSelector("a.quick-view[href*='id_product=6']");
    By changeQuantity = By.cssSelector("a.button-plus");
    By changeSize = By.cssSelector("#group_1");
    By chooseColor = By.cssSelector("#color_8");
    By buttonAddToCart = By.cssSelector("button.exclusive");
    By closeTheConfirmationOverlay = By.cssSelector("span.cross");


    public QuickView(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 40);

    }

     // ** Page Methods **

     // *** Switch to Quickview frame ***

    public void goToQuickViewFrame() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(plpItem))
                .build()
                .perform();
        driver.findElement(quickViewButton).click();
    }

    // *** Set product quantity ***

    public void setQuantity() {
        driver.switchTo().frame(0);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(changeQuantity)).doubleClick()
                .perform();

    }
    // *** Set product size ***

    public void setSize() {
        Select size = new Select(driver.findElement(changeSize));
        size.selectByValue("2");
    }
    // *** Set product color ***

    public void setColor() {
        driver.findElement(chooseColor).click();
        //Assert.assertTrue("This is not selected", driver.findElement(chooseColor).isSelected());
    }

    // *** Calculates final price of order (without discount) ***
    public BigDecimal calculateSubtotalPrice() {
        WebElement productPrice = driver.findElement(By.cssSelector("span#our_price_display"));
        String price = productPrice.getText().substring(1);
        BigDecimal productPrice1 = new BigDecimal(price);
        WebElement productQuantity = driver.findElement(By.cssSelector("input#quantity_wanted"));
        String productQuantity1 = productQuantity.getAttribute("value");
        BigDecimal productQuantity2 = new BigDecimal(productQuantity1);
        BigDecimal finalPrice = productQuantity2.multiply(productPrice1);
        return finalPrice;
    }
       // System.out.println(finalPrice);


        // System.out.println(price);




    public void addToCart() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(buttonAddToCart)));
        driver.findElement(buttonAddToCart).click();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".fancybox-overlay"))));
        driver.switchTo().activeElement();
        driver.findElement(closeTheConfirmationOverlay).click();


    }
}



