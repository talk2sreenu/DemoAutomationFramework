package com.demo.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jetty.util.StringUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demo.utilities.PropertiesLoader;
import com.demo.utilities.WebDriverFactory;


public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports reporter;
	public static String methodName;
	public static String testClassName;
	
	public static XSSFWorkbook workbook;
	public static XSSFSheet workSheet;
	
	public static Logger log = Logger.getLogger("MyFramework");
	
	protected static boolean blnRetry = false;
	
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
	public static ThreadLocal<ExtentTest> logger = new ThreadLocal<>();
	
	private static HashMap<String, String> retryClasses = new HashMap<String,String>();
	
	
	@BeforeSuite(alwaysRun = true)
	public void setup() {
		
		if(System.getProperty("os.name").toLowerCase().contains("win")) {
			try {
				Runtime.getRuntime().exec("taskkill /IM IEDriverServer.exe /IM chromedriver.exe /IM geckodriver.exe /IM MicrosoftWebDriver.exe /f");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\log4j.properties");
		
		try {
			prop = PropertiesLoader.loadProperties();
			log.info("Config File Loaded !!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		if(blnRetry == false)
			reporter = ExtentFactory.generateReport();
	}
	
	/*
	 * @BeforeTest public void setupTest() throws IOException { driver =
	 * WebDriverFactory.createDriverInstance(); }
	 */
	
	@BeforeMethod(alwaysRun= true)
	public void startReport(ITestContext context, Method method) throws IOException {
		driver = WebDriverFactory.createDriverInstance();
		testClassName = getClass().getSimpleName();
		methodName = method.getName();
		parentTest.set(reporter.createTest(testClassName+"_"+methodName));
		ExtentTest child = parentTest.get().createNode(methodName);
		logger.set(child);
		logger.get().log(Status.INFO, "Test Execution initiated for : '"+methodName+"'");
		System.out.println("********* Execution initiated for : '"+methodName+"'");
	}
	
	@AfterMethod(alwaysRun = true)
	public void getResults(ITestResult result) {
		logger.get().log(Status.INFO, "Test Execution ended for : '"+methodName+"'");
		
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.get().log(Status.FAIL, "Test Case failed is "+result.getName());
			logger.get().fail(result.getThrowable());
			
			
			if(StringUtil.isNotBlank(prop.getProperty("retryFailedTests"))) {
				if(!retryClasses.containsKey(result.getTestClass().getName())) {
					retryClasses.put(result.getTestClass().getName(), result.getName()); 
				}
				else {
					retryClasses.put(result.getTestClass().getName(),
					retryClasses.get(result.getTestClass().getName())+";"+result.getName()); 
				} 
			}
			 
			
			logger.get().fail("Test Case Failed");
		}
		
		else if(result.getStatus() == ITestResult.SKIP) {
			logger.get().log(Status.SKIP, "Test Case Skipped is : '"+result.getName()+"'");
			logger.get().skip(result.getThrowable());
			logger.get().skip("Test Case Skipped");
		}
		
		else {
			logger.get().pass("Test Case Passed");
		}
		
		driver.close();
		System.out.println("******** Closing the Test : '"+result.getName()+"'");
		reporter.flush();
	}
	
	@AfterSuite
	public void teardown() {
		//Add Code to create Retry XML Here and execute
		//driver.close();
		
	}
	
	@DataProvider(name="testData")
	public Object[][] retrieveData(Method m) throws IOException{
		String fileLocation = System.getProperty("user.dir")+"//src//test//resources//ExcelData//TestData.xlsx";
		HashMap<String, String> dataMap;
		Object[][] dataObject;
		FileInputStream fis = new FileInputStream(fileLocation);
		
		workbook = new XSSFWorkbook(fis);

		workSheet = workbook.getSheet(m.getName());
		
		int rowCnt = workSheet.getLastRowNum();
		dataObject = new Object[rowCnt][1];
		
		for(int i=1;i<=rowCnt;i++) {
			dataMap = new HashMap<String, String>();
			int colCnt = workSheet.getRow(i).getLastCellNum();
			for(int j=0;j<colCnt;j++) {
				String header = workSheet.getRow(0).getCell(j).getStringCellValue();
				String val = workSheet.getRow(i).getCell(j).getStringCellValue();
				dataMap.put(header, val);
			}
			dataObject[i-1][0] = dataMap;
		}
		
		return dataObject;
	}

}
