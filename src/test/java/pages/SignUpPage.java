package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Action;
import util.Wait;

public class SignUpPage extends BasePage {

    @FindBy(id = "username")
    private WebElement loginInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement createButton;

    @FindBy(xpath = "//div/div[2]/div/form/div[1]/div")
    private WebElement usernameErrorMessages;

    @FindBy(xpath = "//div/div[2]/div/form/div[2]/div")
    private WebElement emailErrorMessages;

    @FindBy(xpath = "//div/div[2]/div/form/div[3]/div")
    private WebElement passwordErrorMessages;

    @FindBy(xpath = "//div/div[2]/div/form/div[4]/div")
    private WebElement confirmPasswordErrorMessages;

    public void fillInForm(String username, String email, String password, String confirmPassword) {
        Action.sendKeys(loginInput, username);
        Action.sendKeys(emailInput, email);
        Action.sendKeys(passwordInput, password);
        Action.sendKeys(confirmPasswordInput, confirmPassword);
    }

    public LoginPage submitForm() {
        Action.click(createButton);

        return new LoginPage();
    }

    public String getUsernameErrorMessages() {
        return Wait.waitToBeVisible(usernameErrorMessages).getText();
    }

    public String getEmailErrorMessages() {
        return Wait.waitToBeVisible(emailErrorMessages).getText();
    }

    public String getPasswordErrorMessages() {
        return Wait.waitToBeVisible(passwordErrorMessages).getText();
    }

    public String getConfirmPasswordErrorMessages() {
        return Wait.waitToBeVisible(confirmPasswordErrorMessages).getText();
    }
}
