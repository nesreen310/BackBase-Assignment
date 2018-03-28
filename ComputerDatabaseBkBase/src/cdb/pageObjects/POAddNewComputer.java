package cdb.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class POAddNewComputer {
	
	//Web driver reference 
		WebDriver driver;
		
		//Constructor initiate page object elements
		public POAddNewComputer(WebDriver driver) 
		{
			this.driver = driver;
		    PageFactory.initElements(driver, this);   
		}
		
		/**
		 * Your Detail Page element 
		 */
		@FindBy(xpath = "//section[@id = 'main']/h1")
		 private WebElement elemAddComputer;
		@FindBy(id ="name")
		 private WebElement txtComputerName;
		@FindBy(id ="introduced")
		 private WebElement txtIntroduced;
		@FindBy(id ="discontinued")
		 private WebElement txtDiscontinued;
		@FindBy(xpath = "//select[@id = 'company']")
		 private WebElement lstCompany;
		@FindBy(xpath = "//input[@value = 'Create this computer']")
		 private WebElement btnCreateComputer;
		@FindBy(xpath = "//a[@text = 'Cancel']")
		 private WebElement btnCancel;
		@FindBy(xpath = "//input[@value = 'Save this computer']")
		 private WebElement btnSaveComputer;	
		@FindBy(xpath = "//input[@value = 'Delete this computer']")
		 private WebElement btnDeleteComputer;


		/*
		 * Methods to action on web elements
		 */
			
		public boolean isAddComputerPageDisplayed()
		 {
			 return elemAddComputer.isDisplayed();
		 }
		
		public void enterComputerName(String strComNm)
		{
			txtComputerName.sendKeys(strComNm);
		}
		
		public void enterIntroducedDt(String strIdt)
		{
			txtIntroduced.sendKeys(strIdt);
		}
		
		public void enterDiscontinuedDt(String strDdt)
		{
			txtDiscontinued.sendKeys(strDdt);
		}
		
		public void selCompanyName(String strCname)
		{
			lstCompany.click();
			Select sel = new Select(lstCompany);
			sel.selectByVisibleText(strCname);
		}
		
		public void clickCreateNewComp()
		{
			btnCreateComputer.click();
		}
		
		public void clickCancel()
		{
			btnCancel.click();
		}
		
		public void clickSaveComputer()
		{
			btnSaveComputer.click();
		}
		
		public void clearComputerName()
		{
			txtComputerName.clear();
		}
		
		public void clickDeleteComputer()
		{
			btnDeleteComputer.click();
		}
		
		
}
