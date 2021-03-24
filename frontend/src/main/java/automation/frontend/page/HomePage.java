package automation.frontend.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class HomePage extends AbstractPage {

    @FindBy(id = "home")
    protected WebElement homePageLocator;
    @FindBy(id = "services-dropdown")
    protected WebElement serviceDropdown;
    @FindBy(css = "[class^='dropdown-menu'] > a:first-child")
    protected WebElement atlassianToolButton;
    @FindBy(css = "[routerlink='/career']")
    protected WebElement careerNavigationButton;
    @FindBy(css = "[class^='custom-control-wrapper']:nth-child(2) > label > span[class='custom-control-indicator']")
    protected WebElement filterByAndroidCheckbox;
    @FindBy(css = "[data-filters*='android']")
    protected List<WebElement> androidPortfolioList;
    @FindBy(css = "div[data-filters]")
    protected List<WebElement> allPortfolioList;
    @FindBy(css = "[angularticsaction='LeisureGalleryOpen']")
    protected List<WebElement> leisureImages;
    @FindBy(css = "[angularticsaction='LoadMoreLeisureGalleryItems']")
    protected WebElement loadMoreButton;


    @PostConstruct
    private void init() {
        PageFactory.initElements(getDriver(), this);
    }

    @Step("Click on service dropdown")
    public void clickOnServiceDropdown() {
        waitForElementToBeClickable(serviceDropdown);
        serviceDropdown.click();
    }

    @Step("Select atlassian option from service dropdown")
    public void clickOnAtlassianOption() {
        atlassianToolButton.click();
    }

    @Step("Click on 'Career' from navigation bar")
    public void clickOnCareerOption() {
        careerNavigationButton.click();
    }

    @Step("Click filter by android checkbox")
    public void clickOnFilterByAndroidCheckbox() {
        waitForElementToLoad(filterByAndroidCheckbox);
        click(filterByAndroidCheckbox);
    }

    @Step("Click on load more button")
    public void clickOnLoadMoreButton() {
        loadMoreButton.click();
    }

    @Step("Scroll down to leisure section")
    public void scrollDownToLeisureSection() {
        waitForElementToLoad(loadMoreButton);
        focusElement(loadMoreButton);
    }
}
