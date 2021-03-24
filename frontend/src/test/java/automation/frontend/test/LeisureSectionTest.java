package automation.frontend.test;

import automation.frontend.config.BaseTest;
import automation.frontend.page.HomePage;
import automation.frontend.page.assertion.HomePageAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LeisureSectionTest extends BaseTest {
    @Autowired
    private HomePageAssertions homePageAssertions;
    @Autowired
    private HomePage homePage;

    // *********Helper Method*********
    private void loadMoreImages(int pagesToLoad) {
        for (int i = 0; i < pagesToLoad; i++) {
            homePage.clickOnLoadMoreButton();
            homePage.scrollDownToLeisureSection();
            homePageAssertions.assertAllImagesAreLoaded();
        }
    }

    @BeforeMethod(groups = {"regression"}, description = "Description: Navigate to leisure section")
    void navigateToLeisureSection() {
        homePage.goTo(homePage.getPath());
        homePage.scrollDownToLeisureSection();
    }

    // *********Tests*********
    @Test(groups = {"regression"}, description = "Description: Verify each page loads unique images")
    void verifyEachPageLoadsUniqueImages() {
        loadMoreImages(3);
        homePageAssertions.assertImagesAreUnique();
    }

    @Test(groups = {"regression"}, description = "Description: Verify each page loads 12 images")
    void verifyEachPageLoadsExpectedNumberOfImages() {
        loadMoreImages(4);
        homePageAssertions.assertLoadedImageCount(5);
    }
}
