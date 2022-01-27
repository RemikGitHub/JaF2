package tests;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import util.Data;
import util.DataFaker;

public class LoginTests extends TestConfig {

    @Test
    public void testSubmitFormWithValidUsernameAndPassword() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLoginPage();

        loginPage.fillInUsernameAndPassword(DataFaker.getLogin(), DataFaker.getPassword());
        loginPage.submitForm();

        Assert.assertEquals(loginPage.getErrorMessages(), "Wrong username or password");
    }

    @Test
    public void testSubmitFormWithCorrectUsernameAndPassword() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLoginPage();

        loginPage.fillInUsernameAndPassword(Data.USERNAME, Data.PASSWORD);
        loginPage.submitForm();

        Assert.assertEquals(loginPage.getLoggedUsername(), Data.USERNAME);
    }

    @Test
    public void testUserIsLoggedOut() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLoginPage();

        loginPage.fillInUsernameAndPassword(Data.USERNAME, Data.PASSWORD);
        loginPage.submitForm();

        homePage.logout();
        Assert.assertTrue(homePage.userIsLoggedOut());
    }
}
