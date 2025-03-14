package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();
    
    public static String pathOfProject = System.getProperty("user.dir");
    public static String pathToExtentReport = pathOfProject+"/target/";

    public static void setExtent() {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String reportDate = formatter.format(new Date());
        
        String reportName = "ExtentReport_"+reportDate+".html";
        
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(pathToExtentReport+reportName);
        
        extent = new ExtentReports();
        
        htmlReporter.config().setDocumentTitle("Demo Report");
        htmlReporter.config().setReportName("Automation Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        
        extent.attachReporter(htmlReporter);
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static void flush() {
        extent.flush();
    }

    public static void createTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        testReport.set(test);
    }

    public static ExtentTest getTest() {
        return testReport.get();
    }
}
