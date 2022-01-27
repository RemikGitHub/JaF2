package tests;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MyPostsPage;
import pages.NewPostPage;
import util.Data;
import util.DataFaker;

public class NewPostTest extends TestConfig {

    @Test
    public void testAddNewPost() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLoginPage();

        loginPage.fillInUsernameAndPassword(Data.USERNAME, Data.PASSWORD);
        loginPage.submitForm();

        MyPostsPage myPostsPage = homePage.goMyPostsPage();
        NewPostPage newPostPage = myPostsPage.goToNewPostPage();

        newPostPage.writeNewPost(DataFaker.getTitle(), Data.FRONTEND_CATEGORY, DataFaker.getContent());

        Assert.assertEquals(newPostPage.getMessage(), "Well done!\n" + "The post has been added");
    }

    @Test
    public void testAddNewPostWithTooShortTitle() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLoginPage();

        loginPage.fillInUsernameAndPassword(Data.USERNAME, Data.PASSWORD);
        loginPage.submitForm();

        MyPostsPage myPostsPage = homePage.goMyPostsPage();
        NewPostPage newPostPage = myPostsPage.goToNewPostPage();

        newPostPage.writeNewPost("a", Data.FRONTEND_CATEGORY, DataFaker.getContent());

        Assert.assertEquals(newPostPage.getTitleErrorMessage(), "Post title must be between 3 and 80 characters long.");
    }

    @Test
    public void testAddNewPostWithTooShortContent() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLoginPage();

        loginPage.fillInUsernameAndPassword(Data.USERNAME, Data.PASSWORD);
        loginPage.submitForm();

        MyPostsPage myPostsPage = homePage.goMyPostsPage();
        NewPostPage newPostPage = myPostsPage.goToNewPostPage();

        newPostPage.writeNewPost(DataFaker.getTitle(), Data.FRONTEND_CATEGORY, "a");

        Assert.assertEquals(newPostPage.getContentErrorMessage(), "Post must be at least 3 characters long.");
    }

}
