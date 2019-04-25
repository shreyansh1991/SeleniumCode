package com.makemytrip.tests;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.makemytrip.utilities.ExcelReader;
import com.makemytrip.utilities.LoggerHelper;
import com.makemytrip.utilities.PageObjectManager;
import com.makemytrip.webdriverbase.WebDriverBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	protected static WebDriver driver;
	PageObjectManager pageObjectManager;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "Testdata.xlsx");

	public static Logger logger = LoggerHelper.getLogger(BaseTest.class);

	public static ExtentReports extent;
	public static ExtentTest extentTest;
	
	@BeforeTest
	public void setExtent() {

		extent = new ExtentReports(System.getProperty("user.dir") + "/reports/ExtentReport.html", true);
	}

	@BeforeMethod
	public void init() throws IOException {
		driver = WebDriverBase.getCurrentDriver();
		WebDriverBase.getCurrentURL();
		logger.info("Navigated to Required URL");
		pageObjectManager = new PageObjectManager(driver);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, AWTException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test Script Failed" + result.getName());
			extentTest.log(LogStatus.FAIL, "Test Script Failed" + result.getThrowable());
			String path = getScreenshot(driver);

			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(path));

		}

		if (result.getStatus() == ITestResult.SUCCESS) {

			extentTest.log(LogStatus.PASS, "Test Script Passed" + result.getName());

		}
		if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Script Skipped" + result.getName());
			extentTest.log(LogStatus.SKIP, "Test Script Skipped" + result.getThrowable());

		}

		if (driver != null) {
			WebDriverBase.closeAllBrowsers();
		}
		extent.endTest(extentTest);
	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

	public static String getScreenshot(WebDriver driver) throws IOException, AWTException {
		String format = "jpg";

		String fileName = "FullScreenshot." + format;
		File path = new File(System.getProperty("user.dir") + File.separator + "screenshot" + File.separator
				+ new SimpleDateFormat("dd_MM_YYYY").format(new Date()));
		String pathInString = path.toString();

		File file = new File(pathInString);
		file.mkdirs();
		Robot robot = new Robot();

		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
		String screenshotPath = pathInString + File.separator + timestamp() + fileName;
		try {
			ImageIO.write(screenFullImage, format, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("A full screenshot saved!");
		return screenshotPath;

	}

	private static String timestamp() {
		return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());

	}

}
