package cdb.Tests;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cdb.Modules.ModuleCreateComputer;
import cdb.Modules.ModuleHomePage;
import cdb.pageObjects.BasePageObject;

public class SC04_TC01_Edit_Computer_Details extends BasePageObject {
	ExtentReports report;
	ExtentTest logger;
	/*
	 * ****************************************************************************************************************************
	 * Test Script -  	SC04_TC01_Edit_Computer_Details
	 * ****************************************************************************************************************************
	 */
	@Test
	public void SC04_TC01_Edit_Computer_Details_Test() 
	{
		//Time stamp for unique computer names
		String timeStamp = new SimpleDateFormat("yyyy.HH.mm.ss").format(new Date());
		//Initiate application and  Get Loggers and Reports
		logger = initiateApplicationAndLoggers("SC04_TC01_Edit_Computer_Details");
		report = getReports();
		
		HashMap<String, String> testData = getData("SC04_TC01_Edit_Computer_Details");
		//Get all Data from data excel
		String strIDate = testData.get("Introduction_Date");
		String strDdate = testData.get("Discontinued_Date");
		String strCompanyName = testData.get("Company_Name") ;
		
		// create object
		ModuleHomePage cdbHomePg = new ModuleHomePage();	
		ModuleCreateComputer cdbAddComp = new ModuleCreateComputer();
		logger.log(LogStatus.INFO, "CLicking First Computer and saving the name");
		String strCompClicked = cdbHomePg.clickFirstComputer();
		logger.log(LogStatus.PASS, "First Computer Clicked Sucessfully");
		// Append Name with a unique time stamp
		String updtdCompName = strCompClicked+timeStamp;
		cdbAddComp.navigateCreateComputerAndVerify();
		logger.log(LogStatus.PASS, "User is on Edit Computer Page");
		
		cdbAddComp.editComputer(updtdCompName,strIDate, strDdate, strCompanyName);
		logger.log(LogStatus.PASS, "Computer details editided sucessfully and new name is ="+updtdCompName);
		cdbHomePg.verifyComputerDetails(updtdCompName,strIDate, strDdate, strCompanyName);
		logger.log(LogStatus.PASS, "Computer details editided sucessfully verified");
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
					logger.log(LogStatus.FAIL, "SC04_TC01_Edit_Computer_Details", image);
				}
			//Release Report Objects
			report.endTest(logger);
			report.flush();
		}
}
