package automation.frontend.page.assertion;

import automation.frontend.page.CareerPage;
import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class CareerPageAssertions extends CareerPage {

    @Step("Assert user in 'Career' page")
    public void assertUserInCareerPage() {
        waitForElementToLoad(careerPageLocator);
        assertThat(careerPageLocator.isDisplayed()).isTrue();
    }
}
