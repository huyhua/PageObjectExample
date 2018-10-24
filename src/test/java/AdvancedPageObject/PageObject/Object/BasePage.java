package AdvancedPageObject.PageObject.Object;

import AdvancedPageObject.PageObject.Validator.BaseValidator;
import AdvancedPageObject.PageObject.WebDriver.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    BasePage() {
        this.driver = Driver.getDriver();
        this.wait = new WebDriverWait(driver, 10);
        waitForPage();
    }

    protected WebDriver driver;
    protected WebDriverWait wait;

    void waitForPage() {
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }

    void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    abstract <V extends BaseValidator> V validator();
}
