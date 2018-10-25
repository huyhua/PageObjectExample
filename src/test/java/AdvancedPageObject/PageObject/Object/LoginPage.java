package AdvancedPageObject.PageObject.Object;

import AdvancedPageObject.PageObject.Map.LoginPageMap;
import AdvancedPageObject.PageObject.Validator.LoginValidator;

public class LoginPage extends BasePage{
    private LoginPageMap map;


    public LoginPage() {
        map = new LoginPageMap();
    }

    @Override
    public LoginPage navigate() {
        String url = "https://www.anibis.ch/de/login.aspx";
        driver.get(url);
        return this;
    }


    public LoginPage fillUsername(String username) {
        map.username.clear();
        map.username.sendKeys(username);
        return this;
    }

    public LoginPage fillPassword(String password) {
        map.password.clear();
        map.password.sendKeys(password);
        return this;
    }

    public void login() {
        map.loginBtn.click();
        waitForPage();
    }

    public LoginValidator validator() {
        return new LoginValidator();
    }
}
