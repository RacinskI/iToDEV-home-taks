package automation.frontend.listener;

import automation.lib.util.SpringApplicationContext;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotListener extends TestListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotListener.class);

    @SuppressWarnings({"UnusedReturnValue", "ResultOfMethodCallIgnored"})
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(File target) throws IOException {
        var tmp = ((TakesScreenshot) SpringApplicationContext.getBean(WebDriver.class)).getScreenshotAs(OutputType.FILE);
        target.delete();
        target.getParentFile().mkdirs();
        FileUtils.copyFile(tmp, target);
        return Files.readAllBytes(target.toPath());
    }

    @Override
    public void onTestFailure(ITestResult failingTest) {
        try {
            var screenshotDirectory = SpringApplicationContext.getProperty("report.screenshots");
            File screenshot = new File(screenshotDirectory, String.format("%s_%s.png", System.currentTimeMillis(), failingTest.getName()));
            takeScreenshot(screenshot);
        } catch (Exception ex) {
            LOGGER.error("Unable to capture screenshot: ", ex);
        }
    }
}