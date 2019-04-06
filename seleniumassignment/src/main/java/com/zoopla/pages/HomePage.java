package com.zoopla.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private WebDriver driver;
	@FindBy(id = "search-input-location")
	private WebElement searchTextBox;
	
	@FindBy(id="search-submit")
	private WebElement searchButton;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public PropertyPage searchCity(String cityName)
	{
		searchTextBox.sendKeys(cityName);
		searchButton.click();
		return new PropertyPage(driver);
	}
}
