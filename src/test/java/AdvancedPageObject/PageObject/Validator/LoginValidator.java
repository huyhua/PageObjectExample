package AdvancedPageObject.PageObject.Validator;

import AdvancedPageObject.PageObject.Map.LoginPageMap;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginValidator extends BaseValidator<LoginPageMap> {

    public LoginValidator() {
        super(LoginPageMap.class);
    }

    public LoginValidator usernameIsFocused() {
        Assertions.assertTrue(map.username.isSelected());
        return this;
    }

    public LoginValidator errorShown() {
        WebElement highlightField = driver
                .findElement(By.xpath(".//input[@name='ctl00$phlContent$txtUsername' and @aria-required='true']"));

        Assertions.assertFalse(driver.findElements(By.className("error-msg")).isEmpty());
        Assertions.assertTrue(highlightField.isDisplayed());
        return this;
    }

    public LoginValidator isInMeinAnibis() {
        Assertions.assertEquals(driver.findElement(By.id("ctl00_Header1_ctlHeaderMetaBar_ucMainLinks_hypMyAnibis")).getText(), "Mein Anibis");
        return this;
    }
}
