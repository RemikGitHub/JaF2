package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Action;
import util.Wait;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement loginInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//div/div[3]/div/form/div[2]/div")
    private WebElement errorMessages;

    @FindBy(xpath = "//div/div[2]/div/div")
    private WebElement infoMessages;

    public void fillInUsernameAndPassword(String username, String password) {
        Action.sendKeys(loginInput, username);
        Action.sendKeys(passwordInput, password);
    }

    public void submitForm() {
        Action.click(signInButton);
    }

    public String getErrorMessages() {
        return Wait.waitToBeVisible(errorMessages).getText();
    }

    public String getMessages() {
        return Wait.waitToBeVisible(infoMessages).getText();
    }

}
