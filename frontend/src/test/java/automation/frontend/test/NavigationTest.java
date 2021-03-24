package automation.frontend.test;

import automation.frontend.config.BaseTest;
import automation.frontend.page.AtlassianPage;
import automation.frontend.page.CareerPage;
import automation.frontend.page.HomePage;
import automation.frontend.page.assertion.AtlassianPageAssertion;
import automation.frontend.page.assertion.CareerPageAssertions;
import automation.frontend.page.assertion.HomePageAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {
    @Autowired
    private HomePage homePage;
    @Autowired
    private HomePageAssertions homePageAssertions;
    @Autowired
    private AtlassianPage atlassianPage;
    @Autowired
    private AtlassianPageAssertion atlassianPageAssertion;
    @Autowired
    private CareerPage careerPage;
    @Autowired
    private CareerPageAssertions careerPageAssertions;

    @BeforeMethod(groups = {"smoke"}, description = "Description: Navigate to landing page")
    void navigateToLandingPage() {
        homePage.goTo(homePage.getPath());
        homePageAssertions.assertUserInLandingPage();
    }

    @Test(groups = {"smoke"}, description = "Description: Verify user can navigate between 'ito.lt -> ito.lt/atlassian' pages")
    void verifyUserCanNavigateToAtlassianPage() {
        homePage.clickOnServiceDropdown();
        homePage.clickOnAtlassianOption();
        atlassianPageAssertion.assertUserInAtlassianPage();
    }

    @Test(groups = {"smoke"}, description = "Description: Verify user can navigate between 'ito.lt -> ito.lt/career' pages")
    void verifyUserCanNavigateToCareerPage() {
        homePage.clickOnCareerOption();
        careerPageAssertions.assertUserInCareerPage();
    }

    @Test(groups = {"smoke"}, description = "Description: Verify user can navigate between 'ito.lt -> ito.lt/atlassian -> ito.lt/career' pages")
    void verifyUserCanNavigateBetweenPages() {
        homePage.clickOnServiceDropdown();
        homePage.clickOnAtlassianOption();
        atlassianPageAssertion.assertUserInAtlassianPage();
        atlassianPageAssertion.clickOnCareerOption();
        careerPageAssertions.assertUserInCareerPage();
    }

    @Test(groups = {"smoke"}, description = "Description: Verify permalinks work as expected")
    void verifyPermalinkFunctionality() {
        atlassianPage.goTo(atlassianPage.getPath());
        atlassianPageAssertion.assertUserInAtlassianPage();
        careerPage.goTo(careerPage.getPath());
        careerPageAssertions.assertUserInCareerPage();
    }
}
