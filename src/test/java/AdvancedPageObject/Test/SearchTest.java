package AdvancedPageObject.Test;

import AdvancedPageObject.PageObject.HomePage;
import AdvancedPageObject.PageObject.ResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


class SearchTest extends TestBase {
    @Test
    void testCustomSearch() {
        driver.get(baseUrl);
        ResultsPage results = new HomePage(driver).allProperties()
                .location("Zurich")
                .filterRadius(4)
                .sortByDate(2)
                .filterByPrice(1000, 5000);

        List<ResultsPage.AdItem> items = results.getItems();

        Assertions.assertEquals(20, items.size());

        List<ResultsPage.AdItem> sortedItems = new ArrayList<>(items);
        sortedItems.sort(Comparator.comparing(ResultsPage.AdItem::getDate));

        Assertions.assertEquals(sortedItems, items);
    }

}
