package automation.frontend.core;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.Proxy.ProxyType.MANUAL;

@Component
public class SeleniumDriverFactory implements DriverFactory {
    private final DriverType driverType;
    private final Proxy proxy;

    public SeleniumDriverFactory(Environment env) {
        driverType = DriverType.valueOf(env.getRequiredProperty("webDriver.browser").toUpperCase());

        if (env.containsProperty("webDriver.selenium.proxy.host") && env.containsProperty("webDriver.selenium.proxy.port")) {
            proxy = new Proxy();
            String proxyDetails = String.format("%s:%d", env.getRequiredProperty("webDriver.selenium.proxy.host"), env.getRequiredProperty("webDriver.selenium.proxy.port", Integer.class));
            proxy.setProxyType(MANUAL);
            proxy.setHttpProxy(proxyDetails);
            proxy.setSslProxy(proxyDetails);
        } else {
            proxy = null;
        }
    }

    @Override
    public WebDriver newInstance() {
        return driverType.getWebDriverObject(driverType.getDesiredCapabilities(proxy));
    }

    @Override
    public String name() {
        return "selenium";
    }
}

