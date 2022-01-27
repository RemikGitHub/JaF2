package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Wait;

public class AboutUsPage extends BasePage {

    @FindBy(xpath = "//main/div/div/div[1]/div/div")
    private WebElement firstCard;

    @FindBy(xpath = "//main/div/div/div[2]/div/div")
    private WebElement secondCard;

    public AboutUsPage() {
        super();
    }

    public String getFirstCardText() {
        return Wait.waitToBeVisible(firstCard).getText();
    }

    public String getSecondCardText() {
        return Wait.waitToBeVisible(secondCard).getText();
    }

}
