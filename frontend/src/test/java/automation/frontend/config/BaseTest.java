package automation.frontend.config;

import automation.frontend.SpringConfig;
import automation.frontend.listener.ScreenshotListener;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;


@SpringBootTest(classes = SpringConfig.class)
@Listeners(ScreenshotListener.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    @Value("${app.url}")
    private String appUrl;
    @Autowired
    private WebDriver webDriver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        webDriver.get(getAppUrl());
    }

    protected WebDriver getWebDriver() {
        return webDriver;
    }

    protected String getAppUrl() {
        return appUrl;
    }
}