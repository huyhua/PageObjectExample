package AdvancedPageObject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ResultsPage extends BasePage {
    private FilterResultBar filter;

    public ResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        filter = new FilterResultBar(driver);
    }

    public ResultsPage filterByPrice(int from, int to) {
        filter.priceFrom(from);
        filter.priceTo(to);
        driver.findElement(By.cssSelector(".filter-section #ctl00_phlContent_Filter_btnSearch")).click();
        waitForPage();
        return this;
    }

    public ResultsPage filterRadius(int index) {
        filter.setRadius(index);
        waitForPage();
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
        return driver.findElements(By.className("listing-info"))
                .stream()
                .skip(1) // Remove the ad
                .map(AdItem::new)
                .collect(Collectors.toList());
    }

    @Override
    void waitForPage() {
        super.waitForPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-segment-breadcrumb")));
    }



    private class FilterResultBar {
        private WebDriver driver;
        @FindBy(id = "ctl00_phlContent_Filter_ctlLocation_ctlLocationAutocomplete_divLocation")
        private WebElement locationTextField;
        @FindBy(id = "ctl00_phlContent_Filter_ctlPriceRange_txtFrom")
        private WebElement priceFrom;
        @FindBy(id = "ctl00_phlContent_Filter_ctlPriceRange_txtTo")
        private WebElement priceTo;
        @FindBy(id = "ctl00_phlContent_ResList_ddlSorting")
        private WebElement sortByDate;
        @FindBy(id = "ctl00_phlContent_Filter_ctlLocation_ddlDistance")
        private WebElement radius;

        public FilterResultBar(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public void priceFrom(int price) {
            priceFrom = driver.findElement(By.id("ctl00_phlContent_Filter_ctlPriceRange_txtFrom"));
            priceFrom.clear();
            priceFrom.sendKeys(Integer.toString(price));
        }

        public void setLocation(String location) {
            scrollIntoView(locationTextField);

            WebElement targetSuggestion = null;
            int n = 0;
            while (targetSuggestion == null && n < 5) {
                locationTextField.click();
                locationTextField.clear();
                locationTextField.sendKeys(location);
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

        public void priceTo(int price) {
            priceTo.clear();
            priceTo.sendKeys(Integer.toString(price));
        }

        public void setSortByDate(int index) {
            Select sortByDate = new Select(this.sortByDate);
            sortByDate.selectByIndex(index);
        }

        public void setRadius(int index) {
            Select radius = new Select(this.radius);
            radius.selectByIndex(index);
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

    public class AdItem {
        private WebElement wrappedElement;

        public Date getDate() {
            Date date;
            List<WebElement> elements = wrappedElement.findElements(By.className("item-date"));
            if (elements.isEmpty()) return null;
            try {
                wrappedElement.findElements(By.className("item-date"));
                date = new SimpleDateFormat("dd.MM.yyyy").parse(elements.get(0).getText());
            } catch (ParseException e) {
                date = null;
            }
            return date;
        }

        public String getPriceString() {
            return wrappedElement.findElement(By.className("listing-price")).getText();
        }

        public String getTitle() {
            return wrappedElement.findElement(By.className("listing-title")).getText();
        }

        public String getUrl() {
            return wrappedElement.findElement(By.className("listing-title")).getAttribute("href");
        }


        private AdItem(WebElement element) {
            this.wrappedElement = element;
        }

    }
}
