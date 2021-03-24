package automation.frontend.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CareerPage extends HomePage {

    @FindBy(id="career")
    protected WebElement careerPageLocator;

    @PostConstruct
    private void init() {
        PageFactory.initElements(getDriver(), this);
    }

    @Override
    public String getPath() {
        return super.getPath() + "career";
    }
}
