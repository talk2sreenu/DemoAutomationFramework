package com.demo.testcases;

import java.util.Map;

import org.testng.annotations.Test;

import com.demo.base.TestBase;

public class SimpleTest extends TestBase{
	
	@Test(dataProvider = "testData")
	public void simpleTest(Map<String, String> testData) {
		SimplePage sm = new SimplePage(driver);
		String Iteration = testData.get("Iteration");
		System.out.println("Running Iteration : "+ Iteration);
		sm.verifyBrowser(testData);
	}
	
	@Test
	public void secondTest() {
		SimplePage sm = new SimplePage(driver);
		sm.lauchBrowser();
	}
}
