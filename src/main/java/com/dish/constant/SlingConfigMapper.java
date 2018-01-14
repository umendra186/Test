package com.dish.constant;

import java.util.Properties;
import com.dish.utility.Utility;

public class SlingConfigMapper 
{
	static Properties slingConfig = Utility.loadProperty(Constant.config_sling);
	
	public static String URL = slingConfig.getProperty("URL");
	public static String browser = slingConfig.getProperty("browser");
	
}
