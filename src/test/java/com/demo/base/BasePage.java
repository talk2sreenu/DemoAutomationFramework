package com.demo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class BasePage extends TestBase{
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

	public void generateLog(String status, String messageToPrint, boolean screenshot) {		
		if(!screenshot) {
			if(status.toLowerCase().trim().equals("pass"))
				logger.get().log(Status.PASS, messageToPrint);
			else if(status.toLowerCase().trim().equals("fail"))
				logger.get().log(Status.FAIL, messageToPrint);
			else if(status.toLowerCase().trim().equals("warning"))
				logger.get().log(Status.WARNING, messageToPrint);
			else if(status.toLowerCase().trim().equals("info"))
				logger.get().log(Status.INFO, messageToPrint);
		}
		else {
			String base64encodescreenshot = ScreenShotUtil.captureScreenshot(); 
			try {
				if(status.toLowerCase().trim().equals("pass"))
					logger.get().log(Status.PASS, messageToPrint, MediaEntityBuilder.createScreenCaptureFromBase64String(base64encodescreenshot).build());
				else if(status.toLowerCase().trim().equals("fail"))
					logger.get().log(Status.FAIL, messageToPrint, MediaEntityBuilder.createScreenCaptureFromBase64String(base64encodescreenshot).build());
				else if(status.toLowerCase().trim().equals("warning"))
					logger.get().log(Status.WARNING, messageToPrint, MediaEntityBuilder.createScreenCaptureFromBase64String(base64encodescreenshot).build());
				else if(status.toLowerCase().trim().equals("info"))
					logger.get().log(Status.INFO, messageToPrint, MediaEntityBuilder.createScreenCaptureFromBase64String(base64encodescreenshot).build());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void switchWindows(String urlortitle) {
		try {
			System.out.println("Size of windows : "+ driver.getWindowHandles().size());
			for(String winHandle:driver.getWindowHandles()) {
				String winurl = driver.switchTo().window(winHandle).getCurrentUrl();
				String wintitle = driver.switchTo().window(winHandle).getTitle();
				
				if(winurl.contains(urlortitle)||wintitle.contains(urlortitle)) {
					System.out.println("URL after switching : "+ driver.getCurrentUrl());
					System.out.println("Title after switching : "+ driver.getTitle());
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void performClick(WebElement objDisplayed, String elementName) {
		if(objDisplayed.isDisplayed()) {
			generateLog("info", "Expected object : "+elementName+" displayed and selected", false);
			objDisplayed.click();
		}
		else {
			System.out.println("Object is not displayed");
			generateLog("warning", "Expected object : "+objDisplayed.toString()+" Not displayed and selected", true);
		}
	}
}
