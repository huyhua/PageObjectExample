package AdvancedPageObject.PageObject.Object;

import AdvancedPageObject.PageObject.Map.HomePageMap;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    private String url = "https://www.anibis.ch/de/default.aspx";
    private HomePageMap map;

    public HomePage(WebDriver driver) {
        super(driver);
        this.map = new HomePageMap(driver);
    }

    public ResultsPage allProperties() {
        map.allPropertyLink.click();
        waitForPage();
        return new ResultsPage(driver);
    }

    public LoginPage login() {
        map.loginBtn.click();
        waitForPage();
        return new LoginPage(driver);
    }
}