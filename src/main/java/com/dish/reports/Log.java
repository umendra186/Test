package com.dish.reports;

import org.apache.log4j.Logger;

public class Log {
	
	public static Logger logger = Logger.getLogger(Reports.class.getName());
	
	public static void startTest(String testName, String desc) 
	{
		logger.info("###########################        " + testName + "     ###########################");
		
		logger.info("**************--------------------------------------**********************");

		logger.info("###########################        " + desc + "     ###########################");

		logger.info("**************--------------------------------------**********************");
	}

	public static void info(String desc) 
	{
		logger.info(desc);
	}

	public static void pass(String desc) 
	{
		logger.info(desc);
	}

	public static void fail(String desc) 
	{
		logger.info(desc);
	}
	
	public static void endTest() {
		logger.info("---------------- END TEST ---------------------------------");
		logger.info("---------------- END TEST ---------------------------------");
		logger.info("---------------- (:)(:)(:) ---------------------------------");
	}




}
