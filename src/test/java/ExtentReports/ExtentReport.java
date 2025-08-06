package ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.BeforeSuite;

public class ExtentReport {

    ExtentReports extent;
    @BeforeSuite
    public void config(){

        String path = System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("E-Commerce Result");
        reporter.config().setDocumentTitle("Test Result");
         extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Ahmed Hassan");
    }

    public  void initialReport(){

            extent.createTest("init");



    }

}
