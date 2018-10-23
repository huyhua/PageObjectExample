package AdvancedPageObject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    private String url = "https://www.anibis.ch/de/login.aspx";

    public WebElement getUsername() {
        return username;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }

    @FindBy(id = "ctl00_phlContent_txtUsername")
    private WebElement username;
    @FindBy(id = "ctl00_phlContent_txtPassword")
    private WebElement password;
    @FindBy(id = "ctl00_phlContent_btnLogin")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public LoginPage fillUsername(String username) {
        this.username.clear();
        this.username.sendKeys(username);
        return this;
    }

    public LoginPage fillPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
        return this;
    }

    public void login() {
        loginBtn.click();
        waitForPage();
    }

    public Boolean usernameIsFocused() {
        return this.username.isSelected();
    }

    public Boolean isInLoginPage() {
        return driver.getCurrentUrl().contains(url);
    }
}
