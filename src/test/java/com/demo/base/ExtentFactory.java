package com.demo.base;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentFactory extends TestBase{
	
	public static ThreadLocal<ExtentReports> extent = new ThreadLocal<>();
	private static ExtentHtmlReporter htmlReporter;
	public static String fileName = "test-results//AutomationReport"+dateTimeGenerate()+".html";
	
	public static ExtentReports generateReport() {
		
		htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setReportName("Automation Execution Report");
		htmlReporter.config().setDocumentTitle("Automation Execution Status Report");
		
		extent.set(new ExtentReports());
		extent.get().attachReporter(htmlReporter);
		extent.get().setSystemInfo("Host Name", "Srini Testing Tutorials");
		extent.get().setSystemInfo("Environment", "Automation Testing");
		extent.get().setSystemInfo("User Name", "Srini");
		
		return extent.get();
	}
	
	public static String dateTimeGenerate(){
	    Format formatter = new SimpleDateFormat("YYYYMMdd_HHmmssSSS");
	    Date date = new Date(System.currentTimeMillis());
	   return formatter.format(date);
	}
}
