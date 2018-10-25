package AdvancedPageObject.PageObject.Validator;

import AdvancedPageObject.PageObject.Map.BaseMap;
import AdvancedPageObject.PageObject.WebDriver.Driver;
import org.openqa.selenium.WebDriver;

public class BaseValidator<M extends BaseMap> {
    protected M map;
    protected WebDriver driver;

    BaseValidator(Class<M> mClass) {
        try {
            map = mClass.newInstance();
        } catch (Exception ignored) {
            map = null;
        }
        driver = Driver.getDriver();
    }

}
