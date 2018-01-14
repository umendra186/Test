package com.dish.sling.testflow;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dish.constant.Constant;
import com.dish.constant.SlingConfigMapper;
import com.dish.dataprovider.DriveSlingTestData;
import com.dish.reports.Log;
import com.dish.reports.Reports;
import com.dish.sling.modules.AdminLogin;
import com.dish.sling.modules.LaunchEnvironment;
import com.dish.utility.Utility;

public class AdminLoginTC 
{
	static Properties slingConfig = Utility.loadProperty(Constant.config_sling);
	
	  @Test(dataProvider = "AdminLogin", dataProviderClass = DriveSlingTestData.class)
	  public void adminUserLogin(String Username, String Password, String RunMode) throws InterruptedException, IOException {
		  if(RunMode.equalsIgnoreCase("Y"))
		  {
			  Assert.assertTrue(AdminLogin.launchURl(SlingConfigMapper.URL),"Unable to navigate to URL");
			  System.out.println("url: "+SlingConfigMapper.URL);
			  Assert.assertTrue(AdminLogin.userLogin(Username, Password), "Unable to Register Account on CSA WEB");
		 
		  }
	  }
	  @BeforeMethod
		public static void Config() throws Exception {
			System.out.println("Step 1");
			Assert.assertTrue(LaunchEnvironment.LaunchEnv(slingConfig.getProperty("browser")),"Unable to Launch Configuration");
			Log.info("Configuration Launched");
	
		}
	  
	  @BeforeTest
	  public static void IntialiseReports()
	  {
		  DOMConfigurator.configure("log4j.xml");
		  Reports.startTest("AdminLoginTC","Admin user logging into the application");
	  }
	  
	  @AfterMethod
	  public static void FlushReport() throws Exception 
	  {
		  	Reports.info("**************************' TEST CASE FINISH  '*****************************");
		  	Reports.endTest();
			Reports.flush();
			Utility.closeBrowser();
	  }

}
