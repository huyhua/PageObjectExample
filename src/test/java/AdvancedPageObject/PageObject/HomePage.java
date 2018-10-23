package AdvancedPageObject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
    private String url = "https://www.anibis.ch/de/default.aspx";

    @FindBy(linkText = "Immobilien")
    private WebElement allPropertyLink;

    @FindBy(id = "ctl00_Header1_ctlHeaderMetaBar_ucMainLinks_hypMyAnibis")
    private WebElement loginBtn;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ResultsPage allProperties() {
        allPropertyLink.click();
        waitForPage();
        return new ResultsPage(driver);
    }

    public LoginPage login() {
        loginBtn.click();
        waitForPage();
        return new LoginPage(driver);
    }
}