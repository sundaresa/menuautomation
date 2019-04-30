package com.vmetry.reporting;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentTestManager {

	static Map extentMap=new HashMap();
	public static ExtentReports extent=ReportManager.getReporter();
	
	public static synchronized ExtentTest getTest(){
		return (ExtentTest) extentMap.get((int)(long)(Thread.currentThread().getId()));
	}
	
	
	public static synchronized void endTest(){
		extent.endTest((ExtentTest) extentMap.get((int)(long)(Thread.currentThread().getId())));
	}
	
	
	@SuppressWarnings("unchecked")
	public static synchronized ExtentTest startTest(String name, String desc){
		ExtentTest test=extent.startTest(name,desc);
		extentMap.put((int)(long)(Thread.currentThread().getId()), test);
		return test;
	}
	
	public static void logforExtent(String descp){
		getTest().log(LogStatus.INFO, descp);
		
	}
	

}
