package cdb.Tests;

import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cdb.Modules.ModuleCreateComputer;
import cdb.pageObjects.BasePageObject;

public class SC02_TC01_TC07_TC08_CreateNewComputer_Navigation_Cancel extends BasePageObject {

	ExtentReports report;
	ExtentTest logger;
	/*
	 * ****************************************************************************************************************************
	 * Test Script -  	SC02_TC01_Add_New_Computer_Page_Navigation
						SC02_TC07_Add new computer_Create
						SC02_TC08_Add new computer_Cancel
	 * ****************************************************************************************************************************
	 */
	@Test
	public void SC02_TC01_TC07_TC08_CreateNewComputer_Navigation_Cancel_test() 
	{

		//Initiate application and  Get Loggers and Reports
		logger = initiateApplicationAndLoggers("SC02_TC01_TC07_TC08_CreateNewComputer_Navigation");
		report = getReports();
		// create object
		ModuleCreateComputer cdbAddComp = new ModuleCreateComputer();
		HashMap<String, String> testData = getData("SC02_TC01_TC07_TC08_CreateNewComputer_Navigation");
		//Get all Data from data excel
		String strCompName = testData.get("Computer_Name");
		String strIDate = testData.get("Introduction_Date");
		String strDdate = testData.get("Discontinued_Date");
		String strCompanyName = testData.get("Company_Name") ;
		
		cdbAddComp.clickAddNewComputer();
		logger.log(LogStatus.PASS, "Add new computer clicked");
		cdbAddComp.navigateCreateComputerAndVerify();
		logger.log(LogStatus.PASS, "Navigated to Create Computer");
		cdbAddComp.addNewComputer(strCompName, strIDate, strDdate, strCompanyName);
		logger.log(LogStatus.PASS, "New Computer details added");
		cdbAddComp.verifyNewComputerAdded(strCompName);
		logger.log(LogStatus.PASS, "New Computer records reflected");
		cdbAddComp.navigateCreateComputerAndVerify();
		logger.log(LogStatus.PASS, "Navigated to new computer page sucessfully");
		cdbAddComp.addNewComputerCancel("Test027Comp", strIDate, strDdate, strCompanyName);
		logger.log(LogStatus.PASS, "Computer addition cancelled");
		cdbAddComp.verifyNewComputerNotAdded("Test027Comp");
		logger.log(LogStatus.PASS, "Verified Sucessfully");
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
				logger.log(LogStatus.FAIL, "SC02_TC01_TC07_TC08_CreateNewComputer_Navigation", image);
			}
		//Release Report Objects
		report.endTest(logger);
		report.flush();
	}
		
}
