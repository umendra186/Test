package com.dish.dataprovider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.dish.constant.Constant;
import com.dish.utility.ExcelUtils;




public class DriveSlingTestData {
	
	@DataProvider(name = "AdminLogin")
	public static Object[][] ReadExcelData() throws IOException
	{
		Object[][] data = ExcelUtils.readData(Constant.tc_data_path,"AdminLogin");
		return data;
	}

	/*@DataProvider(name = "CSARegistration")
	public static Object[][] AccountRegistration() throws IOException
	{
		Object[][] data = ExcelUtils.readData(Constant.tc_data_path,"AccountRegister");
		return data;
	}*/

}
