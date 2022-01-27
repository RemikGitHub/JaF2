package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Action;
import util.Wait;

public class SinglePostPage extends BasePage {

    @FindBy(xpath = "//div/div/div/div[1]/div/div/h2")
    private WebElement postTitle;

    @FindBy(xpath = "//div/div/div/div[1]/div/p[2]")
    private WebElement postContent;

    @FindBy(id = "commnetContent")
    private WebElement commentContentInput;

    @FindBy(xpath = "//div/div/div/div[2]/form/div/div[2]/button")
    private WebElement addCommentButton;

    @FindBy(xpath = "//div/div/div/div[2]/div/div/p")
    private WebElement firstComment;

    public String getPostTitle(){
        return Wait.waitToBeVisible(postTitle).getText();
    }

    public String getPostContent(){
        return Wait.waitToBeVisible(postContent).getText();
    }

    public void writeComment(String content){
        Action.sendKeys(commentContentInput, content);
        Action.click(addCommentButton);
    }

    public String getFirstComment(){
        return Wait.waitToBeVisible(firstComment).getText();
    }
}
