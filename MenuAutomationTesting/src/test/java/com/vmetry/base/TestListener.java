package com.vmetry.base;

import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;
import com.vmetry.base.Initialization;
import com.vmetry.reporting.ReportManager;
import com.vmetry.reporting.ExtentTestManager;

public class TestListener extends Initialization implements ITestListener  {

	String filePath = "target/surefire-reports/screenshots/";

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Before starting all tests, below method runs.
	public void onStart(ITestContext iTestContext) {
		iTestContext.setAttribute("WebDriver", null);
		
		    }


	// After ending all tests, below method runs.
	public void onFinish(ITestContext iTestContext) {
		// Do tier down operations for extentreports reporting!
		ExtentTestManager.endTest();
		ReportManager.getReporter().flush();
	}

	public void onTestStart(ITestResult iTestResult) {
		// Start operation for extentreports.
		String testClassName = iTestResult.getTestClass().getName();
		String testName = iTestResult.getMethod().getMethodName();
		testClassName = "<font size=\"1\"> " + testClassName + " : " + "</font>";
		testName = "<font size=\"2\"> " + testName + "</font>";
		ExtentTestManager.startTest(testClassName + testName, "");
		// ExtentTestManager.getTest().log(LogStatus.INFO,
		// DataInputs.testParams);
	}

	public void onTestSuccess(ITestResult iTestResult) {
		// Extentreports log operation for passed tests.
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
		long timeStamp = (iTestResult.getEndMillis() - iTestResult.getStartMillis()) / 1000;
		ExtentTestManager.getTest().log(LogStatus.INFO, "Test Execution time(in sec) : " + String.valueOf(timeStamp));
	}

	public void onTestFailure(ITestResult iTestResult) {
		   Object testClass = iTestResult.getInstance();
	       
	    //Take base64Screenshot screenshot.
	        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)wd).
	                getScreenshotAs(OutputType.BASE64);
	        //Extentreports log and screenshot operations for failed tests.
	        ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed",
	                ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
		// Extentreports log and screenshot operations for failed tests.
		ExtentTestManager.getTest().log(LogStatus.FAIL, iTestResult.getMethod().getMethodName() + " - Failed");
		ExtentTestManager.getTest().log(LogStatus.INFO, iTestResult.getThrowable());
		//
		// // This is for legacy testng reporting on jenkins
		// // can be removed if jenkins can implement extent reporting
		// String methodName = iTestResult.getName().toString().trim();
		// takeScreenShot(methodName);
	}

	public void onTestSkipped(ITestResult iTestResult) {
		// Extentreports log operation for skipped tests.
		String methodName = iTestResult.getMethod().getMethodName();
		methodName = methodName.toUpperCase();
		ExtentTestManager.getTest().log(LogStatus.SKIP, "<font color=\"FFA500\">" + methodName + " - skipped </font>");
		ExtentTestManager.getTest().log(LogStatus.INFO, iTestResult.getThrowable().getMessage());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}
