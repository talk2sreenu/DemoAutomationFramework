package com.demo.utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.demo.base.TestBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class WebDriverFactory extends TestBase {

	public static WebDriver driver;
	public static String browserName;
	public static String platform;
	public static String mobilePlatForm;

	public static WebDriver createDriverInstance() throws IOException {

		browserName = prop.getProperty("selenium.browser");
		platform = prop.getProperty("selenium.host");
		mobilePlatForm = prop.getProperty("selenium.mobile.platform");
		
		switch(platform.toUpperCase()) {
			case "LOCAL":
				driver = createBrowserSpecificDriver(browserName);
				break;
				
			case "MOBILE":
				driver = createDeviceSpecificDriver(mobilePlatForm);
				break;
		}
		return driver;
	}
	
	private static WebDriver createDeviceSpecificDriver(String mobilePlatForm2) {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		URL url = null;
		try {
			url = new URL("http://localhost:4723/wd/hub");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		switch(mobilePlatForm2.toUpperCase()) {
			case "ANDROID":
				cap.setCapability("deviceName", "XT1068");
				cap.setCapability("udid", "ZX1D63BZ7B");
				cap.setCapability("platformVersion", "6.0");
				cap.setCapability("platformName", "Android");
				cap.setCapability("automationName", "UIAutomator2");
				cap.setCapability("appPackage", "com.adobe.reader");
				cap.setCapability("appActivity", "com.adobe.reader.AdobeReader");		
				
				driver = new AndroidDriver(url, cap);
				break;
			
			case "IOS":
				cap.setCapability("deviceName", "XT1068");
				cap.setCapability("udid", "ZX1D63BZ7B");
				cap.setCapability("platformVersion", "6.0");
				cap.setCapability("platformName", "ios");
				cap.setCapability("automationName", "UIAutomator2");
				cap.setCapability("appPackage", "com.adobe.reader");
				cap.setCapability("appActivity", "com.adobe.reader.AdobeReader");		
				
				driver = new IOSDriver(url, cap);
				break;
			
			case "WEB_ANDROID":
				cap.setCapability("deviceName", "XT1068");
				cap.setCapability("udid", "ZX1D63BZ7B");
				cap.setCapability("platformVersion", "6.0");
				cap.setCapability("platformName", "Android");
				cap.setCapability("automationName", "UIAutomator2");
				cap.setCapability("appPackage", "com.adobe.reader");
				cap.setCapability("appActivity", "com.adobe.reader.AdobeReader");		
				
				driver = new AndroidDriver(url, cap);
				break;
			
			case "WEB_IOS":
				cap.setCapability("deviceName", "XT1068");
				cap.setCapability("udid", "ZX1D63BZ7B");
				cap.setCapability("platformVersion", "6.0");
				cap.setCapability("platformName", "Android");
				cap.setCapability("automationName", "UIAutomator2");
				cap.setCapability("appPackage", "com.adobe.reader");
				cap.setCapability("appActivity", "com.adobe.reader.AdobeReader");		
				
				driver = new IOSDriver(url, cap);
				break;
			
		}
		return driver;
	}

	public static WebDriver createBrowserSpecificDriver(String browserName) throws IOException {
		
		switch (browserName.toUpperCase()) {
		case "CHROME":
			//Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
			System.setProperty("webdriver.chrome.driver", "G:\\DriverExecutables\\chromedriver.exe");
			driver = new ChromeDriver(setUpChromeOptions());
			break;
			
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", "G:\\DriverExecutables\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		
		case "INTERNET_EXPLORER":
			System.setProperty("webdriver.ie.driver", "G:\\DriverExecutables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
			
		case "CHROME_DOCKER":
			//This will be added to the Chrome Case in Future
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080", "--igone-certificate-errors", "--disable-ipv6");
			driver = new ChromeDriver(option);
			break;
			
		case "FIREFOX_DOCKER":
			//This will be added to the Firefox Case in Future
			FirefoxProfile fp = new FirefoxProfile();
			
			//option.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080", "--igone-certificate-errors", "--disable-ipv6");
			//driver = new ChromeDriver(option);
			break;
		
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}
	
	public static ChromeOptions setUpChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		//Complete the code to cover all Chrome Instances i.e. Local, Remote & Docker
		
		return options;
		// extend the options for all other browsers;
	}
}
