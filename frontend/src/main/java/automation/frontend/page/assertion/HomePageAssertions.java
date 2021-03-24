package automation.frontend.page.assertion;

import automation.frontend.page.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
@Component
public class HomePageAssertions extends HomePage {

    @Step("Assert user in 'Landing' page")
    public void assertUserInLandingPage() {
        waitForElementToLoad(homePageLocator);
        assertThat(homePageLocator.isDisplayed()).isTrue();
    }

    @Step("Assert only android portfolio is displayed")
    public void assertOnlyAndroidPortfolioIsDisplayed() {
        for(WebElement elements : androidPortfolioList) {
            waitForElementAttributeToContain(elements, "class", "shuffle-item--visible");
        }
    }

    @Step("Assert all portfolio is displayed")
    public void assertAllPortfolioIsDisplayed() {
        for(WebElement elements : allPortfolioList) {
            waitForElementAttributeToContain(elements, "class", "shuffle-item--visible");
        }
    }

    @Step("Assert all images are loaded")
    public void assertAllImagesAreLoaded() {
        for(WebElement elements : leisureImages) {
            waitForElementAttributeToContain(elements, "class", "ng-lazyloaded");
        }
    }

    @Step("Assert expected number of images are loaded")
    public void assertLoadedImageCount(int pagesLoaded) {
        assertThat(leisureImages.size()).isEqualTo(12*pagesLoaded);
    }

    @Step("Assert newly loaded leisure images are unique")
    public void assertImagesAreUnique() {
        List<String> allImageSources = getImageSource(leisureImages);
        Set<String> uniqueImageSources = new HashSet<String>(allImageSources);
        assertThat(allImageSources.size()).isEqualTo(uniqueImageSources.size());
    }
}
