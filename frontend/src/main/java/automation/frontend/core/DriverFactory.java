package automation.frontend.core;

import org.openqa.selenium.WebDriver;

public interface DriverFactory {
    WebDriver newInstance();

    String name();
}
