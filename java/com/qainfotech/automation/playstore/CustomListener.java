package com.qainfotech.automation.playstore;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener extends PlayStore_Test implements ITestListener{
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("FAILED Test");
		failed();
		
	}
	

}
