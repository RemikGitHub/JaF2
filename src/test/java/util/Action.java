package util;

import org.openqa.selenium.WebElement;

public class Action {

    public static void click(WebElement webElement) {
        Wait.waitToBeClickable(webElement);
        webElement.click();
    }

    public static void sendKeys(WebElement webElement, String text) {
        Wait.waitToBeVisible(webElement);
        webElement.sendKeys(text);
    }
}
