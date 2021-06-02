package com.demo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage{
	//Common class to add object level actions & create methods
	protected WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 
	public void localSleep(int number) {
		try {
			Thread.sleep(number);
		}catch(Exception e) {
			
		}
	}
}
