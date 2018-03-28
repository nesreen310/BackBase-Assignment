package cdb.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ctm.Utils.TableElements;

public class POCompDBHome {
	
	//Web driver reference 
	WebDriver driver;
	
	//Constructor initiate page object elements
	public POCompDBHome(WebDriver driver) 
	{
		this.driver = driver;
	    PageFactory.initElements(driver, this);   
	}

	/**
	 * Your Detail Page element 
	 */
	
	@FindBy(xpath = "//a[contains(text(), 'Play sample application — Computer database')]")
	 private WebElement elemHeaderApplication;
	
	@FindBy(xpath = "//section[@id = 'main']/h1")
	 private WebElement elemNumberOfComp;
	
	@FindBy(xpath = "//table[@class = 'computers zebra-striped']")
	 private WebElement tblCompData;
	
	@FindBy(id ="add")
	 private WebElement btnAddComputer;
	
	@FindBy(id ="searchbox")
	 private WebElement txtFilter;
	
	@FindBy(id ="searchsubmit")
	 private WebElement btnSearchFilter;
	
	@FindBy(id ="pagination")
	 private WebElement elemPagination;
	
	@FindBy(xpath = "//div[@class = 'alert-message warning']")
	 private WebElement elemMessageHeader;
	
	@FindBy(xpath = "//div[@id = 'pagination']/ul/li[2]/a")
	 private WebElement elemPaginationText;
	
	@FindBy(xpath = "//a[contains(text(),'Next')]")
	 private WebElement elemPaginationNext;
	
	@FindBy(xpath = "//a[contains(text(),'Previous')]")
	 private WebElement elemPaginationPrev;
	
	/*
	 * Methods to action on web elements
	 */
		
	public boolean isHomePageDisplayed()
	 {
		 return elemHeaderApplication.isDisplayed();
	 }
	
	public void isHeaderNoOfCompDisplayed()
	{
		 elemNumberOfComp.isDisplayed();
	} 
	
	public String getTotalCompHeaderText()
	{
		return elemNumberOfComp.getText();
	} 
	
	public boolean isTblCompDataPresent()
	{
		return tblCompData.isDisplayed();
	} 

	public boolean isBtnAddComputerPresent()
	{
		return btnAddComputer.isDisplayed();
	} 
	
	public boolean isBtnFilterAndTxtFilterPresent()
	{
		return( btnSearchFilter.isDisplayed() && txtFilter.isDisplayed());
	}
	
	public boolean isElemPaginationPresent()
	{
		return elemPagination.isDisplayed() ;
	}
	
	public void clickAddNewComputerBtn()
	{
		 btnAddComputer.click(); ;
	}
	
	public void enterFilterVal(String strFil)
	{
		txtFilter.sendKeys(strFil);
	}
	
	public void clickFilterBtn()
	{
		btnSearchFilter.click();
		
	}
	
	public  TableElements getCompDataTable()
	{	
		
		return new TableElements(tblCompData);
	}
	
	public void clearFilterByName()
	{
		txtFilter.clear();
	}
	
	public String getMessageHeaderText()
	{
		return elemMessageHeader.getText();
	}
	
	public String getPaginationText()
	{
		return elemPaginationText.getText();
	}
	
	public void clickPaginationNext()
	{
		 elemPaginationNext.click();
	}
	
	public void clickPaginationPrev()
	{
		 elemPaginationNext.click();
	}
		
 }
