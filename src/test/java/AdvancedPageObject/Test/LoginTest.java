package AdvancedPageObject.Test;

import AdvancedPageObject.PageObject.Object.HomePage;
import AdvancedPageObject.PageObject.Object.LoginPage;
import org.junit.jupiter.api.Test;

class LoginTest extends TestBase{
    @Test
    void testNavigateToLogin() {
        driver.get(baseUrl);
        HomePage home = new HomePage();
        home.login().validator().usernameIsFocused();
    }

    @Test
    void testLoginWrongInput() {
        driver.get("https://www.anibis.ch/de/login.aspx");
        LoginPage loginPage = new LoginPage();
        loginPage.fillUsername(randomString(8))
                .fillPassword(randomString(8))
                .login();

        loginPage.validator().errorShown();
    }

    @Test
    void testLoginCorrectData() {
        driver.get("https://www.anibis.ch/de/login.aspx");
        LoginPage loginPage = new LoginPage();
        loginPage.fillUsername("ani_test_2")
                .fillPassword("ani_test_2")
                .login();

        loginPage.validator().isInMeinAnibis();
    }
}
