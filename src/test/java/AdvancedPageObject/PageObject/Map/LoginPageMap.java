package AdvancedPageObject.PageObject.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageMap extends BaseMap {
    @FindBy(id = "ctl00_phlContent_txtUsername")
    public WebElement username;
    @FindBy(id = "ctl00_phlContent_txtPassword")
    public WebElement password;
    @FindBy(id = "ctl00_phlContent_btnLogin")
    public WebElement loginBtn;
    @FindBy(xpath = ".//input[@name='ctl00$phlContent$txtUsername' and @aria-required='true']")
    public WebElement usernameHighlight;
    @FindBy(xpath = ".//input[@name='ctl00$phlContent$txtPassword' and @aria-required='true']")
    public WebElement passwordHighlight;
    @FindBy(className = "error-msg")
    public WebElement errorMsg;

    public LoginPageMap() {
        PageFactory.initElements(driver, this);
    }
}
