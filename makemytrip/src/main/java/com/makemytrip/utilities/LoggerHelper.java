package com.makemytrip.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {
	private static boolean root = false;
	

	public static Logger getLogger(Class c) {
		if (root) {
			return Logger.getLogger(c);
		}
		// PropertiesConfigurator is used to configure logger from properties file
		PropertyConfigurator.configure("log4j.properties");
		root = true;
		return Logger.getLogger(c);
	}

}
