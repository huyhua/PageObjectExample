package PageObject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ResultsPage {
    private WebDriver driver;
    private FilterResultBar filter;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        filter = new FilterResultBar(driver);
    }

    public void filterByPrice(int from, int to) {
        filter.priceFrom(from);
        filter.priceTo(to);
        driver.findElement(By.cssSelector(".filter-section #ctl00_phlContent_Filter_btnSearch")).click();
        waitForPage();
    }

    public void filterRadius(int index) {
        filter.setRadius(index);
        waitForPage();
    }

    public void location(String location) {
        filter.setLocation(location);
    }

    public void sortByDate(int index) {
        filter.setSortByDate(index);
        waitForPage();
    }

    public List<AdItem> getItems() {
        return driver.findElements(By.className("listing-info"))
                .stream()
                .skip(1) // Remove the ad
                .map(AdItem::new)
                .collect(Collectors.toList());
    }

    private void waitForPage() {
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        WebElement breadCrumb = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("page-segment-breadcrumb")));
    }



    private class FilterResultBar {
        private WebDriver driver;
        private WebElement locationTextField;
        private Select radius;
        private WebElement priceFrom;
        private WebElement priceTo;
        private Select sortByDate;

        public FilterResultBar(WebDriver driver) {
            this.driver = driver;
        }

        public void priceFrom(int price) {
            priceFrom = driver.findElement(By.id("ctl00_phlContent_Filter_ctlPriceRange_txtFrom"));
            priceFrom.clear();
            priceFrom.sendKeys(Integer.toString(price));
        }

        public void setLocation(String location) {
            locationTextField = driver
                    .findElement(By.id("ctl00_phlContent_Filter_ctlLocation_ctlLocationAutocomplete_divLocation"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", locationTextField);

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
            priceTo = driver.findElement(By.id("ctl00_phlContent_Filter_ctlPriceRange_txtTo"));
            priceTo.clear();
            priceTo.sendKeys(Integer.toString(price));
        }

        public void setSortByDate(int index) {
            sortByDate = new Select(driver.findElement(By.id("ctl00_phlContent_ResList_ddlSorting")));
            sortByDate.selectByIndex(index);
        }

        public void setRadius(int index) {
            radius = new Select(driver.findElement(By.id("ctl00_phlContent_Filter_ctlLocation_ddlDistance")));
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
