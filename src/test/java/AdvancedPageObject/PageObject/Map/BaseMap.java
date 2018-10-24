package AdvancedPageObject.PageObject.Map;

import AdvancedPageObject.PageObject.WebDriver.Driver;
import org.openqa.selenium.WebDriver;

public abstract class BaseMap{
    protected WebDriver driver;

    public BaseMap() {
        this.driver = Driver.getDriver();
    }
}
