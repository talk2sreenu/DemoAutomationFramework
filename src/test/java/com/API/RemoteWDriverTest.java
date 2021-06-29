package com.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteWDriverTest {
	
	public static void testApplication(String browserName) throws MalformedURLException {
		System.out.println("Running Script for Browser : "+ browserName);
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(browserName);
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), dc);
		
		driver.get("http://www.google.com");
		
		driver.manage().window().maximize();
		//driver.navigate().forward();
		//driver.navigate().back();
		//driver.navigate().refresh();
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		System.out.println(driver.getWindowHandle());
		driver.close();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//String bName = br.readLine();
		String[] bName = {"chrome", "firefox"};
		for(String brow:bName)
			testApplication(brow);
	}
}
