package cdb.Tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cdb.Modules.ModuleHomePage;
import cdb.pageObjects.BasePageObject;

public class SC06_TC02_Pagination_No_Of_Records_match extends BasePageObject 
{
	ExtentReports report;
	ExtentTest logger;
	/*
	 * ****************************************************************************************************************************
	 * Test Script -  SC06_TC02_Pagination_No_Of_Records_match
	 * ****************************************************************************************************************************
	 */
	@Test
	public void SC06_TC02_Pagination_No_Of_Records_match_Test() 
	{
		//Initiate application and  Get Loggers and Reports
		logger = initiateApplicationAndLoggers("SC06_TC02_Pagination_No_Of_Records_match");
		report = getReports();
		
		ModuleHomePage cdbHomePg = new ModuleHomePage();
		cdbHomePg.tallyTotalRecords(logger);
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
					logger.log(LogStatus.FAIL, "SC06_TC02_Pagination_No_Of_Records_match", image);
				}
			//Release Report Objects
			report.endTest(logger);
			report.flush();
		}

}
