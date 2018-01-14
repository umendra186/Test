package com.dish.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.dish.constant.Constant;
import com.dish.reports.Log;
import com.dish.reports.Reports;
import com.dish.sling.modules.LaunchEnvironment;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class Utility extends LaunchEnvironment 
{
	public static Properties prop;
	public static InputStream file;
	public static String value, data, filepath;
	public static String locvalue;
	public static String loctype;
	public static WebElement loct;

	public static String ReadProperty(String key) {
		file = null;
		try {
			file = new FileInputStream(Constant.locator_sling);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop = new Properties();
			prop.load(file);
			value = prop.getProperty(key);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to Load/Read property file");
		}
		return value;
	}

	public static Properties loadProperty(String filelocation) {
		// file = null;
		prop = new Properties();
		try {
			file = new FileInputStream(filelocation);
		} catch (FileNotFoundException e) {
			Reports.fail(e.toString());
			e.printStackTrace();
		}
		try {
			prop.load(file);
		} catch (IOException e) {
			Reports.fail(e.toString());
			e.printStackTrace();
		}
		return prop;
	}

	public static String ReadConfigProperty(String key) {
		file = null;
		try {
			file = new FileInputStream(Constant.config_sling);
		} catch (FileNotFoundException e) {
			Reports.fail(e.toString());
			e.printStackTrace();
		}
		try {
			prop = new Properties();
			prop.load(file);
			value = prop.getProperty(key);

		} catch (IOException e) {
			Reports.fail(e.toString());
			System.out.println("Unable to Load/Read Config property file");
		}
		return value;
	}

	public static WebElement getLocator(String key) {
		try {
			// String locaterValue = Utility.ReadProperty(key);
			// String[] locaterarray = locaterValue.split("##");
			String[] locaterarray = key.split("##");
			locvalue = locaterarray[0];
			loctype = locaterarray[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			Reports.fail(e.toString());
			e.printStackTrace();
		}
		// System.out.println(locvalue);
		// System.out.println(loctype);
		try {
			if (loctype.equalsIgnoreCase("id")) {
				loct = driver.findElement(By.id(locvalue));
				ElementHighlight(loct);
			} else if (loctype.equalsIgnoreCase("xp")) {
				loct = driver.findElement(By.xpath(locvalue));
				ElementHighlight(loct);
			} else if (loctype.equalsIgnoreCase("lnktxt")) {
				loct = driver.findElement(By.linkText(locvalue));
				ElementHighlight(loct);
			} else if (loctype.equalsIgnoreCase("name")) {
				loct = driver.findElement(By.name(locvalue));
				ElementHighlight(loct);
			} else if (loctype.equalsIgnoreCase("partlnktxt")) {
				loct = driver.findElement(By.partialLinkText(locvalue));
				ElementHighlight(loct);
			} else if (loctype.equalsIgnoreCase("css")) {
				loct = driver.findElement(By.cssSelector(locvalue));
				ElementHighlight(loct);
			} else if (loctype.equalsIgnoreCase("tagname")) {
				loct = driver.findElement(By.tagName(locvalue));
				ElementHighlight(loct);
			} else {
				System.out.println("Locaters not matched");
			}
		} catch (ElementNotFoundException e) {
			Reports.fail(e.toString());
			e.printStackTrace();

		}
		return loct;

	}

	/*public static String getSuccessScreenshot() {
		try {
			filepath = null;
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			filepath = System.getProperty("user.dir") + "\\Screenshots\\SuccessScreenshot\\"
					+ System.currentTimeMillis() + ".png";
			FileUtils.copyFile(file, new File(filepath));
		} catch (IOException e) {
			Reports.fail(e.toString());
			e.printStackTrace();
		}
		return filepath;
	}*/

	public static void closeBrowser() throws IOException {
		try {
			Log.info("Closing Browser");
			driver.close();

		} catch (Exception e) {
			Reports.fail(e.toString());
			e.printStackTrace();

		}

	}

	public static String getfailScreenshot() {
		try {
			filepath = null;
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			filepath = System.getProperty("user.dir") + "\\Screenshots\\FailedScreenshot\\" + System.currentTimeMillis()
					+ ".png";
			FileUtils.copyFile(file, new File(filepath));
		} catch (IOException e) {
			Reports.fail(e.toString());
			e.printStackTrace();
		}
		return filepath;
	}

	public static void ElementHighlight(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'background:yellow; border:2px solid red;');", element);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Reports.fail(e.toString());
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'border: solid 2px green ');",
				element);
	}

	/*public static String getDatetime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String founddate = dateFormat.format(date);
		//System.out.println(dateFormat.format(date)); // 2014/08/06 15:59:48

		String[] parts = founddate.split(" ");
		// String part1 = parts[0]; // 004
		String[] appenderpart1 = parts[0].split("/");
		String[] appenderpart2 = parts[1].split(":");
		String appender = appenderpart1[1] + appenderpart1[2] + appenderpart2[0] + appenderpart2[1] + appenderpart2[2];
		//System.out.println(appender);
		return appender;
	}*/

	public static void waitElement(WebElement element) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(120, TimeUnit.SECONDS)
				.pollingEvery(300, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		
	}

}
