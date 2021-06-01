package com.demo.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryClass implements IRetryAnalyzer{

	private int retryCount = 0;
	private int maxRetryCount = 5;
	
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retry #" + retryCount + " for test: " + result.getName() + " with status " + getResultStatusName(result.getStatus()));
            return true;
        }
        return false;
    }

	private String getResultStatusName(int status) {
		String resultName = null;
		if(status == 1)
			resultName = "SUCCESS";
		if(status == 2)
			resultName = "FAILURE";
		if(status == 3)
			resultName = "SKIP";
		return resultName;
	}
	

}
