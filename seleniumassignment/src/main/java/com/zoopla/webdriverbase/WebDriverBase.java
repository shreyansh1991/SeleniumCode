package com.zoopla.webdriverbase;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.zoopla.dataprovider.TestConfiguration;

public class WebDriverBase {
	static WebDriver driver = null;

	public static WebDriver getCurrentDriver() throws IOException {
		String browserName = TestConfiguration.getValueFromPropertyFile("browser");
		System.out.println("Browser from config file is " + browserName);

		if (driver == null) {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + File.separator + "browser_exe/chromedriver.exe");
				driver = new ChromeDriver();

			} else if (browserName.equalsIgnoreCase("ff")) {

				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + File.separator + "browser_exe/geckodriver.exe");

				driver = new FirefoxDriver();

			} else {
				System.out.println("Please provide the correct browser name.");
			}
		}
		return driver;

	}

	public static void getCurrentURL() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String url = "";
		try {
			url = TestConfiguration.getValueFromPropertyFile("url");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!url.equals("") && url != null) {
			System.out.println("Url is " + url);
			driver.get(url);
		} else {
			System.out.println("Could not get current url!");
		}

	}

	public static void closeAllBrowsers() {
		if (driver != null)
			driver.quit();
	}

}
