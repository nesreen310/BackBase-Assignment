package cdb.Modules;

import java.util.List;
import org.testng.Assert;
import ctm.Utils.TableElements;
import cdb.pageObjects.BasePageObject;
import cdb.pageObjects.POCompDBHome;
import cdb.pageObjects.POAddNewComputer;

public class ModuleCreateComputer extends BasePageObject {
	
	// object creation of Your Details page
	POCompDBHome homePgObj = new POCompDBHome(driver);
	POAddNewComputer createCompPgObj = new POAddNewComputer(driver);
	
	
	TableElements compDataTable = homePgObj.getCompDataTable();
	
	/*
	 * ****************************************************************************************************************************
	 * Verify that user is on details Page 
	 * ****************************************************************************************************************************
	 */
	public void clickAddNewComputer()
	{
		homePgObj.clickAddNewComputerBtn();
	}
	 
	/*
	 * ****************************************************************************************************************************
	 * Verify that user navigate to create computer
	 * ****************************************************************************************************************************
	 */
	public void navigateCreateComputerAndVerify()
	{	
		
		Assert.assertTrue(createCompPgObj.isAddComputerPageDisplayed(), "Verify That Add Computer page is displayed");
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method fills the new computer form
	 * Arguments :
	 * String strComNm = Computer Name 
	 * String strIdt  = Induction Date
	 * String strDdt = Discontinued Date
	 * String strCname = Company Name
	 * ****************************************************************************************************************************
	 */
	public void addNewComputerFormFill(String strComNm, String strIdt, String strDdt, String strCname)
	{
		createCompPgObj.clearComputerName();
		createCompPgObj.enterComputerName(strComNm);
		createCompPgObj.enterIntroducedDt(strIdt);
		createCompPgObj.enterDiscontinuedDt(strDdt);
		createCompPgObj.selCompanyName(strCname);
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method fills the new computer form and create computer
	 * Arguments :
	 * String strComNm = Computer Name 
	 * String strIdt  = Induction Date
	 * String strDdt = Discontinued Date
	 * String strCname = Company Name
	 * ****************************************************************************************************************************
	 */
	public void addNewComputer(String strComNm, String strIdt, String strDdt, String strCname)
	{
		addNewComputerFormFill(strComNm,strIdt,strDdt,strCname);
		
		createCompPgObj.clickCreateNewComp();
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method verifies that the computer is added successfully
	 * Arguments :
	 * String strComNm = Computer Name 
	 * ****************************************************************************************************************************
	 */
	public void verifyNewComputerAdded(String strComNm)
	
	{
		searchFilter(strComNm);
		// get column value as a list
		List<String> compDataLst = compDataTable.getColumnValuesList("Computer name");
		Assert.assertTrue (compDataLst.contains(strComNm));
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method searches the computer database
	 * Arguments :
	 * String strComNm = Computer Name 
	 * ****************************************************************************************************************************
	 */
	public void searchFilter(String strComNm)
	{
		homePgObj.enterFilterVal(strComNm);
		homePgObj.clickFilterBtn();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method fills the new computer form and cancels it
	 * Arguments :
	 * String strComNm = Computer Name 
	 * String strIdt  = Induction Date
	 * String strDdt = Discontinued Date
	 * String strCname = Company Name
	 * ****************************************************************************************************************************
	 */
	public void addNewComputerCancel(String strComNm, String strIdt, String strDdt, String strCname)
	{
		addNewComputerFormFill(strComNm,strIdt,strDdt,strCname);
		
		createCompPgObj.clickCancel();
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method verifies that after canceling add computer
	 * Arguments :
	 * String strComNm = Computer Name 
	 * ****************************************************************************************************************************
	 */
	public void verifyNewComputerNotAdded(String strComNm)
		
	{	//call search filter
		searchFilter(strComNm);
		
		List<String> compDataLst = compDataTable.getColumnValuesList("Computer name");
		Assert.assertFalse (compDataLst.contains(strComNm));
     }
	
	/*
	 * ****************************************************************************************************************************
	 * This method verifies the alphabetic characters accepted by filter
	 * Arguments :
	 * String strComNm = Computer Name 
	 * ****************************************************************************************************************************
	 */
	public void verifyFilterOnNameByAlphabet(String strName)
	{
		homePgObj.clearFilterByName();
		searchFilter(strName);
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method edits the computer added
	 * Arguments :
	 * String strComNm = Computer Name 
	 * String strIdt  = Induction Date
	 * String strDdt = Discontinued Date
	 * String strCname = Company Name
	 * ****************************************************************************************************************************
	 */
	public void editComputer(String strComNm, String strIdt, String strDdt, String strCname)
	{
		addNewComputerFormFill(strComNm,strIdt,strDdt,strCname);
		createCompPgObj.clickSaveComputer();
		
	}
	/*
	 * ****************************************************************************************************************************
	 * This method deletes the computer
	 * ****************************************************************************************************************************
	 */
	public void deleteComputer()
	{
		createCompPgObj.clickDeleteComputer();
	}

}
