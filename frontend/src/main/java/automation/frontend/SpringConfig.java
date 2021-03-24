package automation.frontend;

import automation.frontend.core.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.util.List;

@SpringBootConfiguration
@ComponentScan("automation.frontend")
@EnableAutoConfiguration
public class SpringConfig {
    @Scope("singleton")
    @Bean(destroyMethod = "quit")
    WebDriver webDriver(List<DriverFactory> driverFactories, @Value("${webDriver.mode}") String mode) {
        return driverFactories.stream().filter(df -> df.name()
                .equalsIgnoreCase(mode))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unknown WebDriver mode: " + mode))
                .newInstance();
    }

    @Bean
    WebDriverWait webDriverWait(WebDriver webDriver, @Value("${wait.explicit.seconds}") Long waitExplicitSec) {
        return new WebDriverWait(webDriver, waitExplicitSec);
    }

    @Bean
    ExtentReports extentReports(@Value("${report.extent.file}") String extentReportFile) {
        var file = new File(extentReportFile);
        //noinspection ResultOfMethodCallIgnored
        file.getParentFile().mkdirs();

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(file);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(file.getName());
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(file.getName());

        var extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }
}
