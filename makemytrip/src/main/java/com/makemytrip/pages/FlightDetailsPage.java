package com.makemytrip.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.makemytrip.utilities.TestUtil;

public class FlightDetailsPage {
	private WebDriver driver;
	@FindBy(how = How.XPATH, using = "//div[@id='ow_domrt-jrny']/div[2]/div")
	private List<WebElement> allDepartureFlights;
	
	@FindBy(how = How.XPATH, using = "//div[@id='ow_domrt-jrny']/following::div[@class='fli-list splitVw-listing']")
	private List<WebElement> allReturnFlights;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Non Stop')]")
	private WebElement nonStopCheckBox;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'1 Stop')]")
	private WebElement onetopCheckBox;
	

	@FindBy(how = How.XPATH, using = "(//div[@class='pull-right marL5 text-right']/p)[1]")
	private WebElement dFlightpriceAtBottomLabel;
	

	@FindBy(how = How.XPATH, using = "(//div[@class='pull-right marL5 text-right']/p)[2]")
	private WebElement rFlightpriceAtBottomLabel;


	@FindBy(how = How.CSS, using = "span[class='splitVw-total-fare']")
	private WebElement totalFlightAmountLabel;
	
	
	

	public FlightDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void printTotalNumberOfDepartureFlights() throws InterruptedException
	{
		TestUtil.scrollTillBottom();
		
		System.out.println("Total Count of Departure Flights are "+  allDepartureFlights.size());
		System.out.println("Total Count of Return Flights are "+ allReturnFlights.size());
		
	}
	public FlightDetailsPage clickNonStopCheckBox()
	{
		try {
			TestUtil.scrollToTop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		nonStopCheckBox.click();
		return new FlightDetailsPage(driver);
		
	}
	public FlightDetailsPage clickOneStopCheckBox()
	{
		try {
			TestUtil.scrollToTop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onetopCheckBox.click();
		return new FlightDetailsPage(driver);
		
	}
	public void selectDepartureAndReturnFlights(int departureRowNumber,int returnFlightRowNumber)
	{
		// Below line of code is to select the radio button based on the rownumber passed.
		WebElement departureRadioButton = driver.findElement(By.xpath("(//div[@id='ow_domrt-jrny']/div[2]/div/label//span[@class='splitVw-outer append_right9'])["+departureRowNumber+"]"));
		TestUtil.clickByJS(departureRadioButton);
		WebElement price = driver.findElement(By.xpath("(//div[@id='ow_domrt-jrny']/div[2]/div/label/div[2]/div[3])["+departureRowNumber+"]"));
		String priceValue = price.getText();
		System.out.println("Flight Amount is "+priceValue);
		String priceValueAtBottom=dFlightpriceAtBottomLabel.getText();
		/*String priceValueAtBottom=driver.findElement(By.xpath("(//div[@class='pull-right marL5 text-right']/p)[1]")).getText();*/
		System.out.println("Price Value displayed at Bottom of the page is "+ priceValueAtBottom);
		TestUtil.assertResults(priceValue, priceValueAtBottom);
		
		// Below lines of code is to select the radio return flight button based on the rownumber passed.
		
		WebElement returnRadioButton = driver.findElement(By.xpath("(//div[@id='rt-domrt-jrny']/div[2]/div/label//span[@class='splitVw-outer append_right9'])["+returnFlightRowNumber+"]"));
		TestUtil.clickByJS(returnRadioButton);
		WebElement returnPrice = driver.findElement(By.xpath("(//div[@id='rt-domrt-jrny']/div[2]/div/label/div[2]/div[3])["+returnFlightRowNumber+"]"));
		String returnPriceValue = returnPrice.getText();
		System.out.println("Flight Amount is "+returnPriceValue);
		String priceValueAtBottomRight=rFlightpriceAtBottomLabel.getText();
		/*String priceValueAtBottomRight=driver.findElement(By.xpath("(//div[@class='pull-right marL5 text-right']/p)[2]")).getText();*/
		System.out.println("Price Value displayed at Bottom of the page is "+ priceValueAtBottomRight);
		TestUtil.assertResults(returnPriceValue, priceValueAtBottomRight);
		
		// below code is to Verify the correct total amount (Departure Flight price + Return Flight
		// price) is getting reflected correctly
		
		/*String totalAmount = driver.findElement(By.cssSelector("span[class='splitVw-total-fare']")).getText();*/
		String totalAmount =totalFlightAmountLabel.getText();
		System.out.println(totalAmount);
		// Removing characters other than numbers from total Amount
		totalAmount=totalAmount.replaceAll("[\\D]+","");
		System.out.println(totalAmount);
		
		// Removing characters other than numbers from the return flight amount
		priceValueAtBottomRight=priceValueAtBottomRight.replaceAll("[\\D]+","");
		System.out.println(priceValueAtBottomRight);
		
		//Removing characters other than numbers from the departure flight amount
		 priceValueAtBottom = priceValueAtBottom.replaceAll("[\\D]+","");
		 System.out.println(priceValueAtBottom);
		 
		Integer flightsSum= Integer.valueOf(priceValueAtBottom)+Integer.valueOf(priceValueAtBottomRight);
		String sum = String.valueOf(flightsSum);
		TestUtil.assertResults(sum, totalAmount);
		
		
	
	}
}
