package PageObject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private String url = "https://www.anibis.ch/de/default.aspx";
    private WebDriver driver;
    private WebElement propertyBuyLink;
    private WebElement propertyRentLink;
    private WebElement allPropertyLink;
    private WebElement loginLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;

        propertyBuyLink = driver.findElements(By.className("category-link")).get(1);
        propertyRentLink = driver.findElements(By.className("category-link")).get(2);
        allPropertyLink = driver.findElement(By.linkText("Immobilien"));
        loginLink = driver.findElement(By.id("ctl00_Header1_ctlHeaderMetaBar_ucMainLinks_hypMyAnibis"));
    }

    public void rentProperty() {
        propertyRentLink.click();
        waitForPage();
    }

    public void buyProperty() {
        propertyBuyLink.click();
        waitForPage();
    }

    public void allProperties() {
        allPropertyLink.click();
        waitForPage();
    }

    public void toLogin() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLink.click();
        waitForPage();
    }

    private void waitForPage() {
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }
}