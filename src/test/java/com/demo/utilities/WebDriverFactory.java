package com.demo.utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;

import com.demo.base.TestBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class WebDriverFactory extends TestBase {

	public static WebDriver driver_Local;
	public static String browserName;
	public static String platform;
	public static String mobilePlatForm;

	public static WebDriver createDriverInstance(ITestContext... context) throws IOException {
		if(context.length > 0)
			PropertiesLoader.loadContextProperties(context[0]);
		browserName = System.getProperty("selenium.browser");
		platform = System.getProperty("selenium.host");
		mobilePlatForm = System.getProperty("selenium.mobile.platform");

		switch(platform.toUpperCase()) {
			case "LOCAL":
				driver_Local = createBrowserSpecificLocalDriver(browserName);
				break;
				
			case "MOBILE":
				driver_Local = createDeviceSpecificDriver(mobilePlatForm);
				break;
				
			case "REMOTE":
				driver_Local = createBrowserSpecificRemoteDriver(browserName);
				break;
		}
		return driver_Local;
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
				
				driver_Local = new AndroidDriver(url, cap);
				break;
			
			case "IOS":
				cap.setCapability("deviceName", "XT1068");
				cap.setCapability("udid", "ZX1D63BZ7B");
				cap.setCapability("platformVersion", "6.0");
				cap.setCapability("platformName", "ios");
				cap.setCapability("automationName", "UIAutomator2");
				cap.setCapability("appPackage", "com.adobe.reader");
				cap.setCapability("appActivity", "com.adobe.reader.AdobeReader");		
				
				driver_Local = new IOSDriver(url, cap);
				break;
			
			case "WEB_ANDROID":
				cap.setCapability("deviceName", "XT1068");
				cap.setCapability("udid", "ZX1D63BZ7B");
				cap.setCapability("platformVersion", "6.0");
				cap.setCapability("platformName", "Android");
				cap.setCapability("automationName", "UIAutomator2");
				cap.setCapability("appPackage", "com.adobe.reader");
				cap.setCapability("appActivity", "com.adobe.reader.AdobeReader");		
				
				driver_Local = new AndroidDriver(url, cap);
				break;
			
			case "WEB_IOS":
				cap.setCapability("deviceName", "XT1068");
				cap.setCapability("udid", "ZX1D63BZ7B");
				cap.setCapability("platformVersion", "6.0");
				cap.setCapability("platformName", "Android");
				cap.setCapability("automationName", "UIAutomator2");
				cap.setCapability("appPackage", "com.adobe.reader");
				cap.setCapability("appActivity", "com.adobe.reader.AdobeReader");		
				
				driver_Local = new IOSDriver(url, cap);
				break;
		}
		return driver_Local;
	}

	public static WebDriver createBrowserSpecificLocalDriver(String browserName) throws IOException {
		
		switch (browserName.toUpperCase()) {
			case "CHROME":
				driver_Local = new ChromeDriver(setUpChromeOptions());
				break;
			case "FIREFOX":
				driver_Local = new FirefoxDriver(setupFirefoxOptions());
				break;
			case "INTERNET_EXPLORER":
				driver_Local = new InternetExplorerDriver(setupInternetOptions());
				break;
			case "CHROME_DOCKER":
				//This will be added to the Chrome Case in Future
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080", "--igone-certificate-errors", "--disable-ipv6");
				driver_Local = new ChromeDriver(option);
				break;		
		}
		
		driver_Local.manage().window().maximize();
		driver_Local.manage().deleteAllCookies();
		driver_Local.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver_Local;
	}
	
	private static InternetExplorerOptions setupInternetOptions() {
		System.setProperty("webdriver.ie.driver", "G:\\DriverExecutables\\IEDriverServer.exe");
		InternetExplorerOptions ip = new InternetExplorerOptions();
		// TODO Auto-generated method stub
		return ip;
	}

	private static FirefoxOptions setupFirefoxOptions() {
		System.setProperty("webdriver.gecko.driver", "G:\\DriverExecutables\\geckodriver.exe");
		FirefoxOptions fp = new FirefoxOptions();
		// TODO Auto-generated method stub
		return fp;
	}

	private static WebDriver createBrowserSpecificRemoteDriver(String browserName2) throws MalformedURLException {
		String hostURL = System.getProperty("selenium.remote.hub.url"); 
		URL url = new URL(hostURL);

		switch (browserName.toUpperCase()) {
			case "CHROME":
				driver_Local = new RemoteWebDriver(url, getChromeDesiredCapabilities());
				break;
			case "FIREFOX":
				driver_Local = new RemoteWebDriver(url, getFirefoxDesiredCapabilities());
				break;
		}

		driver_Local.manage().window().maximize();
		driver_Local.manage().deleteAllCookies();
		driver_Local.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver_Local;
	}

	private static Capabilities getFirefoxDesiredCapabilities() {
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setCapability("platform", "LINUX");
		dc.setJavascriptEnabled(true);
		return dc;
	}

	private static DesiredCapabilities getChromeDesiredCapabilities() {
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		ChromeOptions co = new ChromeOptions();
		co.addArguments(
                "--headless",
                "--disable-web-security",
                "--ignore-certificate-errors",
                "--privet-ipv6-only",
                "--no-sandbox",
                "--disable-gpu",
                "--disable-dev-shm-usage");
		
		dc.setCapability(ChromeOptions.CAPABILITY, co);
		dc.setCapability("platform", "LINUX");
		dc.setJavascriptEnabled(true);
		return dc;
	}

	public static ChromeOptions setUpChromeOptions() {
		System.setProperty("webdriver.chrome.driver", "G:\\DriverExecutables\\chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		ChromeOptions options = new ChromeOptions();
		
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		 
		options.addArguments("disable-infobars");
		options.addArguments("--no-sandbox");
		options.addArguments("--dns-prefetch-disable");
		options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return options;
	}
}