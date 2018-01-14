package com.dish.sling.modules;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dish.constant.SlingConfigMapper;
import com.dish.constant.SlingLocatorsMapper;
import com.dish.constant.Constant;
import com.dish.reports.Log;
import com.dish.reports.Reports;
import com.dish.sling.pageElement.Login;
import com.dish.utility.Utility;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class AdminLogin extends LaunchEnvironment 
{
	public static boolean flag;
	public static String imagePath;
	
	public static WebDriverWait wait = new WebDriverWait(driver, 120);
	
	public static Properties slingRepository = Utility.loadProperty(Constant.locator_sling);
	public static Properties slingConfig = Utility.loadProperty(Constant.config_sling);

	public static boolean userLogin(String userID, String password) throws IOException 
	{
		Reports.info("Preparing for Login with Admin user");
		Log.info("Preparing for Login with Admin user");
		flag = false;
		try 
		{
			try 
			{
				Select envtSelect=new Select(Utility.getLocator(SlingLocatorsMapper.environment));
				envtSelect.selectByVisibleText("qa");
				Reports.info("Successfully select the Environment as qa");
				Utility.getLocator(SlingLocatorsMapper.username).clear();
				Utility.getLocator(SlingLocatorsMapper.username).sendKeys(userID);
				Reports.info("Customer Login ID is : " + userID);
				Log.info("Customer Login ID : " + userID);
				Utility.getLocator(SlingLocatorsMapper.password).sendKeys(password);
				Reports.info("Customer Password is : " + password);
				Log.info("Customer Password is : " + password);
				Utility.getLocator(SlingLocatorsMapper.LoginBtn).click();
				Reports.info("Submit Button Clicked");
				Log.info("Submit Button Clicked");
			} 
			catch (Exception e) 
		{
				Select envtSelect=new Select(Utility.getLocator(SlingLocatorsMapper.environment));
				envtSelect.selectByVisibleText("devlopment");
				Reports.info("Successfully select the Environment as Dev");
				Utility.getLocator(SlingLocatorsMapper.username).clear();
				Utility.getLocator(SlingLocatorsMapper.username).sendKeys(userID);
				Reports.info("Customer Login ID is : " + userID);
				Log.info("Customer Login ID : " + userID);
				Utility.getLocator(SlingLocatorsMapper.password).clear();
				Utility.getLocator(SlingLocatorsMapper.password).sendKeys(password);
				Reports.info("Customer Password is : " + password);
				Log.info("Customer Password is : " + password);
				Utility.getLocator(SlingLocatorsMapper.LoginBtn).click();
				Reports.info("Submit Button Clicked");
				Log.info("Submit Button Clicked");
			}
			//Assert.assertEquals(driver.getTitle().trim(), SlingConfigMapper.After_Login_Title);
			Reports.info("User Successfully Logged In ");
			Log.info("User Successfully Logged In ");
			flag = true;
			Reports.pass("User Logged In with UserID ::-- " + userID + " and password ::-- " + password);

		} catch (Exception e) {
			imagePath = Utility.getfailScreenshot();
			Reports.attachfailScreenshot("Unable to Login with customer ID - " + userID + " and passowrd " + password,
					imagePath);
			e.printStackTrace();
			Utility.closeBrowser();
		}

		return flag;
	}

	/*
	public static boolean reset() throws IOException 
	{
		Log.info("Resetting Environment, Username and Password fields");
		flag = false;
		try 
		{
			try 
			{
				Utility.getLocator(SlingLocatorsMapper.environment).clear();
				Utility.getLocator(SlingLocatorsMapper.username).clear();
				Utility.getLocator(SlingLocatorsMapper.password).clear();
				Log.info("Environment, Username and Password fields cleared");
			} 
			catch (Exception e) 
			{
				
			}
			
			Log.info("User Successfully Logged In ");
			flag = true;
			/*
			 * imagePath = Utility.getSuccessScreenshot();
			 * Reports.attachScreenshot("USer Account Logged In", imagePath);
			 */
	/*
			Reports.pass("User Logged In with UserID " + userID + " and password " + password);

		} catch (Exception e) {
			imagePath = Utility.getfailScreenshot();
			Reports.attachfailScreenshot("Unable to Login with customer ID - " + userID + " and passowrd " + password,
					imagePath);
			e.printStackTrace();
			Utility.closeBrowser();
		}

		return flag;
	}

	*/
	
	/*
	public static boolean landingPageVerification() throws IOException {
		flag = false;
		Log.info("Verify landing page for Customer Account as per Sys/Prin");
		try {
			Thread.sleep(7000);
			String URL = driver.getCurrentUrl();
			Log.info("Current URL is :- " + URL);

			if (URL.endsWith("myprofile")) {
				Log.info("Landing page - My Profile");
				Thread.sleep(2000);
				String Account_partner = Utility.getLocater(SlingLocatorsMapper.Account_no_partner).getText().trim();
				if (Account_partner.startsWith("8072")) {
					Reports.pass("User has Logged in with Partner Account and Landing page is 'My Profile' ");
					Log.pass("User has Logged in with Partner Account");
				} else {
					Reports.fail("User Account number doesn't starts with 8072. Please check :- " + Account_partner);
					Log.fail("User Account number doesn't starts with 8072. Please check :- " + Account_partner);
				}

			} else if (URL.endsWith("myaccountsummary")) {
				Thread.sleep(4000);
				String Account_dish = Utility.getLocator(SlingLocatorsMapper.Account_no_dish).getText()
						.replace("Acct# ", "").trim();
				System.out.println(Account_dish);

				if (Account_dish.startsWith("8046")) {
					Reports.pass("User has Logged in with DISH Account and Landing page is 'My Account Summary' ");
					Log.pass("User has Logged in with DISH Account");
				} else {
					Reports.fail("User Account number doesn't starts with 8046. Please check :- " + Account_dish);
					Log.fail("User Account number doesn't starts with 8046. Please check :- " + Account_dish);
				}

			} else {
				Reports.fail(
						"Landing page is neither My Profile or My Account Summary page. Please Login with above URL manually with required TestData :-"
								+ URL);
				Log.fail(
						"Landing page is neither My Profile or My Account Summary page. Please Login with above URL manually with required TestData :-"
								+ URL);
			}
			flag = true;
		} catch (Exception e) {
			imagePath = Utility.getfailScreenshot();
			Reports.attachfailScreenshot(e.getMessage(), imagePath);
			e.printStackTrace();
			Utility.closeBrowser();
		}

		return flag;
	}

	public static void logout() {
		Login.Logout(SlingLocatorsMapper.logout);
		Reports.info("USer Successfully Logged Out");
		Log.info("USer Successfully Logged Out");
	} */

}
