package PageObject.Test;

import PageObject.PageObject.HomePage;
import PageObject.PageObject.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

class LoginTest {
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
    void testNavigateToLogin() {
        driver.get(baseUrl);
        HomePage home = new HomePage(driver);
        home.toLogin();
        LoginPage loginPage = new LoginPage(driver);

        Assertions.assertTrue(loginPage.isInLoginPage());
        Assertions.assertTrue(loginPage.usernameIsFocused(),"Username field is not focused");
    }

    @Test
    void testLoginWrongInput() {
        driver.get("https://www.anibis.ch/de/login.aspx");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsername(randomString(8));
        loginPage.fillPassword(randomString(8));
        loginPage.login();

        WebElement highlightField = driver
                .findElement(By.xpath(".//input[@name='ctl00$phlContent$txtUsername' and @aria-required='true']"));

        Assertions.assertFalse(driver.findElements(By.className("error-msg")).isEmpty());
        Assertions.assertTrue(highlightField.isDisplayed());
    }

    @Test
    void testLoginCorrectData() {
        driver.get("https://www.anibis.ch/de/login.aspx");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsername("ani_test_2");
        loginPage.fillPassword("ani_test_2");
        loginPage.login();

        Assertions.assertEquals(driver.findElement(By.id("ctl00_Header1_ctlHeaderMetaBar_ucMainLinks_hypMyAnibis")).getText(), "Mein Anibis");
    }

    private String randomString(int length) {
        return new Random().ints(48,123)
                .filter(i -> (i < 58) || (i > 64 && i < 91) || (i > 96))
                .limit(length)
                .collect(StringBuilder::new, (sb, i) -> sb.append((char) i), StringBuilder::append).toString();
    }

}
