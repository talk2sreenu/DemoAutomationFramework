package com.demo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.ITestContext;

import com.demo.base.TestBase;

public class PropertiesLoader extends TestBase{
	
	static Properties locProp;
	public static void loadProperties() throws IOException {
		locProp = new Properties();
		String fileName = System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties";
		FileInputStream fis = new FileInputStream(fileName);
		locProp.load(fis);
		loadLocalProperties(locProp);
		//return locProp;
	}
	
	public static void loadLocalProperties(Properties prop) {
		for(String key : prop.stringPropertyNames()) {
			if(key.toLowerCase().contains(".browser"))
				System.setProperty(key, prop.getProperty(key));
			if(key.toLowerCase().contains(".host"))
				System.setProperty(key, prop.getProperty(key));
			if(key.toLowerCase().contains(".mobile.platform"))
				System.setProperty(key, prop.getProperty(key));
		}
	}
	
	public static void loadContextProperties(ITestContext context) {
		
		if(context.getCurrentXmlTest().getParameter("browserName")!=null)
			System.setProperty("selenium.browser", context.getCurrentXmlTest().getParameter("browserName"));
		if(context.getCurrentXmlTest().getParameter("platform")!=null)
			System.setProperty("selenium.host", context.getCurrentXmlTest().getParameter("platform"));
		if(context.getCurrentXmlTest().getParameter("mobileplatForm")!=null)
			System.setProperty("selenium.mobile.platform", context.getCurrentXmlTest().getParameter("mobilePlatForm"));
	}
	
}
