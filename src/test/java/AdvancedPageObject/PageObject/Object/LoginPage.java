package AdvancedPageObject.PageObject.Object;

import AdvancedPageObject.PageObject.Map.LoginPageMap;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    private String url = "https://www.anibis.ch/de/login.aspx";
    private LoginPageMap map;


    public LoginPage(WebDriver driver) {
        super(driver);
        map = new LoginPageMap(driver);
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

    public Boolean usernameIsFocused() {
        return map.username.isSelected();
    }

    public Boolean isInLoginPage() {
        return driver.getCurrentUrl().contains(url);
    }
}
