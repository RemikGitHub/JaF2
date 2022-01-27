package pages;

import config.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Action;
import util.Wait;

public class BasePage {

    @FindBy(css = "a[href='/logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/form/span")
    private WebElement loggedUser;

    public BasePage() {
        PageFactory.initElements(WebDriverSingleton.getInstance(), this);
    }

    public String getLoggedUsername() {
        return Wait.waitToBeVisible(loggedUser).getText();
    }

    public void logout() {
        Action.click(logoutButton);
    }

    public boolean userIsLoggedOut() {
        Wait.waitToBeVisible(By.cssSelector("a[href='/login']"));

        return true;
    }
}
