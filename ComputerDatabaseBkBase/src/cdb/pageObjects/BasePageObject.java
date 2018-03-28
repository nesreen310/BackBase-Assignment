package cdb.pageObjects;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ctm.Utils.PropertyReader;

public class BasePageObject {
	
	/*
	 * ****************************************************************************************************************************
	 * This is a Base Page object class which is responsible for initializing drivers
	 * This class will be extended by all Step Definitions
	 * ****************************************************************************************************************************
	 */
	
	// Create Web driver type variable
	protected static WebDriver driver;
	
	//ExtentReports report;
	ExtentTest logger;
	
	
	// Create Property Reader Config
	PropertyReader objProp = new PropertyReader();
	
	// path for IE driver
	static String driverPath = System.getProperty("user.dir")+"\\lib\\";
	
	ExtentReports report = new ExtentReports(System.getProperty("user.dir")+"\\Report\\Report.html");
	
	/*
	 * ****************************************************************************************************************************
	 * constructor to initialize drivers 
	 * ****************************************************************************************************************************
	 */

    public BasePageObject() {
        initialize();
    }
  
    /*
	 * ****************************************************************************************************************************
	 * This method calls createNewDriverInstance() method 
	 * ****************************************************************************************************************************
	 */
    public void initialize() {
        if (driver == null)
            createNewDriverInstance();
    }

    /*
	 * ****************************************************************************************************************************
	 * This method creates web driver instance of IE driver type
	 * ****************************************************************************************************************************
	 */
    private void createNewDriverInstance() 
    {
    	if(objProp.readProperty("browser").equalsIgnoreCase("IE"))
    	{
    	System.setProperty("webdriver.ie.driver", driverPath+"IEDriverServer.exe");
	    driver = new InternetExplorerDriver();
    	}
    	
    	if(objProp.readProperty("browser").equalsIgnoreCase("Chrome"))
    	{
    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
	    driver = new ChromeDriver();
	   
    	}
    	if(objProp.readProperty("browser").equalsIgnoreCase("FF"))
    	{
    	System.setProperty("webdriver.gecko.driver", driverPath + "/geckodriver");
	    driver = new FirefoxDriver();
	    driver.get("http://computer-database.herokuapp.com/computers");
    	}
    	
    }

    /*
	 * ****************************************************************************************************************************
	 * This method returns Web Driver 
	 * ****************************************************************************************************************************
	 */
    public WebDriver getDriver() {
        return driver;
    }
    
    /*
	 * ****************************************************************************************************************************
	 * Verify method destroys the web driver
	 * ****************************************************************************************************************************
	 */
    @AfterClass
    public void destroyDriver() {
        driver.quit();
        //release driver object
        driver = null;
    }
    
    /*
   	 * ****************************************************************************************************************************
   	 * This method launches application
   	 * ****************************************************************************************************************************
   	 */
    public void launchCTMApplication()
	{
    	// Read the AppURL from COnfig file
    	String applicationURL = objProp.readProperty("AppURL");
        driver.get(applicationURL);
        // Give a default timeout for every element
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
    
    /*
   	 * ****************************************************************************************************************************
   	 * This Method captures Screen Shot
   	 * Argument - Webdriver
   	 *            Name of screen shot
   	 * ****************************************************************************************************************************
   	 */
    
    public static String  captureScreenshot(WebDriver ldriver, String strName)
    {
	 String strDestination = System.getProperty("user.dir")+"\\Report\\"+strName+System.currentTimeMillis()+".png";
	 // Take screenshot and store as a file format
	 File src= ((TakesScreenshot)ldriver).getScreenshotAs(OutputType.FILE);
    	try 
    	{
    	 // now copy the  screenshot to desired location using copyFile method
    	 FileUtils.copyFile(src, new File(strDestination));
    	}
    	catch (IOException e)   	 
    	{
    		System.out.println(e.getMessage()); 
    	}
    	return strDestination;
    }
    
    /*
   	 * ****************************************************************************************************************************
   	 * This Method gets report object
   	 * ****************************************************************************************************************************
   	 */
    
    public ExtentReports getReports()
    {
    	return report = new ExtentReports(System.getProperty("user.dir")+"\\Report\\Report.html");
    }
    
    /*
   	 * ****************************************************************************************************************************
   	 * This Method initiates all loggers and launches application
   	 * ****************************************************************************************************************************
   	 */
    public ExtentTest initiateApplicationAndLoggers(String testName)
    {
    	// get report object
    	getReports();
		//Create a Logger object to start test reporting
		logger = report.startTest(testName);
		logger.log(LogStatus.INFO, "Brwoser Starting");
		launchCTMApplication();
		logger.log(LogStatus.PASS, "Application Launched Successfully");
		return logger;
    }
    
    public ExtentTest initExLogger()
    {
    	getReports();
		//Create a Logger object to start test reporting
		return logger = report.startTest("SC06_TC01_Pagination_10_Records_Displayed_Next_Previous");
    }
    
    /*
   	 * ****************************************************************************************************************************
   	 * This Method reads the Excel sheet Data and stores in hash map
   	 * Argument -Name of Data sheet
   	 * ****************************************************************************************************************************
   	 */
    @SuppressWarnings("resource")
	public HashMap<String, String> getData(String nmBook)
    {
    	XSSFWorkbook workbook = null;
    	HashMap<String, String> currentHash = null ;
		try {
			workbook = new XSSFWorkbook(new FileInputStream(System.getProperty("user.dir")+"\\data\\"+nmBook+".xlsx"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
    	List<HashMap<String, String>> mydata = new ArrayList<HashMap<String, String>>();
    	XSSFSheet sheet = workbook.getSheet("Sheet1");
    	Row HeaderRow = sheet.getRow(0);
    	for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) 
    	{
    	    Row currentRow = sheet.getRow(i);
    	    currentHash = new HashMap<String, String>();
    	    for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
    	        Cell currentCell = currentRow.getCell(j);
    	        switch (currentCell.getCellType()) {
    	            case Cell.CELL_TYPE_STRING:
    	                currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
    	                break;
    	            case Cell.CELL_TYPE_NUMERIC:
    	                currentHash.put(HeaderRow.getCell(j).getStringCellValue(), String.valueOf(currentCell.getNumericCellValue()));
    	                break;
    	        }
    	    }
    	    mydata.add(currentHash);
    	}
		return currentHash;
    } 

}
