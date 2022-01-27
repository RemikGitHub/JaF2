package tests;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import pages.*;
import util.Data;
import util.DataFaker;

public class SinglePostTest extends TestConfig {

    @Test
    public void testNewPostCorrectTitleAndContent() {
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
        SinglePostPage singlePostPage = myPostsPage.goToFirstPost();

        Assert.assertEquals(singlePostPage.getPostTitle(), title);
        Assert.assertEquals(singlePostPage.getPostContent(), content);
    }

    @Test
    public void testNewCommentAppear() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLoginPage();

        String commentContent = DataFaker.getContent();

        loginPage.fillInUsernameAndPassword(Data.USERNAME, Data.PASSWORD);
        loginPage.submitForm();

        MyPostsPage myPostsPage = homePage.goMyPostsPage();
        NewPostPage newPostPage = myPostsPage.goToNewPostPage();

        newPostPage.writeNewPost(DataFaker.getTitle(), Data.FRONTEND_CATEGORY, DataFaker.getContent());

        newPostPage.goMyPostsPage();
        SinglePostPage singlePostPage = myPostsPage.goToFirstPost();

        singlePostPage.writeComment(commentContent);

        Assert.assertEquals(singlePostPage.getFirstComment(), commentContent);
    }
}
