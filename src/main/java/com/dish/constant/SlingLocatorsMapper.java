package com.dish.constant;

import java.util.Properties;

import com.dish.utility.Utility;

public class SlingLocatorsMapper 
{
	static Properties slingRepository = Utility.loadProperty(Constant.locator_sling);

	//Administrator Login
	public static String environment = slingRepository.getProperty("environment");
	public static String username = slingRepository.getProperty("username");
	public static String password = slingRepository.getProperty("password");
	public static String LoginBtn = slingRepository.getProperty("LoginBtn");
	
	//Logout
	public static String logout = slingRepository.getProperty("logout");

}
