package automation.frontend.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

public enum DriverType implements DriverSetup {
    FIREFOX {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            return addProxySettings(capabilities, proxySettings);
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            WebDriverManager.firefoxdriver().setup();
            var options = new FirefoxOptions();
            var path = new File("target/tmp/downloads").getAbsolutePath();
            options.addPreference("browser.download.folderList", 2);
            options.addPreference("browser.helperApps.alwaysAsk.force", false);
            options.addPreference("browser.download.dir", path);
            options.addPreference("browser.download.defaultFolder", path);
            options.addPreference("browser.download.manager.showWhenStarting", false);
            options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
            return new FirefoxDriver(options.merge(capabilities));
        }
    },
    CHROME {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Collections.singletonList("--no-default-browser-check"));
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            String OS = System.getProperty("os.name").toLowerCase();
            if (!OS.contains("win") && !OS.contains("mac")) {
                throw new IllegalStateException("*nix is not supported");
            }
            WebDriverManager.chromedriver().setup();
            HashMap<String, Object> chromePreferences = new HashMap<>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            chromePreferences.put("profile.default_content_settings.popups", 0);
            chromePreferences.put("download.default_directory", new File("target/tmp/downloads").getAbsolutePath());
            var options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePreferences);
            return new ChromeDriver(options.merge(capabilities));
        }
    },
    IE {
        public DesiredCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability("requireWindowFocus", true);
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            WebDriverManager.iedriver().setup();
            return new InternetExplorerDriver(new InternetExplorerOptions(capabilities));
        }
    };

    protected DesiredCapabilities addProxySettings(DesiredCapabilities capabilities, Proxy proxySettings) {
        if (null != proxySettings) {
            capabilities.setCapability(PROXY, proxySettings);
        }

        return capabilities;
    }
}