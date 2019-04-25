package com.makemytrip.pages;

import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.makemytrip.utilities.TestUtil;

public class HomePage {
	private WebDriver driver;
	

	@FindBy(how = How.CSS, using = "span[class='chNavIcon appendBottom2 chSprite chFlights active']")
	private WebElement flightLink;

	@FindBy(how = How.XPATH, using = "(//span[@class='tabsCircle appendRight5'])[2]")
	private WebElement roundTripRdbtn;

	@FindBy(how = How.ID, using = "fromCity")
	private WebElement fromCityLabel;

	@FindBy(how = How.CSS, using = "input[type='text'][placeholder='From']")
	private WebElement fromCityTextbox;

	@FindBy(how = How.CSS, using = "li[id='react-autowhatever-1-section-0-item-0'] p:nth-of-type(1)")
	private WebElement fromCitySuggestion;

	@FindBy(how = How.ID, using = "toCity")
	private WebElement toCityLabel;

	@FindBy(how = How.CSS, using = "input[type='text'][placeholder='To']")
	private WebElement toCityTextbox;

	@FindBy(how = How.XPATH, using = "//input[@id='departure']/../span")
	private WebElement departure;

	@FindBy(how = How.XPATH, using = "//input[@id='return']/../span")
	private WebElement returnLabel;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Search')]")
	private WebElement searchBtn;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public FlightDetailsPage searchRoundTripFlights(String from, String to) throws InterruptedException, ParseException {
		flightLink.click();
		roundTripRdbtn.click();
		fromCityLabel.click();
		fromCityTextbox.sendKeys(from);
		TestUtil.waitForTextToBeDisplayed(driver,
				By.cssSelector("li[id='react-autowhatever-1-section-0-item-0'] p:nth-of-type(1)"), "Delhi");

		fromCitySuggestion.click();
		TestUtil.clickByJS(toCityLabel);
		toCityTextbox.sendKeys(to);
		TestUtil.waitForTextToBeDisplayed(driver,
				By.cssSelector("li[id='react-autowhatever-1-section-0-item-0'] p:nth-of-type(1)"), "Bangalore");
		fromCitySuggestion.click();

		String currentDate = TestUtil.getCurrentDate();
		System.out.println("currentDate is " + currentDate);
		String dateArray[] = currentDate.split("/");
		String date = dateArray[0];
		String month = dateArray[1];
		String year = dateArray[2];
		// selectDate is a generic method in TestUtil class to select any date from calender.
		TestUtil.selectDate(departure, year, month, date, driver);
		String dateAfterDays = TestUtil.getDateAfterDays(7, "dd/MMMM/YYYY");
		String[] afterDate = dateAfterDays.split("/");
		date = afterDate[0];
		month = afterDate[1];
		year = afterDate[2];
		System.out.println(date);
		System.out.println(month);
		System.out.println(year);
		
		TestUtil.selectDate(departure, year, month, date, driver);
		
		searchBtn.click();
		return new FlightDetailsPage(driver);
		
	}
}
