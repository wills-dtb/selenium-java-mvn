package shop;

import base.BaseTestCase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;

public class ShopTest extends BaseTestCase {

    @Override
    public void setUp() throws MalformedURLException {
        super.setUp();
        driver.findElement(By.linkText("Start Shopping »")).click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("ul li:last-child")));
    }

    @Test
    public void testItemsPopulateCorrectlyInCart() {
        WebElement funnyCowBuyButton = driver.findElement(By.cssSelector("#product-6 > div > p > a"));
        ShopHelpers.executor.executeScript("arguments[0].scrollIntoView(true);", funnyCowBuyButton);
        funnyCowBuyButton.click();
        funnyCowBuyButton.click();

        WebElement fluffyBunnyBuyButton = driver.findElement(By.cssSelector("#product-4 > div > p > a"));
        ShopHelpers.executor.executeScript("arguments[0].scrollIntoView(true);", fluffyBunnyBuyButton);
        fluffyBunnyBuyButton.click();

        WebElement cartMenuListItem = driver.findElement(By.id("nav-cart"));
        cartMenuListItem.click();

        String cartCount = cartMenuListItem.findElement(By.cssSelector("span[class='cart-count ng-binding']")).getText();
        assertEquals("3", cartCount);

        int cartItemCount = driver.findElements(By.xpath("//tbody/tr")).size();
        assertEquals(2, cartItemCount);
    }
}
