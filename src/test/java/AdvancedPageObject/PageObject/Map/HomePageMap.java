package AdvancedPageObject.PageObject.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageMap {

    @FindBy(linkText = "Immobilien")
    public WebElement allPropertyLink;

    @FindBy(id = "ctl00_Header1_ctlHeaderMetaBar_ucMainLinks_hypMyAnibis")
    public WebElement loginBtn;

    public HomePageMap(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
