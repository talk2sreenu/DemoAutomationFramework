package com.testTurotials;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test1 {
	
	public void checkWaits() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		options.merge(dc);
		WebDriver driver = new ChromeDriver(options);
		WebDriver rDriver = new RemoteWebDriver(new URL("value"), dc);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		Actions aa = new Actions(driver);
		WebElement atxt = driver.findElement(By.xpath("//a[@id='value']"));
		aa.moveToElement(atxt).build().perform();
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('text').value='Hello World'");
		jse.executeScript("window.scrollBy(0,250)");
		
		PageFactory.initElements(driver, this.getClass());
	}
	
	
	public static void main(String[] args) {
		
	}

}
