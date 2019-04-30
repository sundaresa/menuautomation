package com.vmetry.reporting;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ReportManager {
	
	private static ExtentReports extRep;
	
	public static synchronized ExtentReports getReporter(){
		if(extRep==null){
		String workingdir=System.getProperty("user.dir");
		String reportout=workingdir+"\\ExtentOutput\\ExtentsReport.html";
		File configfile=new File(workingdir+"\\lib\\extent-config.xml");
		extRep=new ExtentReports(reportout,true);
		extRep.loadConfig(configfile);
		}
		return extRep;
		
	}
}
