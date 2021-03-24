package automation.lib.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import automation.lib.util.SpringApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Optional;

public class ExtentListener implements ITestListener {
    private final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private ExtentReports extent;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtentListener.class);

    @Override
    public synchronized void onStart(ITestContext context) {
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        if (extent == null) {
            extent = SpringApplicationContext.getBean(ExtentReports.class);
        }
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        Optional.ofNullable(test.get()).map(t -> t.skip(result.getThrowable()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LOGGER.info("onTestFailedButWithinSuccessPercentage for {}", result.getMethod().getMethodName());
    }
}
