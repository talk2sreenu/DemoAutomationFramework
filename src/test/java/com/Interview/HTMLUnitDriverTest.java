package com.Interview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HTMLUnitDriverTest {

	public static void main(String[] args) {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://www.google.com");						
		WebElement element = driver.findElement(By.name("q"));			
		element.sendKeys("Guru99");	
		element.submit();				
		System.out.println("Page title is: " + driver.getTitle());		
		driver.quit();	
	}
	
	
}
