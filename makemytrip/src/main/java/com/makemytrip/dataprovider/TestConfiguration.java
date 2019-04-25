package com.makemytrip.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfiguration {
	static Properties p = null;

	public TestConfiguration() throws IOException {
		p = new Properties();
		File file = new File(System.getProperty("user.dir") + File.separator + "configuration.properties");
		FileInputStream in = new FileInputStream(file);
		p.load(in);
	}

	public static String getValueFromPropertyFile(String key) throws IOException {
		if(p==null)
		{
			new TestConfiguration();
		}
		String value = p.getProperty(key);
		return value;
	}
}
