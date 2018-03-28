package cdb.Modules;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import cdb.pageObjects.BasePageObject;
import cdb.pageObjects.POCompDBHome;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ctm.Utils.TableElements;

public class ModuleHomePage extends BasePageObject {
	
	//private static Logger Log = Logger.getLogger(ModuleHomePage.class)
	
	// object creation of Your Details page
	POCompDBHome homePgObj = new POCompDBHome(driver);
	
	TableElements compDataTable = homePgObj.getCompDataTable();
	
	/*
	 * ****************************************************************************************************************************
	 * Verify that user is on details Page 
	 * Arguments: logger object
	 * ****************************************************************************************************************************
	 */
	
	public void verifyHomePageUI(ExtentTest logger) 
	{
		Assert.assertTrue(homePgObj.isHomePageDisplayed(), "Home Page is Displayed");
		logger.log(LogStatus.PASS, "Home Page is displayed");
		Assert.assertTrue(homePgObj.getTotalCompHeaderText().contains("computers found"),"Computers Found Label Is Displayed");	
		logger.log(LogStatus.PASS, "Header is displayed");
		Assert.assertTrue(homePgObj.isBtnAddComputerPresent(), "Add Computer Button Is Displayed");
		logger.log(LogStatus.PASS, "Buttons are displayed");
		Assert.assertTrue(homePgObj.isBtnFilterAndTxtFilterPresent(), "FIlter Elements are Present");
		logger.log(LogStatus.PASS, "Filter elements are displayed");
		Assert.assertTrue(homePgObj.isTblCompDataPresent(), "Verify If Table Is Present");
		logger.log(LogStatus.PASS, "Table is displayed");
		Assert.assertTrue(homePgObj.isElemPaginationPresent(), "Verify If Pagination ELements are Present");
		logger.log(LogStatus.PASS, "Pagination section is displayed");
		
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method selects the payment type and frequency
	 * Arguments: 
	 * args1 - Payment Type
	 * args2 - Payment Frequency
	 * ****************************************************************************************************************************
	 */
	
	public void searchFilter(String strComNm)
	{
		homePgObj.enterFilterVal(strComNm);
		homePgObj.clickFilterBtn();
		// Sleep used as the filter was sometimes showing erratic behavior 
		try {
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method selects the fist computer from the table
	 * ****************************************************************************************************************************
	 */
	public String clickFirstComputer()
	{	//get the first element
		WebElement elemCompNameFirst = compDataTable.getTableValueLink(1,"Computer Name");
		String strfirstName = elemCompNameFirst.getText();
		elemCompNameFirst.click();
		return strfirstName;
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method verifies the header text / compares with a expected header text 
	 * Arguments: 
	 * strMessage - Expected header text
	 * ****************************************************************************************************************************
	 */
	public void verifyHeadermessageText(String strMessage)
	{
		String strHeaderText = homePgObj.getMessageHeaderText();
		Assert.assertTrue(strHeaderText.contains(strMessage), "Verify the Header message");
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method verifies the header text / compares with a expected header text 
	 * Arguments: 
	 * strComNm - Computer Name
	 * strIdt - Expected Introduced Date
	 * strDdt - Expected - Discontinued date
	 * strCname - expected Company Name
	 * ****************************************************************************************************************************
	 */
	public void verifyComputerDetails(String strComNm, String strIdt, String strDdt, String strCname)
	{
		verifyHeadermessageText(strComNm+" "+"has been updated");
		//Search for a computer Name
		searchFilter(strComNm);
		// Verify the COmputer details
		List<String> lstCompName = compDataTable.getColumnValuesList("Computer Name");
		List<String> lstIntroduced = compDataTable.getColumnValuesList("Introduced");
		List<String> lstDiscontinued = compDataTable.getColumnValuesList("Discontinued");
		List<String> lstCompanyName = compDataTable.getColumnValuesList("Company Name");
		Assert.assertTrue(lstCompName.contains(strComNm), "Verify Computer Name is updated");
		Assert.assertTrue(lstIntroduced.contains(strIdt), "Verify Introduced Date is updated");
		Assert.assertTrue(lstDiscontinued.contains(strDdt), "Verify Introduced Date is updated");
		Assert.assertTrue(lstCompanyName.contains(strCname), "Verify Company Name is updated");
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method verifies the computer is deleted and no more visible in table
	 * Arguments: 
	 * strComNm - Computer Name
	 * ****************************************************************************************************************************
	 */
	public void verifyComputerDeletedSucessfully(String strComNm)
	{
		verifyHeadermessageText("Computer has been deleted");
		searchFilter(strComNm);
		// get all values of computer name in list
		List<String> lstCompName = compDataTable.getColumnValuesList("Computer Name");
		Assert.assertFalse(lstCompName.contains(strComNm), "Verify Computer does not exist");
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method verifies that the every page displays 10 records
	 * Argument logger object
	 * ****************************************************************************************************************************
	 */
	public void verifyPageRecords(ExtentTest logger)
	{
		// take the row count
		int irowCnt = compDataTable.getRowCount();
		Assert.assertEquals(irowCnt, 10, "Verify that each page has 10 records");
		logger.log(LogStatus.PASS, "each page has 10 records");
		// Go to next page
		homePgObj.clickPaginationNext();
		logger.log(LogStatus.INFO, "Navigated to Next Page");
		// Get the row count
		int frowCnt = compDataTable.getRowCount();
		Assert.assertEquals(frowCnt, 10, "Verify that next page has 10 records");
		logger.log(LogStatus.PASS, "next page has 10 records");
		// Click on previous page
		homePgObj.clickPaginationPrev();
		logger.log(LogStatus.INFO, "Navigated to Previous Page");
		// Get row count
		int prevrowCnt = compDataTable.getRowCount();
		Assert.assertEquals(prevrowCnt, 10, "Verify that previous page has 10 records");
		logger.log(LogStatus.PASS, "previous page has 10 record");
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method verifies that number of computers mentioned in header and pagination are equal
	 * Argument logger object
	 * ****************************************************************************************************************************
	 */
	public void tallyTotalRecords(ExtentTest logger)
	{
		// take pagination text
		String cntTotal = homePgObj.getPaginationText();
		// take header text
		String cntHeaderTotal = homePgObj.getTotalCompHeaderText();
		
		String[] compCountPg = cntTotal.split(" ");
		String[] compCountHeadr = cntHeaderTotal.split(" ");
		
		Assert.assertEquals(compCountPg[5],compCountHeadr[0], "Verify that the count in header is equal to count in pagination)");
		logger.log(LogStatus.PASS, "No of computers in pagination and header are equal");
	}
	
}
