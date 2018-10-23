package AdvancedPageObject.PageObject.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPageMap {
    @FindBy(className = "listing-info")
    private List<WebElement> items;

    public ResultsPageMap(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getItems() {
        return this.items
                .stream()
                .skip(1) // Remove the ad
                .collect(Collectors.toList());
    }
}
