package com.demo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage{
	//Common class to add object level actions & create methods
	public WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void loadPageObject(Object obj) {
		PageFactory.initElements(driver, obj);
	}
	
	public void localSleep(int number) {
		try {
			Thread.sleep(number);
		}catch(Exception e) {
			
		}
	}
}
