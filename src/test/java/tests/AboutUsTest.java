package tests;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import pages.AboutUsPage;
import pages.HomePage;

public class AboutUsTest extends TestConfig {

    @Test
    public void testCorrectAboutUsInformation() {
        HomePage homePage = new HomePage();
        AboutUsPage aboutUsPage = homePage.goToAboutUsPage();

        Assert.assertEquals(aboutUsPage.getFirstCardText(), "Remigusz Ratajski\n" + "Scrum Master");
        Assert.assertEquals(aboutUsPage.getSecondCardText(), "Bartosz Płonka\n" + "Graphic designer");
    }
}
