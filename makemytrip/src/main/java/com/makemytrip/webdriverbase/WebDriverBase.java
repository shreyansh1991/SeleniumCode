package com.makemytrip.webdriverbase;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.makemytrip.constants.Constants;
import com.makemytrip.dataprovider.TestConfiguration;
import com.makemytrip.tests.BaseTest;
import com.makemytrip.utilities.LoggerHelper;

public class WebDriverBase {
	static WebDriver driver = null;
	public static WebDriverWait wait;
	public static Logger logger=LoggerHelper.getLogger(BaseTest.class);
	

	public static WebDriver getCurrentDriver() throws IOException {
		
		String browserName = TestConfiguration.getValueFromPropertyFile("browser");
		logger.info("Browser from config file is " + browserName);

		if (driver == null) {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + File.separator + "browser_exe/chromedriver.exe");
				driver = new ChromeDriver();
				logger.info("Chrome driver is initialized.");
			} else if (browserName.equalsIgnoreCase("ff")) {

				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + File.separator + "browser_exe/geckodriver.exe");

				driver = new FirefoxDriver();
				logger.info("firefox driver is initialized.");
			} else {
				logger.info("Please provide the correct browser name.");
			}
			wait = new WebDriverWait(driver, Constants.explicitWait);
		}
		return driver;

	}

	public static void getCurrentURL() {
		driver.manage().timeouts().implicitlyWait(Constants.implicitWait,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String url = "";
		try {
			url = TestConfiguration.getValueFromPropertyFile("url");
			logger.info("URL from property file is "+url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!url.equals("") && url != null) {
			driver.get(url);
		} else {
			logger.info("Could not get current url!");
		}

	}

	public static void closeAllBrowsers() {
		if (driver != null)
			driver.quit();
	}

}
