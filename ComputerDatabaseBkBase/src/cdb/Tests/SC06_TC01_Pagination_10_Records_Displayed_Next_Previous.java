package cdb.Tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cdb.Modules.ModuleHomePage;
import cdb.pageObjects.BasePageObject;

public class SC06_TC01_Pagination_10_Records_Displayed_Next_Previous extends BasePageObject {
	
	ExtentReports report;
	ExtentTest logger;
	/*
	 * ****************************************************************************************************************************
	 * Test Script -  SC06_TC01_Pagination_10_Records_Displayed_Next_Previous
	 * ****************************************************************************************************************************
	 */
	@Test
	public void SC06_TC01_Pagination_10_Records_Displayed_Next_Previous_Test() 
	{ 
		//Initiate application and  Get Loggers and Reports
		logger = initiateApplicationAndLoggers("SC06_TC01_Pagination_10_Records_Displayed_Next_Previous");
		report = getReports();
		// Create Module object
		ModuleHomePage cdbHomePg = new ModuleHomePage();
		// Verify each page has 10 records
		cdbHomePg.verifyPageRecords(logger);
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
				logger.log(LogStatus.FAIL, "SC06_TC01_Pagination_10_Records_Displayed_Next_Previous", image);
			}
		//Release Report Objects
		report.endTest(logger);
		report.flush();
	}

}
