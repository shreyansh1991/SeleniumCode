package com.zoopla.tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.zoopla.utilities.ExcelReader;
import com.zoopla.utilities.PageObjectManager;
import com.zoopla.webdriverbase.WebDriverBase;

public class BaseTest {
	WebDriver driver;
	PageObjectManager pageObjectManager;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "Testdata.xlsx");

	@BeforeSuite
	public void init() throws IOException {
		driver = WebDriverBase.getCurrentDriver();
		WebDriverBase.getCurrentURL();
		pageObjectManager = new PageObjectManager(driver);
	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
		WebDriverBase.closeAllBrowsers();
		}
	}
}
