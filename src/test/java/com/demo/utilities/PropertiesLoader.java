package com.demo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.demo.base.TestBase;

public class PropertiesLoader extends TestBase{
	
	static Properties locProp;
	public static Properties loadProperties() throws IOException {
		locProp = new Properties();
		String fileName = System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties";
		FileInputStream fis = new FileInputStream(fileName);
		locProp.load(fis);
		return locProp;
	}
	
}
