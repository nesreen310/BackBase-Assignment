package cdb.Tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cdb.Modules.ModuleHomePage;
import cdb.pageObjects.BasePageObject;

// Test Class
public class SC01_TC01_Computer_Database_UI extends BasePageObject  
{	
	ExtentReports report;
	ExtentTest logger;
	
	/* Test Case
	 * SC01_TC01_Computer_Database_UI
	 */
	@Test
	public void SC01_TC01_Computer_Database_UI_Test() 
	{	
		report = new ExtentReports(System.getProperty("user.dir")+"\\Report\\Report.html");
		//Create a Logger object to start test reporting
		logger = report.startTest("SC01_TC01_Computer_Database_UI_Test");
		logger.log(LogStatus.INFO, "Brwoser Starting"); 
		//Call launch application method
		launchCTMApplication();
		logger.log(LogStatus.PASS, "Application Launched Successfully");
		// Create Home Page Module Object
		ModuleHomePage cdbHomePg = new ModuleHomePage();
		// Verify Various Home page elements
		cdbHomePg.verifyHomePageUI(logger);
		// Conclude	 Reporting
		report.endTest(logger);
		report.flush();
	}
	// Report in case of failure
	@AfterMethod
	public void tearDown(ITestResult result)
	{
	 // Check If test fails	
	if(result.getStatus()==ITestResult.FAILURE)
		{
			//Capture screen and attach to test
			String screenshot_path = captureScreenshot(driver, result.getName());
			String image= logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.FAIL, "SC01_TC01_Computer_Database_UI_Test", image);
		}
	//Release Report Objects
	report.endTest(logger);
	report.flush();
	}
}
