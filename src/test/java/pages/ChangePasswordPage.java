package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Action;
import util.Wait;

public class ChangePasswordPage extends BasePage {

    @FindBy(id = "oldPassword")
    private WebElement oldPasswordInput;

    @FindBy(id = "newPassword")
    private WebElement newPasswordInput;

    @FindBy(id = "confirmNewPassword")
    private WebElement confirmNewPasswordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement changePasswordButton;

    @FindBy(xpath = "//div/div[2]/div/form/div[1]/div/p")
    private WebElement oldPasswordMessages;

    @FindBy(xpath = "//div/div[2]/div/form/div[2]/div/p")
    private WebElement newPasswordMessages;

    @FindBy(xpath = "//div/div[2]/div/form/div[3]/div/p")
    private WebElement confirmNewPasswordMessages;

    public void fillInForm(String oldPassword, String newPassword, String confirmNewPassword) {
        Action.sendKeys(oldPasswordInput, oldPassword);
        Action.sendKeys(newPasswordInput, newPassword);
        Action.sendKeys(confirmNewPasswordInput, confirmNewPassword);
    }

    public LoginPage submitForm() {
        Action.click(changePasswordButton);

        return new LoginPage();
    }

    public String getOldPasswordMessages() {
        return Wait.waitToBeVisible(oldPasswordMessages).getText();
    }

    public String getNewPasswordMessages() {
        return Wait.waitToBeVisible(newPasswordMessages).getText();
    }

    public String getConfirmNewPasswordMessages() {
        return Wait.waitToBeVisible(confirmNewPasswordMessages).getText();
    }
}
