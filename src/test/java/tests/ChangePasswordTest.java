package tests;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import pages.*;
import util.Data;
import util.DataFaker;

public class ChangePasswordTest extends TestConfig {

    @Test
    public void testSubmitFormWithInvalidOldPassword() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLoginPage();

        String newPassword = DataFaker.getPassword();

        loginPage.fillInUsernameAndPassword(Data.USERNAME, Data.PASSWORD);
        loginPage.submitForm();

        MyPostsPage myPostsPage = homePage.goMyPostsPage();
        ChangePasswordPage changePasswordPage = myPostsPage.goToChangePasswordPage();

        changePasswordPage.fillInForm(DataFaker.getPassword(), newPassword, newPassword);
        changePasswordPage.submitForm();

        Assert.assertEquals(changePasswordPage.getOldPasswordMessages(), "Invalid old password");
    }

    @Test
    public void testSubmitFormWithInvalidNewPassword() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLoginPage();

        String weakNewPassword = DataFaker.getShortPassword();

        loginPage.fillInUsernameAndPassword(Data.USERNAME, Data.PASSWORD);
        loginPage.submitForm();

        MyPostsPage myPostsPage = homePage.goMyPostsPage();
        ChangePasswordPage changePasswordPage = myPostsPage.goToChangePasswordPage();

        changePasswordPage.fillInForm(Data.PASSWORD, weakNewPassword, weakNewPassword);
        changePasswordPage.submitForm();

        Assert.assertTrue(changePasswordPage.getNewPasswordMessages().contains("Password must be between 8 and 20 characters long."));
    }

    @Test
    public void testSubmitFormWithInvalidConfirmNewPassword() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLoginPage();

        loginPage.fillInUsernameAndPassword(Data.USERNAME, Data.PASSWORD);
        loginPage.submitForm();

        MyPostsPage myPostsPage = homePage.goMyPostsPage();
        ChangePasswordPage changePasswordPage = myPostsPage.goToChangePasswordPage();

        changePasswordPage.fillInForm(Data.PASSWORD, DataFaker.getPassword(), DataFaker.getPassword());
        changePasswordPage.submitForm();

        Assert.assertEquals(changePasswordPage.getConfirmNewPasswordMessages(), "Those passwords didn’t match.");
    }

}
