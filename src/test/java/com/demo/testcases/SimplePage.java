package com.demo.testcases;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.demo.base.BasePage;
import com.demo.base.TestBase;

public class SimplePage extends BasePage{
	
	public SimplePage(WebDriver driver) {
		super(driver);
		loadPageObject(this);
		// TODO Auto-generated constructor stub
	}

	public void verifyBrowser(Map<String, String> testData) {
		driver.get(testData.get("Application Name"));
		System.out.println("UserName : "+testData.get("UserName"));
		String pageVal = driver.getCurrentUrl();
		System.out.println(pageVal);
	}

	public void lauchBrowser() {
		driver.get("http://www.google.com");
		
	}
}
