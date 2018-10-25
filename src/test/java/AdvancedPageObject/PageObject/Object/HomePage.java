package AdvancedPageObject.PageObject.Object;

import AdvancedPageObject.PageObject.Map.HomePageMap;
import AdvancedPageObject.PageObject.Validator.BaseValidator;

public class HomePage extends BasePage{
    private HomePageMap map;

    public HomePage() {
        this.map = new HomePageMap();
    }

    @Override
    <V extends BaseValidator> V validator() {
        return null;
    }

    public HomePage navigate() {
        String url = "https://www.anibis.ch/de/default.aspx";
        driver.get(url);
        return this;
    }

    public ResultsPage allProperties() {
        map.allPropertyLink.click();
        waitForPage();
        return new ResultsPage();
    }

    public LoginPage login() {
        map.loginBtn.click();
        waitForPage();
        return new LoginPage();
    }
}