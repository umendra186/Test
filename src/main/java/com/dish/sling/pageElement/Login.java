package com.dish.sling.pageElement;

import com.dish.utility.Utility;

public class Login {
	
	public static void UserID(String locValue, String testdata)
	{
		Utility.getLocator(locValue).sendKeys(testdata);
	}
	
	public static void password(String locValue, String testdata)
	{
		Utility.getLocator(locValue).sendKeys(testdata);
	}
	
	public static void submitAction(String locValue)
	{
		Utility.getLocator(locValue).click();
	}
	
	public static void Logout(String locValue)
	{
		Utility.getLocator(locValue).click();
	}

}
