package cdb.Tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cdb.Modules.ModuleCreateComputer;
import cdb.Modules.ModuleHomePage;
import cdb.pageObjects.BasePageObject;

public class SC05_TC01_Delete_Computer extends BasePageObject {
	
	ExtentReports report;
	ExtentTest logger;
	/*
	 * ****************************************************************************************************************************
	 * Test Script -  	SC05_TC01_Delete_Computer
	 * ****************************************************************************************************************************
	 */
	@Test
	public void SC05_TC01_Delete_Computer_Test() 
	{
		//Initiate application and  Get Loggers and Reports
		logger = initiateApplicationAndLoggers("SC05_TC01_Delete_Computer");
		report = getReports();
		// create object of Home Page
		ModuleHomePage cdbHomePg = new ModuleHomePage();	
		// create object of Create Computer
		ModuleCreateComputer cdbAddComp = new ModuleCreateComputer();
		
		String strCompClicked = cdbHomePg.clickFirstComputer();
		logger.log(LogStatus.PASS, "First Computer Clicked Sucessfully");
		cdbAddComp.navigateCreateComputerAndVerify();
		logger.log(LogStatus.PASS, "User Navigated to Create new computer Page");
		cdbAddComp.deleteComputer();
		logger.log(LogStatus.PASS, "Computer Deleted Sucessfully");
		cdbHomePg.verifyComputerDeletedSucessfully(strCompClicked);
		logger.log(LogStatus.PASS, "Deleted Computer Not Visible in Table");
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
				logger.log(LogStatus.FAIL, "SC05_TC01_Delete_Computer", image);
			}
		//Release Report Objects
		report.endTest(logger);
		report.flush();
	}
}
