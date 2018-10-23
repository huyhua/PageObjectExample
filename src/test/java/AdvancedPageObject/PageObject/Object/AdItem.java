package AdvancedPageObject.PageObject.Object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


    public AdItem(WebElement element) {
        this.wrappedElement = element;
    }

}
