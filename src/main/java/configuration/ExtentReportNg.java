package configuration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public  class ExtentReportNg {


    public static ExtentReports getReportObject(){

        String path = System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("E-Commerce Result");
        reporter.config().setDocumentTitle("Test Result");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Ahmed Hassan");
        return extent;
    }

    public  void init(){

            //extent.createTest("init");



    }

}
