package PageObject.Test;

import PageObject.PageObject.HomePage;
import PageObject.PageObject.ResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


class SearchTest {
    private WebDriver driver;
    private String baseUrl = "https://www.anibis.ch/de/default.aspx";

    @BeforeEach
    void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void testCustomSearch() {
        driver.get(baseUrl);
        new HomePage(driver).allProperties();
        ResultsPage results = new ResultsPage(driver);


        results.location("Zurich");
        results.filterRadius(4);
        results.sortByDate(2);
        results.filterByPrice(1000, 5000);

        List<ResultsPage.AdItem> items = results.getItems();

        Assertions.assertEquals(20, items.size());

        List<ResultsPage.AdItem> sortedItems = new ArrayList<>(items);
        sortedItems.sort(Comparator.comparing(ResultsPage.AdItem::getDate));

        Assertions.assertEquals(sortedItems, items);
    }

}
