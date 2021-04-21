package contact;

import base.BaseTestCase;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

import static org.junit.Assert.*;

public class ContactTest extends BaseTestCase {

    @Override
    public void setUp() throws MalformedURLException {
        super.setUp();
        driver.findElement(By.linkText("Contact")).click();
//        driver.get("https://jupiter.cloud.planittesting.com/#/contact");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Submit")));
    }

    void isErrorMessagesInvisible(Boolean bool) {
        assertThat(driver.findElements(By.cssSelector(".alert-error")).isEmpty(), CoreMatchers.is(bool));
        assertThat(driver.findElements(By.id("forename-err")).isEmpty(), CoreMatchers.is(bool));
        assertThat(driver.findElements(By.id("email-err")).isEmpty(), CoreMatchers.is(bool));
        assertThat(driver.findElements(By.id("message-err")).isEmpty(), CoreMatchers.is(bool));
    }

    void clickSubmitButton() {
        WebElement submitButton = driver.findElement(By.linkText("Submit"));
        ContactHelpers.executor.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();
    }

    @Test
    public void testEmptySubmissionProducesErrors() throws InterruptedException {
        Thread.sleep(5000);

        clickSubmitButton();

        assertTrue(driver.findElement(By.cssSelector(".alert-error")).isDisplayed());
        isErrorMessagesInvisible(false);

        driver.findElement(By.id("forename")).sendKeys(ContactHelpers.FORENAME);
        driver.findElement(By.id("email")).sendKeys(ContactHelpers.EMAIL);
        driver.findElement(By.id("message")).sendKeys(ContactHelpers.MESSAGE);

        isErrorMessagesInvisible(true);
    }

    @Test
    public void testSuccessfulSubmission() throws InterruptedException {
        driver.findElement(By.id("forename")).sendKeys(ContactHelpers.FORENAME);
        driver.findElement(By.id("email")).sendKeys(ContactHelpers.EMAIL);
        driver.findElement(By.id("message")).sendKeys(ContactHelpers.MESSAGE);

        driver.findElement(By.linkText("Submit")).click();

        WebDriverWait wait = new WebDriverWait(BaseTestCase.driver, 20);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        assertTrue(element.isDisplayed());
    }

    @Test
    public void testInvalidDataMessages() {
        driver.findElement(By.id("forename")).sendKeys(ContactHelpers.FORENAME);
        driver.findElement(By.id("email")).sendKeys(ContactHelpers.BAD_EMAIL);
        driver.findElement(By.id("message")).sendKeys(ContactHelpers.MESSAGE);

        assertThat(driver.findElement(By.id("email-err")).isDisplayed(), CoreMatchers.is(true));
    }
}
