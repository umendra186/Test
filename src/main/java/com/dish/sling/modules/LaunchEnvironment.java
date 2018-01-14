package com.dish.sling.modules;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.dish.constant.SlingConfigMapper;
import com.dish.constant.Constant;
import com.dish.reports.Log;
import com.dish.reports.Reports;
import com.dish.utility.Utility;

public class LaunchEnvironment {

	public static WebDriver driver;
	public static String imagepath;
	public static boolean flag;
	/*public static Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(120, TimeUnit.SECONDS)
			.pollingEvery(300, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);*/
	public static boolean LaunchEnv(String browser) throws IOException {
		flag = false;

		try {

			Log.info("Launching Environment as per pre-condition");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			DesiredCapabilities cap1 = DesiredCapabilities.internetExplorer();
			
			if (browser.equalsIgnoreCase("chrome")) {
				cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				System.setProperty("webdriver.chrome.driver", Constant.chromeDriver_path);
				driver = new ChromeDriver();
				Reports.info("Chrome Browser Launched");
				Log.info("Chrome Browser Launched");
			} else if (browser.equalsIgnoreCase("IE")) {
				cap1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				System.setProperty("webdriver.ie.driver", Constant.ieDriver_path);
				driver = new InternetExplorerDriver();
				Reports.info("IE Browser Launched");
				Log.info("IE Browser Launched");
			}
			else if (browser.equalsIgnoreCase("firefox")) {
			//	cap1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				System.setProperty("webdriver.gecko.driver", Constant.ffDriver_path);
				driver = new FirefoxDriver();
				Reports.info("firefox Browser Launched");
				Log.info("firefox Browser Launched");
			}
			driver.manage().window().maximize();

			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
			
			flag = true;
			Reports.info("Envionment Successfully Launched");
			Log.info("Envionment Launched");			
		} catch (Exception e) {
			imagepath = Utility.getfailScreenshot();
			Reports.attachfailScreenshot(e.toString(), imagepath);
			e.printStackTrace();
			Utility.closeBrowser();
			
		}

		return flag;

	}
	
	public static boolean launchURl(String URL) throws InterruptedException, IOException
	{
		flag = false;
		try{
		driver.get(URL);		
		Log.info("Navigated to URL :- " + SlingConfigMapper.URL);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Reports.info("Navigated to URL :- " + SlingConfigMapper.URL);
		flag = true;
		}catch(Exception e)
		{
			imagepath = Utility.getfailScreenshot();
			Reports.attachfailScreenshot(e.toString(), imagepath);
			e.printStackTrace();
			Utility.closeBrowser();
	
		}
		return flag;
		
	}

}
