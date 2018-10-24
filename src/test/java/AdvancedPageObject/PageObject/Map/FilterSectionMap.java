package AdvancedPageObject.PageObject.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilterSectionMap extends BaseMap{
    @FindBy(id = "ctl00_phlContent_Filter_ctlLocation_ctlLocationAutocomplete_divLocation")
    public WebElement locationTextField;
    @FindBy(id = "ctl00_phlContent_Filter_ctlPriceRange_txtFrom")
    public WebElement priceFrom;
    @FindBy(id = "ctl00_phlContent_Filter_ctlPriceRange_txtTo")
    public WebElement priceTo;
    @FindBy(id = "ctl00_phlContent_ResList_ddlSorting")
    public WebElement sortByDate;
    @FindBy(id = "ctl00_phlContent_Filter_ctlLocation_ddlDistance")
    public WebElement radius;
    @FindBy(css = ".filter-section #ctl00_phlContent_Filter_btnSearch")
    public WebElement filterBtn;

    public FilterSectionMap() {
        PageFactory.initElements(driver, this);
    }
}
