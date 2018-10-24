package AdvancedPageObject.PageObject.WebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {
    private static WebDriverWait driverWait;
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if(driver == null) throw new NullPointerException("The WebDriver browser instance was not initialized. You should first call the method Start.");

        return driver;
    }

    public static WebDriverWait getDriverWait() {
        if(driverWait == null) throw new NullPointerException("The WebDriver browser instance was not initialized. You should first call the method Start.");

        return driverWait;
    }

    public static void startDriver() {
        Driver.driver = new ChromeDriver();
        Driver.driverWait = new WebDriverWait(driver, 10);
    }

    public static void stopDriver() {
        Driver.driver.quit();
        Driver.driver = null;
        Driver.driverWait = null;
    }

}
