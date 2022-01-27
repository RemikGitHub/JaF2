package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Action;

public class HomePage extends BasePage {

    @FindBy(css = "a[href='/about-us']")
    private WebElement aboutUsButton;

    @FindBy(css = "a[href='/my-posts']")
    private WebElement myPostsButton;

    @FindBy(xpath = "//*[@id=\"submenu\"]")
    private WebElement categoriesButton;

    @FindBy(css = "a[href='/frontend']")
    private WebElement frontendButton;

    @FindBy(css = "a[href='/backend']")
    private WebElement backendButton;

    @FindBy(css = "a[href='/mobile']")
    private WebElement mobileButton;

    @FindBy(css = "a[href='/']")
    private WebElement homeButton;

    @FindBy(css = "a[href='/login']")
    private WebElement loginButton;

    @FindBy(css = "a[href='/signup']")
    private WebElement signUpButton;

    public HomePage() {
        super();
    }

    public AboutUsPage goToAboutUsPage() {
        Action.click(aboutUsButton);

        return new AboutUsPage();
    }

    public HomePage goToHomePage() {
        Action.click(homeButton);

        return new HomePage();
    }

    public LoginPage goToLoginPage() {
        Action.click(loginButton);

        return new LoginPage();
    }

    public SignUpPage goToSignUpPage() {
        Action.click(signUpButton);

        return new SignUpPage();
    }

    public MyPostsPage goMyPostsPage() {
        Action.click(myPostsButton);

        return new MyPostsPage();
    }
}
