package PageObject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private String url = "https://www.anibis.ch/de/login.aspx";
    private WebDriver driver;

    public WebElement getUsername() {
        return username;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }

    private WebElement username;
    private WebElement password;
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public void fillUsername(String username) {
        this.username = driver.findElement(By.id("ctl00_phlContent_txtUsername"));
        this.username.clear();
        this.username.sendKeys(username);
    }

    public void fillPassword(String password) {
        this.password = driver.findElement(By.id("ctl00_phlContent_txtPassword"));
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void login() {
        loginBtn = driver.findElement(By.id("ctl00_phlContent_btnLogin"));
        loginBtn.click();
        waitForIdle();
    }

    public Boolean usernameIsFocused() {
        this.username = driver.findElement(By.id("ctl00_phlContent_txtUsername"));
        return this.username.isSelected();
    }

    public Boolean isInLoginPage() {
        return driver.getCurrentUrl().contains(url);
    }

    private void waitForIdle() {
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }
}
