package com.makemytrip.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.makemytrip.tests.BaseTest;

public class TestListener extends BaseTest implements ITestListener{

	public void onTestStart(ITestResult result) {
		logger.info("<----"+result.getName()+ "  is Started---->");
	}

	public void onTestSuccess(ITestResult result) {
		logger.info("<----"+result.getName()+ "  was Passed---->");
	}

	public void onTestFailure(ITestResult result) {
		
		logger.error("<----"+result.getName()+ "  was Failed---->");
	}

	public void onTestSkipped(ITestResult result) {
		logger.warn("<----"+result.getName()+ "  was Skipped---->");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		
	}

	

}
