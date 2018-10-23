package NoPageObject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    WebDriver driver;
    String baseUrl = "https://www.anibis.ch/de/default.aspx";
    String loginUrl = "https://www.anibis.ch/login.aspx";

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
    public void testNavigateToLogin() {
        driver.get(baseUrl);
        WebElement loginButton = driver.findElement(By.id("ctl00_Header1_ctlHeaderMetaBar_ucMainLinks_hypMyAnibis"));
        loginButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_Header1_hypLogo")));
        WebElement username = driver.findElement(By.id("ctl00_phlContent_txtUsername"));

        Assertions.assertEquals(driver.switchTo().activeElement(), username);
    }

    @Test
    public void testLoginWrongInput() {
        driver.get(loginUrl);
        WebElement username = driver.findElement(By.id("ctl00_phlContent_txtUsername"));
        WebElement password = driver.findElement(By.id("ctl00_phlContent_txtPassword"));
        username = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(username));
		username.sendKeys(randomString(8));
		password.sendKeys(randomString(8));
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        WebElement loginBtn = driver.findElement(By.id("ctl00_phlContent_btnLogin"));
        loginBtn.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");


        WebElement highlightField = driver
                .findElement(By.xpath(".//input[@name='ctl00$phlContent$txtUsername' and @aria-required='true']"));

        Assertions.assertFalse(driver.findElements(By.className("error-msg")).isEmpty());
        Assertions.assertTrue(highlightField.isDisplayed());
    }

    @Test
    public void testLoginCorrectData() {
        driver.get(loginUrl);
        WebElement username = driver.findElement(By.id("ctl00_phlContent_txtUsername"));
        WebElement password = driver.findElement(By.id("ctl00_phlContent_txtPassword"));
        username = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(username));
        username.clear();
        username.sendKeys("ani_test_2");
        password.clear();
        password.sendKeys("ani_test_2");

        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        WebElement loginBtn = driver.findElement(By.id("ctl00_phlContent_btnLogin"));
        loginBtn.click();

        Assertions.assertEquals(driver.findElement(By.id("ctl00_Header1_ctlHeaderMetaBar_ucMainLinks_hypMyAnibis")).getText(), "Mein Anibis");
    }

    private String randomString(int length) {
        return new Random().ints(48,123)
                .filter(i -> (i < 58) || (i > 64 && i < 91) || (i > 96))
                .limit(length)
                .collect(StringBuilder::new, (sb, i) -> sb.append((char) i), StringBuilder::append).toString();
    }

}
