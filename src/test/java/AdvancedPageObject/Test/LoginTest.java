package AdvancedPageObject.Test;

import AdvancedPageObject.PageObject.Object.HomePage;
import AdvancedPageObject.PageObject.Object.LoginPage;
import org.junit.jupiter.api.Test;

class LoginTest extends TestBase{
    @Test
    void testNavigateToLogin() {
        HomePage home = new HomePage();
        home.navigate().login().validator().usernameIsFocused();
    }

    @Test
    void testLoginWrongInput() {
        LoginPage loginPage = new LoginPage();
        loginPage.navigate()
                .fillUsername(randomString(8))
                .fillPassword(randomString(8))
                .login();

        loginPage.validator().errorShown();
    }

    @Test
    void testLoginCorrectData() {
        LoginPage loginPage = new LoginPage();
        loginPage.navigate()
                .fillUsername("ani_test_2")
                .fillPassword("ani_test_2")
                .login();

        loginPage.validator().isInMeinAnibis();
    }
}
