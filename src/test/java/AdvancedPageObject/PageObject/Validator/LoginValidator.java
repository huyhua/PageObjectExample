package AdvancedPageObject.PageObject.Validator;

import AdvancedPageObject.PageObject.Map.LoginPageMap;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class LoginValidator extends BaseValidator<LoginPageMap> {

    public LoginValidator() {
        super(LoginPageMap.class);
    }

    public LoginValidator usernameIsFocused() {
        Assertions.assertTrue(map.username.isSelected());
        return this;
    }

    public LoginValidator errorShown() {
        Assertions.assertFalse(map.errorMsg.getText().isEmpty());
        Assertions.assertTrue(map.usernameHighlight.isDisplayed());
        return this;
    }

    public LoginValidator isInMeinAnibis() {
        Assertions.assertEquals(driver.findElement(By.id("ctl00_Header1_ctlHeaderMetaBar_ucMainLinks_hypMyAnibis")).getText(), "Mein Anibis");
        return this;
    }
}
