package AdvancedPageObject.Test;

import AdvancedPageObject.PageObject.Object.AdItem;
import AdvancedPageObject.PageObject.Object.HomePage;
import AdvancedPageObject.PageObject.Object.ResultsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


class SearchTest extends TestBase {
    @Test
    void testCustomSearch() {
        driver.get(baseUrl);
        new HomePage(driver).allProperties()
                .location("Zurich")
                .filterRadius(4)
                .sortByDate(2)
                .filterByPrice(1000, 5000)
                .filter()
                .validate().resultCount(20)
                .sortedBy(Comparator.comparing(AdItem::getDate));
    }
}
