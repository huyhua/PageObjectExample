package AdvancedPageObject.PageObject.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageMap {
    @FindBy(id = "ctl00_phlContent_txtUsername")
    public WebElement username;
    @FindBy(id = "ctl00_phlContent_txtPassword")
    public WebElement password;
    @FindBy(id = "ctl00_phlContent_btnLogin")
    public WebElement loginBtn;

    public LoginPageMap(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
