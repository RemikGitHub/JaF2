package tests;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;
import util.Data;
import util.DataFaker;

public class SignUpTest extends TestConfig {

    @Test
    public void testSubmitFormWithExistingUsername() {
        HomePage homePage = new HomePage();
        SignUpPage signUpPage = homePage.goToSignUpPage();

        String password = DataFaker.getPassword();
        signUpPage.fillInForm(Data.USERNAME, DataFaker.getEmail(), password, password);
        signUpPage.submitForm();

        Assert.assertEquals(signUpPage.getUsernameErrorMessages(), "This username already exists.");
    }

    @Test
    public void testSubmitFormWithTooShortPassword() {
        HomePage homePage = new HomePage();
        SignUpPage signUpPage = homePage.goToSignUpPage();

        String shortPassword = DataFaker.getShortPassword();
        signUpPage.fillInForm(DataFaker.getLogin(), DataFaker.getEmail(), shortPassword, shortPassword);
        signUpPage.submitForm();

        Assert.assertTrue(signUpPage.getPasswordErrorMessages().contains("Password must be between 8 and 20 characters long."));
    }

    @Test
    public void testSubmitFormWithInvalidConfirmPassword() {
        HomePage homePage = new HomePage();
        SignUpPage signUpPage = homePage.goToSignUpPage();

        signUpPage.fillInForm(DataFaker.getLogin(), DataFaker.getEmail(), DataFaker.getPassword(), DataFaker.getPassword());
        signUpPage.submitForm();

        Assert.assertTrue(signUpPage.getConfirmPasswordErrorMessages().contains("Those passwords didn’t match."));
    }

    @Test
    public void testSubmitFormWithCorrectValues() {
        HomePage homePage = new HomePage();
        SignUpPage signUpPage = homePage.goToSignUpPage();

        String password = DataFaker.getPassword();
        String email = DataFaker.getEmail();

        signUpPage.fillInForm(DataFaker.getLogin(), email, password, password);
        LoginPage loginPage = signUpPage.submitForm();

        Assert.assertEquals(loginPage.getMessages(), "One more step\n" + "Activate your email: " + email);
    }
}
