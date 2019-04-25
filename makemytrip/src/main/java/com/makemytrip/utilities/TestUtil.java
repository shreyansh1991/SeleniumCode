package com.makemytrip.utilities;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.google.common.base.Function;
import com.makemytrip.tests.BaseTest;

public class TestUtil extends BaseTest {

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];

		Hashtable<String, String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String, String>();

			for (int colNum = 0; colNum < cols; colNum++) {

				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}

	public static void waitForTextToBeDisplayed(WebDriver driver, final By by, final String expectedText) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(java.time.Duration.ofSeconds(1));
		wait.withTimeout(java.time.Duration.ofSeconds(20));
		wait.ignoring(NoSuchElementException.class); // We need to ignore this exception.
														

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver dr) {
				WebElement element = dr.findElement(by);
				String actualText = element.getText();
				logger.info("Actual Text is "+ actualText);
				if (actualText.contains(expectedText)) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return true;
				} else {
					logger.info("still waiting for " + expectedText +  " to be displayed in suggestions");
				}
				return false;
			}
		};
		wait.until(function);
	}

	public static void mouseHover(WebElement element) {
		Actions action = new Actions(driver);

		action.moveToElement(element).build().perform();

	}

	
	public static void clickByJS(WebElement webelement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click();", webelement);	

	}
	
	public static void selectDate(WebElement calendar, String year, String monthName, String day, WebDriver driver) throws ParseException
	{
		//Clicking on calendar to open calendar widget
	//	calendar.click();
		
		// Retrieving current year value
		String currentYear= driver.findElement(By.cssSelector("div[class='DayPicker-Caption']>div")).getText();
			logger.info("currentYear is "+ currentYear);
		// Click on Next arrow till we get desired year
		if(!currentYear.contains(year))
		{
			do{
				driver.findElement(By.cssSelector("span[aria-label='Next Month']")).click();
			}while(!driver.findElement(By.xpath("(//div[@class='DayPicker-Caption']/following::span[1])[1]")).getText().equals(year));
			
		}
		
		// Select desired month after selecting desired year
		
		String currentMonth=  driver.findElement(By.cssSelector("div[class='DayPicker-Caption']>div")).getText();
		System.out.println(currentMonth);
		logger.info("currentMonth is "+ currentMonth);
		if(!currentMonth.contains(monthName))
		{
			do{
				driver.findElement(By.cssSelector("span[aria-label='Next Month']")).click();
			}while(!driver.findElement(By.cssSelector("div[class='DayPicker-Caption']>div")).getText().trim().contains(monthName));
		}
		//get java month number for desired month
		
		int javaMonthInt= getMonthJavaInt(monthName);
		
		
		List<WebElement> allDateOfDesiredMonth= driver.findElements(By.cssSelector("div[class*='DayPicker'][aria-disabled='false'] div p:nth-of-type(1)"));
		for(WebElement d:allDateOfDesiredMonth )
		{
			if(d.getText().trim().contains(day) || day.contains(d.getText().trim()))
			{
				TestUtil.clickByJS(d);
				break;
			}
		}
	}
	// Code to get java month number
	public static int getMonthJavaInt(String monthName) throws ParseException 
	{

		Date date = new SimpleDateFormat("MMMM").parse(monthName);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}
	
	public static String getCurrentDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MMMM/YYYY");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		return dateFormat.format(date);
		
	}
	public static String getDateAfterDays(int days,String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(cal.getTime());
	}
	
	public static void scrollTillBottom() throws InterruptedException
	{
		
		Long lastHeight = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
 
		while (true) {
		    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		    Thread.sleep(2000);
		    
		    Long newHeight = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
		    if (newHeight.compareTo(lastHeight)==0) {
		        break;
		    }
		    lastHeight = newHeight;
		}
	}
	
	public static void scrollToTop() throws InterruptedException
	{
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
	}
	public static void assertResults(String actual,String expected)
	{
		Assert.assertEquals(actual,expected,"Mismatch Please check..");
	}
}
