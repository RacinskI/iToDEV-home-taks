package automation.frontend.page.assertion;

import automation.frontend.page.AtlassianPage;
import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class AtlassianPageAssertion extends AtlassianPage {

    @Step("Assert user in 'Atlassian' page")
    public void assertUserInAtlassianPage() {
        waitForElementToLoad(atlassianPageLocator);
        assertThat(atlassianPageLocator.isDisplayed()).isTrue();
    }
}
