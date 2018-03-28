package cdb.Tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cdb.Modules.ModuleCreateComputer;
import cdb.pageObjects.BasePageObject;

public class SC03_TC01_TC02_Filter_Computer_Name_Alphabets_Numeric extends BasePageObject 
{
	ExtentReports report;
	ExtentTest logger;
	/*
	 * ****************************************************************************************************************************
	 * Test Script -  	SC03_TC01_TC02_Filter_Computer_Name_Alphabets_Numeric
	 * ****************************************************************************************************************************
	 */
	@Test
	public void SC03_TC01_TC02_Filter_Computer_Name_Alphabets_Numeric_Test() 
	{
		//Initiate application and  Get Loggers and Reports
		logger = initiateApplicationAndLoggers("SC03_TC01_TC02_Filter_Computer_Name_Alphabets_Numeric");
		report = getReports();
		// Create Home Page Object
		ModuleCreateComputer cdbAddComp = new ModuleCreateComputer();
		
		cdbAddComp.verifyFilterOnNameByAlphabet("Test");
		logger.log(LogStatus.PASS, "Alphabetic search is sucessfull");
		cdbAddComp.verifyFilterOnNameByAlphabet("09");
		logger.log(LogStatus.PASS, "Numeric search is sucessfull");
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
				logger.log(LogStatus.FAIL, "SC03_TC01_TC02_Filter_Computer_Name_Alphabets_Numeric", image);
			}
		//Release Report Objects
		report.endTest(logger);
		report.flush();
	}
}