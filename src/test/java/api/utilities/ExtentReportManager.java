package api.utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter; //Look & feel of the report
    public ExtentReports extent; //Project common data e..g project name, 
    public ExtentTest test; //Creating entries in the report

    String repName;

    public void onStart(ITestContext testContext) {
        // Generate a dynamic timestamp for the report name to avoid overwriting previous results
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        
        // Define the destination path for the HTML report
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        // Configure Report Layout and UI
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Report Title
        sparkReporter.config().setReportName("Pet Store Users API"); // Report Name
        sparkReporter.config().setTheme(Theme.DARK); // Dark or Standard UI Theme
        
        // Initialise ExtentReports and attach configuration
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // Populate System/Environment metadata
        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        
        // Capture context configuration details if declared in testng.xml parameters
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os != null ? os : System.getProperty("os.name"));
        
        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser != null ? browser : "Chrome");
        
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
        // Create a test log item and assign its group category if applicable
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); 
        test.log(Status.PASS, result.getName() + " executed successfully.");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " failed.");
        test.log(Status.INFO, result.getThrowable().getMessage());
        
   }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " was skipped.");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {
        // Writes everything to the HTML file
        extent.flush(); //Mandatory without this the report will not be generated
        
//        // Optional: Automatically open the report in the default browser upon execution completion
//        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
//        File extentReport = new File(pathOfExtentReport);
//        
//        try {
//            Desktop.getDesktop().browse(extentReport.toURI());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}