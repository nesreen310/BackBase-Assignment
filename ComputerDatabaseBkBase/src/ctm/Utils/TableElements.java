package ctm.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TableElements {
	
	 /*
	 * ****************************************************************************************************************************
	 * This method loads property file
	 * Arguments: NA
	 * ****************************************************************************************************************************
	 */	
	static final Logger logger = Logger.getLogger(TableElements.class);

	private WebElement table;

	public TableElements(WebElement tableElement)
	{
		this.table = tableElement;
	}
	
	/*
	 * ****************************************************************************************************************************
	 * This method refreshes the table element object
	 * Arguments: tableElement webelement
	 * ****************************************************************************************************************************
	 */		
	public void refreshTable(WebElement tableElement)
	{
		this.table = tableElement;
	}

	/**
	 * Returns no. of rows in the table
	 *
	 * @return int
	 */
	public int getRowCount()
	{
		int rowCount = table.findElements(By.xpath(".//tbody/tr")).size();
		return rowCount;
	}

	/**
	 * Returns no. of columns in the table
	 *
	 * @return int
	 */
	public int getColumnCount()
	{
		int columnCount = table.findElements(By.xpath(".//thead/tr/*")).size();
		logger.debug("getColumnCount() :: columnCount = " + columnCount);
		return columnCount;
	}
	/*
	 * ****************************************************************************************************************************
	 * This method returns column number whose name is known
	 * Arguments: Column Name
	 * ****************************************************************************************************************************
	 */	
	public int getColumnNumberByName(String columnName)
	{
		logger.debug("getColumnNumberByName() :: columnName = " + columnName);
		List<WebElement> columnHeadersList = getColumnHeaderElementsList();
		int columnNumber = 0;
		for (int i = 0; i < columnHeadersList.size(); i++)
		{
			String columnNameFromTable = columnHeadersList.get(i).getText().replaceAll("\\n", " ").replaceAll("\\?", "").trim();
			logger.debug("getColumnNumberByName() :: '" + columnNameFromTable + "' == '" + columnName + "'");
			if (columnNameFromTable.equalsIgnoreCase(columnName))
			{
				columnNumber = i + 1;
				break;
			}
		}
		logger.debug("getColumnNumberByName() :: columnNumber of column '" + columnName + "' = " + columnNumber);
		return columnNumber;
	}

	/*
	 * ****************************************************************************************************************************
	 * This method returns the value of particular row and columns
	 * Arguments: Row Number and Column Name
	 * ****************************************************************************************************************************
	 */	
	public WebElement getTableValue(int rowNumber, String columnName)
	{
		logger.debug("getTableValue() :: rowNumber = " + rowNumber + ", columnName = " + columnName);
		int columnNumber = getColumnNumberByName(columnName);
		return getTableValue(rowNumber, columnNumber);
	}

	/*
	 * ****************************************************************************************************************************
	 * This method returns the value of a hyperlink in table
	 * Arguments: Row Number and Column Name
	 * ****************************************************************************************************************************
	 */
	public WebElement getTableValueLink(int rowNumber, String columnName)
	{
		int columnNumber = getColumnNumberByName(columnName);
		return getTableValueLink(rowNumber, columnNumber);
	}

	/*
	 * ****************************************************************************************************************************
	 * This method returns the value of particular row and columns
	 * Arguments: Row Number and Column Number
	 * ****************************************************************************************************************************
	 */	
	public WebElement getTableValue(int rowNumber, int columnNumber)
	{
		logger.debug("getTableValue() :: rowNumber = " + rowNumber + ", columnNumber = " + columnNumber);
		List<WebElement> tableDataList = new ArrayList<WebElement>();
		try
		{
			tableDataList.addAll(table.findElements(By.xpath(".//tbody/tr[" + rowNumber + "]/td"))); //replaced with td to count rows only with td and not the hidden ones


		}
		catch (Exception e)
		{
			logger.warn("getTableValue() :: Exception while fetching [tr/*] elements..", e);
		}
		return tableDataList.get(columnNumber - 1);
		//return tableDataList.get(columnNumber);
	}





	public WebElement getTableValueFooter(int rowNumber, int columnNumber)
	{
		logger.debug("getTableValue() :: rowNumber = " + rowNumber + ", columnNumber = " + columnNumber);
		List<WebElement> tableDataList = new ArrayList<WebElement>();
		try
		{
			tableDataList.addAll(table.findElements(By.xpath(".//tfoot/tr[" + rowNumber + "]/*")));
		}
		catch (Exception e)
		{
			logger.warn("getTableValue() :: Exception while fetching [tr/*] elements..", e);
		}
		return tableDataList.get(columnNumber - 1);
	}

	public WebElement getTableValueLink(int rowNumber, int columnNumber)
	{
		logger.debug("getTableValueLink() :: rowNumber = " + rowNumber + ", columnNumber = " + columnNumber);
		try
		{
			return getTableValue(rowNumber, columnNumber).findElement(By.tagName("a"));
		}
		catch (Exception e)
		{
			logger.debug("getTableValueLink() :: <a> not found.", e);
			return getTableValue(rowNumber, columnNumber);
		}
	}

	public WebElement getTableValueInput(int rowNumber, int columnNumber)
	{
		logger.debug("getTableValueInput() :: rowNumber = " + rowNumber + ", columnNumber = " + columnNumber);
		try
		{
			return getTableValue(rowNumber, columnNumber).findElement(By.tagName("input"));
		}
		catch (Exception e)
		{
			logger.debug("getTableValueInput() :: <input> not found.", e);
			return getTableValue(rowNumber, columnNumber);
		}
	}

	public List<WebElement> getColumnElementsList(String columnName)
	{
		int columnNumber = getColumnNumberByName(columnName);
		return table.findElements(By.xpath(".//tbody/tr/td[" + columnNumber + "]"));
	}

	public List<WebElement> getColumnElementsList(int iColNo)
	{
		return table.findElements(By.xpath(".//tbody/tr/td[" + iColNo + "]"));
	}

	public List<String> getColumnValuesList(String columnName)
	{
		List<WebElement> columnElements = getColumnElementsList(columnName);
		List<String> columnValuesList = new ArrayList<String>();
		String strWebElem = "";
		for (Iterator<WebElement> iterator = columnElements.iterator(); iterator.hasNext();)
		{
			WebElement webElement = iterator.next();
			strWebElem = webElement.getText().replaceAll("\\n", " ").trim();
			strWebElem = strWebElem.replaceAll(",", "").trim();
			columnValuesList.add(strWebElem);
		}
		logger.debug("getColumnElementsList() :: Values of column '" + columnName + "' = " + columnValuesList);
		return columnValuesList;
	}

	/**
	 * get the column Value List on column Number
	 */
	public List<String> getColumnValuesList(int iColumnNo)
	{
		List<WebElement> columnElements = getColumnElementsList(iColumnNo);
		List<String> columnValuesList = new ArrayList<String>();
		String strWebElem = "";
		for (Iterator<WebElement> iterator = columnElements.iterator(); iterator.hasNext();)
		{
			WebElement webElement = iterator.next();
			strWebElem = webElement.getText().replaceAll("\\n", " ").trim();
			strWebElem = strWebElem.replaceAll(",", "").trim();
			columnValuesList.add(strWebElem);
		}
		logger.debug("getColumnElementsList() :: Values of column '" + iColumnNo + "' = " + columnValuesList);
		return columnValuesList;
	}

	
	public List<WebElement> getColumnHeaderElementsList()
	{
		return table.findElements(By.xpath(".//thead/tr/th"));	//to exclude the hidden headers
	}
}
