package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import util.Action;
import util.Wait;

public class NewPostPage extends BasePage {

    @FindBy(id = "title")
    private WebElement titleInput;

    @FindBy(id = "category")
    private WebElement categoryInput;

    @FindBy(id = "content")
    private WebElement contentInput;

    @FindBy(css = "button[type='submit']")
    private WebElement addButton;

    @FindBy(xpath = "//main/div/div/div/div/div/div")
    private WebElement message;

    @FindBy(xpath = "//div/div/div/div/div/form/div[1]/div/p")
    private WebElement titleErrorMessage;

    @FindBy(xpath = "//div/div/div/div/div/form/div[3]/div/p")
    private WebElement contentErrorMessage;

    @FindBy(css = "a[href='/my-posts']")
    private WebElement myPostsButton;

    public void writeNewPost(String title, String category, String content) {
        Action.sendKeys(titleInput, title);
        new Select(categoryInput).selectByValue(category);
        Action.sendKeys(contentInput, content);

        Action.click(addButton);
    }

    public MyPostsPage goMyPostsPage() {
        Action.click(myPostsButton);

        return new MyPostsPage();
    }

    public String getMessage(){
        return Wait.waitToBeVisible(message).getText();
    }

    public String getTitleErrorMessage(){
        return Wait.waitToBeVisible(titleErrorMessage).getText();
    }

    public String getContentErrorMessage(){
        return Wait.waitToBeVisible(contentErrorMessage).getText();
    }
}
