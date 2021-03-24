package automation.frontend.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AtlassianPage extends HomePage {

    @FindBy(id="atlassian")
    protected WebElement atlassianPageLocator;

    @PostConstruct
    private void init() {
        PageFactory.initElements(getDriver(), this);
    }

    @Override
    public String getPath() {
        return super.getPath() + "atlassian";
    }
}
