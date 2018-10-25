package AdvancedPageObject.PageObject.Object;

import AdvancedPageObject.PageObject.Map.FilterSectionMap;
import AdvancedPageObject.PageObject.Map.ResultsPageMap;
import AdvancedPageObject.PageObject.Validator.ResultValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ResultsPage extends BasePage {
    private FilterResultBar filter;
    private ResultsPageMap map;

    public ResultsPage() {
        filter = new FilterResultBar(driver);
    }

    public ResultsPage filterByPrice(int from, int to) {
        filter.priceFrom(from);
        filter.priceTo(to);
        waitForPage();
        return this;
    }

    public ResultsPage filterRadius(int index) {
        filter.setRadius(index);
        waitForPage();
        return this;
    }

    public ResultsPage filter() {
        filter.performFilter();
        return this;
    }

    public ResultsPage location(String location) {
        filter.setLocation(location);
        return this;
    }

    public ResultsPage sortByDate(int index) {
        filter.setSortByDate(index);
        waitForPage();
        return this;
    }

    public List<AdItem> getItems() {
        return map.getItems().stream()
                .map(AdItem::new)
                .collect(Collectors.toList());
    }

    public ResultValidator validator() {
        return new ResultValidator();
    }

    @Override
    void waitForPage() {
        super.waitForPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-segment-breadcrumb")));
    }

    @Override
    public ResultsPage navigate() {
        throw new IllegalAccessError("Not implemented!");
    }


    private class FilterResultBar {
        private FilterSectionMap map;
        private WebDriver driver;


        FilterResultBar(WebDriver driver) {
            this.driver = driver;
            map = new FilterSectionMap();
        }

        void priceFrom(int price) {
            map.priceFrom = driver.findElement(By.id("ctl00_phlContent_Filter_ctlPriceRange_txtFrom"));
            map.priceFrom.clear();
            map.priceFrom.sendKeys(Integer.toString(price));
        }

        void setLocation(String location) {
            scrollIntoView(map.locationTextField);

            WebElement targetSuggestion = null;
            int n = 0;
            while (targetSuggestion == null && n < 5) {
                map.locationTextField.click();
                map.locationTextField.clear();
                map.locationTextField.sendKeys(location);
                waitForPage();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                targetSuggestion = getTargetSuggestion();
                n++;
            }
            if (n == 5) {
                System.out.println("There's no such suggestion");
            }
            targetSuggestion.click();
        }

        void priceTo(int price) {
            map.priceTo.clear();
            map.priceTo.sendKeys(Integer.toString(price));
        }

        void setSortByDate(int index) {
            Select sortByDate = new Select(map.sortByDate);
            sortByDate.selectByIndex(index);
        }

        void setRadius(int index) {
            Select radius = new Select(map.radius);
            radius.selectByIndex(index);
        }

        void performFilter() {
            map.filterBtn.click();
            waitForPage();
        }

        private WebElement getTargetSuggestion() {
            WebElement targetSuggestion = null;
            try {
                targetSuggestion = driver.findElement(By.cssSelector(
                        "#ctl00_phlContent_Filter_ctlLocation_ctlLocationAutocomplete_ulAutocompleteOptionsList .list-item:last-child"));
            } catch (Exception e) {
                // TODO: handle exception
            }
            return targetSuggestion;
        }
    }


}
