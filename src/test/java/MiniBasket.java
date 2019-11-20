import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;

public class MiniBasket {


    WebDriver driver;

    By miniBasket = By.cssSelector("div.shopping_cart > a:nth-child(1)");
    By price = By.cssSelector("span.price");


    public MiniBasket(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmMiniCartContent() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(miniBasket)).build().perform();

    }
        public BigDecimal getSubtotalPrice() {
            WebElement price = driver.findElement(By.cssSelector("span.price"));
            price.getText().substring(1);
            String price1 = price.getText().substring(1);
            BigDecimal price2 = new BigDecimal(price1);
            return price2;

        }


    }

