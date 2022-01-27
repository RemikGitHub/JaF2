package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Action;
import util.Wait;

public class MyPostsPage extends BasePage {

    @FindBy(css = "a[href='/new-post']")
    private WebElement newPostButton;

    @FindBy(css = "a[href='/change-password']")
    private WebElement changePasswordButton;

    @FindBy(xpath = "//div/div/div/div/div/div[1]/div/div[2]/div/div[1]/a")
    private WebElement firstPostButton;

    @FindBy(xpath = "//div/div/aside/div/div/div/div/button")
    private WebElement deleteAccountButton;

    @FindBy(xpath = "//*[@id=\"deleteUser\"]/div/div")
    private WebElement deleteAccountDialog;

    @FindBy(xpath = "//div/div/div/div/div/div[1]/div/div[1]/h5")
    private WebElement firstPostTitle;

    @FindBy(xpath = "//div/div/div/div/div/div[1]/div/div[2]/p")
    private WebElement firstPostContent;

    public NewPostPage goToNewPostPage(){
        Action.click(newPostButton);

        return new NewPostPage();
    }

    public ChangePasswordPage goToChangePasswordPage(){
        Action.click(changePasswordButton);

        return new ChangePasswordPage();
    }

    public SinglePostPage goToFirstPost(){
        Action.click(firstPostButton);

        return new SinglePostPage();
    }

    public String getFirstPostTitle(){
        return Wait.waitToBeVisible(firstPostTitle).getText();
    }

    public String getFirstPostContent(){
        return Wait.waitToBeVisible(firstPostContent).getText();
    }

    public void showDeleteAccountDialog(){
        Action.click(deleteAccountButton);
    }

    public boolean deleteAccountDialogIsVisible(){
        return Wait.waitToBeVisible(deleteAccountButton).isDisplayed();
    }
}
