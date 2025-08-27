package selenium.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportsNG {
    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "/target/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Framework report");
        reporter.config().setDocumentTitle("Test repot");
        ExtentReports extend = new ExtentReports();
        extend.attachReporter(reporter);
        extend.setSystemInfo("Tester", "Nithishkumar");
        return extend;
    }
}
