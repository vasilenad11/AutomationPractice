import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class HomePage {

    WebDriver driver;


    // ** Web Elements **

    By womenCategory = By.cssSelector("a.sf-with-ul");
    By subcategory = By.cssSelector("[href*='id_category=11']");

    // ** Page Methods **

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToSubCategory(WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(womenCategory))
                .build()
                .perform();
        driver.findElement(subcategory).click();

    }


}
