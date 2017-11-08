package com.emirates.utils;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ReportTest 
{
	@SuppressWarnings("deprecation")
	public static ExtentReports ERInstance()
	{
		ExtentReports extent;
		String Path="./Report/TestReport.html";
		File file=new File(Path);
		if(file.exists())
		{
			file.delete();
		}
		extent=new ExtentReports(Path,false);
		extent.config().documentTitle("Automation Report").reportName("Smoke Test");
		return extent;
	}
}

