package com.demo.testcases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MobileTest {
	
	//static RemoteWebDriver driver;
	static WebDriver driver;
	
	public static void main(String[] args) {
		try {
			openCalculator();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	private static void openCalculator() throws MalformedURLException {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setCapability("deviceName", "XT1068");
		cap.setCapability("udid", "ZX1D63BZ7B");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "6.0");
		cap.setCapability("automationName", "UIAutomator2");
		
		cap.setCapability("appPackage", "com.adobe.reader");
		cap.setCapability("appActivity", "com.adobe.reader.AdobeReader");
		
		URL url = new URL("http://localhost:4723/wd/hub");
		driver = new AppiumDriver(url, cap);
		
		System.out.println("Adobe Reader application launched Successfully....!!!");
		localSleep(15000);
		
		WebElement welCometext = driver.findElement(By.id("com.adobe.reader:id/msg_title_id"));
		WebElement secText = driver.findElement(By.id("com.adobe.reader:id/msg_detail_id"));
		WebElement signUpLink = driver.findElement(By.id("com.adobe.reader:id/signin_or_signup"));
		
		System.out.println(welCometext.getText());
		System.out.println(secText.getText());
		
		printContext();
		
		signUpLink.click();
		
		localSleep(15000);
		printContext();

		WebElement SignInText = driver.findElement(By.xpath("//android.view.View[@text='Sign in']"));
		WebElement btnContinue = driver.findElement(By.xpath("//android.widget.Button[@text='Continue']"));
		
		System.out.println(SignInText.getText());
		
		btnContinue.click();
		
		localSleep(15000);
		WebElement errMsg = driver.findElement(By.xpath("//android.view.View[@text='Please enter an email address.']"));
		
		System.out.println("Error Message is :"+errMsg.getText());
	}
	
	private static void printContext() {
		String context = ((AppiumDriver) driver).getContext();
		System.out.println(context);
	}

	public static void localSleep(int timeVal) {
		try {
			Thread.sleep(timeVal);
		}catch(Exception e) {
			
		}
		
	}

}
