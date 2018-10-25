package AdvancedPageObject.Test;

import AdvancedPageObject.PageObject.WebDriver.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public abstract class TestBase {
    protected WebDriver driver;

    @BeforeEach
    void setup() {
        Driver.startDriver();
        driver = Driver.getDriver();
    }

    @AfterEach
    void tearDown() {
        Driver.stopDriver();
        driver = null;
    }

    String randomString(int length) {
        return new Random().ints(48,123)
                .filter(i -> (i < 58) || (i > 64 && i < 91) || (i > 96))
                .limit(length)
                .collect(StringBuilder::new, (sb, i) -> sb.append((char) i), StringBuilder::append).toString();
    }
}
