package com.dish.reports;

import com.dish.constant.Constant;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports 
{
	public static ExtentReports reports = new ExtentReports(Constant.report_html, true);
	
	public static ExtentTest test;

	public static void startTest(String testName, String desc) 
	{
		test = reports.startTest(testName, desc);
	}

	public static void info(String desc) 
	{
		test.log(LogStatus.INFO, desc);
	}

	public static void pass(String desc) 
	{
		test.log(LogStatus.PASS, desc);
	}

	public static void fail(String desc) 
	{
		test.log(LogStatus.FAIL, desc);
	}
	
	public static void printException(Object errorstack)
	{
		test.log(LogStatus.FAIL,(Throwable) errorstack);
	}

	public static void endTest() 
	{
		reports.endTest(test);
	}

	public static void flush() 
	{
		reports.flush();
	}

	public static void attachScreenshot(String details, String imagePath) 
	{
		test.log(LogStatus.PASS, details + test.addScreenCapture(imagePath));
	}

	public static void attachfailScreenshot(String details, String imagepath) 
	{
		test.log(LogStatus.FAIL, details + test.addScreenCapture(imagepath));
	}

}
