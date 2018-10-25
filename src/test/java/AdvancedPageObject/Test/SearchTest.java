package AdvancedPageObject.Test;

import AdvancedPageObject.PageObject.Object.AdItem;
import AdvancedPageObject.PageObject.Object.HomePage;
import org.junit.jupiter.api.Test;

import java.util.Comparator;


class SearchTest extends TestBase {
    @Test
    void testCustomSearch() {
        new HomePage().navigate().allProperties()
                .location("Zurich")
                .filterRadius(4)
                .sortByDate(2)
                .filterByPrice(1000, 5000)
                .filter()
                .validator().resultCount(20)
                .sortedBy(Comparator.comparing(AdItem::getDate));
    }
}
