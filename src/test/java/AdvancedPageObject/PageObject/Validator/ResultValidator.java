package AdvancedPageObject.PageObject.Validator;

import AdvancedPageObject.PageObject.Map.ResultsPageMap;
import AdvancedPageObject.PageObject.Object.AdItem;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultValidator {
    private WebDriver driver;
    private ResultsPageMap map;

    public ResultValidator(WebDriver driver) {
        this.driver = driver;
    }

    public ResultValidator resultCount(int count) {
        Assertions.assertEquals(count, map.getItems().size());
        return this;
    }

    public ResultValidator sortedBy(Comparator<? super AdItem> c) {
        List<AdItem> list = map.getItems().stream().map(AdItem::new).collect(Collectors.toList());
        Assertions.assertEquals(list.stream().sorted(c).collect(Collectors.toList()), list);
        return this;
    }
}
