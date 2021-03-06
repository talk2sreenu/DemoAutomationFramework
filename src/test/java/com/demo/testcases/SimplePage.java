package com.demo.testcases;
import java.awt.event.KeyEvent;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.demo.base.BasePage;

public class SimplePage extends BasePage{
	
	public SimplePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="(//*[@name='btnK'])[2]")
	public WebElement btnSubmit;
	
	@FindBy(name="q")
	public WebElement txtSearch;
	
	@FindBy(linkText="Store")
	public WebElement lnkStore;

	public void verifyBrowser(Map<String, String> testData) {
		driver.get(testData.get("Application Name"));
		System.out.println("UserName : "+testData.get("UserName"));
		String pageVal = driver.getCurrentUrl();
		System.out.println(pageVal);
	}

	public void lauchBrowser() {
		driver.get("http://www.google.com");
		localSleep(1000);
		try {
			if(btnSubmit.isEnabled()) {
				System.out.println("Object is displayed as expected");
				txtSearch.sendKeys("StudentFunda");
				txtSearch.sendKeys(Keys.TAB);
				performClick(btnSubmit, "Submit");
				localSleep(2000);
				driver.navigate().back();
				performClick(lnkStore, "Store Link");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		generateLog("info", "Another Test", true);
		generateLog("info", "Generic False Test", false);
	}
	
}
