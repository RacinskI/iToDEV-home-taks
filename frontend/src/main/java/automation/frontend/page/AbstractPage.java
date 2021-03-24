package automation.frontend.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.join;

@Component
public abstract class AbstractPage {
    @Autowired
    private WebDriverWait driverWait;
    @Autowired
    private WebDriver driver;
    @Value("${app.url}")
    private String appUrl;
    private static final String PATH = "";

    @PostConstruct
    private void init() {
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getDriverWait() {
        return driverWait;
    }

    public String getPath() {
        return PATH;
    }

    //*********Action*********
    protected Actions action() {
        return new Actions(driver);
    }

    public void goTo(String path) {
        var p = join(path.split("/"), "/");
        driver.get(String.format("%s/%s", appUrl, p));
    }
    protected void click(WebElement elementLocator) {
        action().click(elementLocator).perform();
    }

    public List<String> getImageSource(List<WebElement> elementLocator) {
        return elementLocator.stream().map(webElement -> webElement.getAttribute("src")).collect(Collectors.toList());
    }

    protected void focusElement(WebElement elementLocator) {
        action().moveToElement(elementLocator).perform();
    }

    //*********Wait*********
    protected void waitForElementToBeClickable(WebElement elementLocator) {
        driverWait.until(ExpectedConditions.elementToBeClickable(elementLocator));
    }

    protected void waitForElementToLoad(WebElement elementLocator) {
        driverWait.until(ExpectedConditions.visibilityOf(elementLocator));
    }

    protected void waitForElementAttributeToContain(WebElement elementLocator, String attribute, String value) {
        driverWait.until(ExpectedConditions.attributeContains(elementLocator, attribute, value));
    }
}
