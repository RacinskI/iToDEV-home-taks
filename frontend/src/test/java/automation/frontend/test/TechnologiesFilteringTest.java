package automation.frontend.test;

import automation.frontend.config.BaseTest;
import automation.frontend.page.assertion.HomePageAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class TechnologiesFilteringTest extends BaseTest {
    @Autowired
    private HomePageAssertions homePageAssertions;

    @Test(groups = {"regression"}, description = "Description: Verify user can filter out technologies portfolio")
    void verifyTechnologiesFiltering() {
        homePageAssertions.assertAllPortfolioIsDisplayed();
        homePageAssertions.clickOnFilterByAndroidCheckbox();
        homePageAssertions.assertOnlyAndroidPortfolioIsDisplayed();
    }
}
