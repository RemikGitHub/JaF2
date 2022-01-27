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

public class MyPostsTest extends TestConfig {

    @Test
    public void testNewPostAppear() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLoginPage();

        String title = DataFaker.getTitle();
        String content = DataFaker.getContent();

        loginPage.fillInUsernameAndPassword(Data.USERNAME, Data.PASSWORD);
        loginPage.submitForm();

        MyPostsPage myPostsPage = homePage.goMyPostsPage();
        NewPostPage newPostPage = myPostsPage.goToNewPostPage();

        newPostPage.writeNewPost(title, Data.FRONTEND_CATEGORY, content);

        newPostPage.goMyPostsPage();

        Assert.assertEquals(myPostsPage.getFirstPostTitle(), title);
        Assert.assertEquals(myPostsPage.getFirstPostContent(), content);
    }

    @Test
    public void testDeleteUserDialog() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLoginPage();

        loginPage.fillInUsernameAndPassword(Data.USERNAME, Data.PASSWORD);
        loginPage.submitForm();

        MyPostsPage myPostsPage = homePage.goMyPostsPage();
        myPostsPage.showDeleteAccountDialog();

        Assert.assertTrue(myPostsPage.deleteAccountDialogIsVisible());
    }
}
