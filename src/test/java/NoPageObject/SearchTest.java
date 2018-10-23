package NoPageObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchTest {
    WebDriver driver;
    String baseUrl = "https://www.anibis.ch/de/default.aspx";

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void testCustomSearch() {
        driver.get(baseUrl);
        WebElement immobilienLink = driver.findElement(By.xpath("//a[contains(text(),'Immobilien')]"));
        if (immobilienLink.isDisplayed()) {
            immobilienLink.click();
        }
        waitForPage();

        WebElement location = driver
                .findElement(By.id("ctl00_phlContent_Filter_ctlLocation_ctlLocationAutocomplete_divLocation"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", location);

        WebElement targetSuggestion = null;
        int n = 0;
        while (targetSuggestion == null && n < 5) {
            location.click();
            location.clear();
            location.sendKeys("Zurich");
            js.executeScript("return document.readyState").equals("complete");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            targetSuggestion = getTargetSuggestion();
            n++;
        }
        if (n == 5) {
            System.out.println("There's no such suggestion");
        }
        targetSuggestion.click();

        Select distanceSelection = new Select(
                driver.findElement(By.id("ctl00_phlContent_Filter_ctlLocation_ddlDistance")));
        distanceSelection.selectByIndex(4);

        WebElement priceFrom = driver.findElement(By.id("ctl00_phlContent_Filter_ctlPriceRange_txtFrom"));
        priceFrom.sendKeys("1000");
        WebElement priceTo = driver.findElement(By.id("ctl00_phlContent_Filter_ctlPriceRange_txtTo"));
        priceTo.sendKeys("5000");
        driver.findElement(By.cssSelector(".filter-section #ctl00_phlContent_Filter_btnSearch")).click();
        waitForPage();
        List<WebElement> searchResultItems = driver.findElements(By.className("listing-data"));

        Assertions.assertEquals(21, searchResultItems.size());
        waitForPage();
        Select selectSortDate = new Select(driver.findElement(By.id("ctl00_phlContent_ResList_ddlSorting")));
        if (!selectSortDate.getFirstSelectedOption().getText().contains("Datum")) {
            selectSortDate.selectByIndex(2);
        }

        List<WebElement> numberOfDateOfSearchResultItems = driver.findElements(By.className("item-date"));
        List<Date> dateList = new ArrayList<Date>();
        for (WebElement numberOfDateOfSearchResultItem : numberOfDateOfSearchResultItems) {
            try {
                Date date = new SimpleDateFormat("dd.MM.yyyy").parse(numberOfDateOfSearchResultItem.getText());
                dateList.add(date);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        for (int i = 0; i < (dateList.size() - 1); i++) {
			Assertions.assertTrue(dateList.get(i).compareTo(dateList.get(i + 1)) >= 0);
        }
    }

    private void waitForPage() {
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        WebElement breadCrumb = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("page-segment-breadcrumb")));
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
