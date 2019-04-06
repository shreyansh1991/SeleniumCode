package com.zoopla.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PropertyPage {
	private WebDriver driver;
	private int pricingLinksCount;
	@FindBy(css = "li[id*='listing_']>div>div:nth-child(2)>a")
	private List<WebElement> allPropertyPricingLinks;

	public PropertyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public PropertyPage printPropertyPricing() {
		List<String> priceList = new ArrayList<String>();
		int pricingLinksCount = allPropertyPricingLinks.size();
		this.pricingLinksCount = pricingLinksCount;
		for (int i = 0; i < pricingLinksCount; i++) {
			String pricingValue = allPropertyPricingLinks.get(i).getText();
			String[] pricingValueArray = pricingValue.split(" ");
			priceList.add(pricingValueArray[0]);
		}
		// sorting in descending order
		Collections.sort(priceList, Collections.reverseOrder());
		// Printing the pricing value in descending order..
		System.out.println("Printing the pricing value in descending order..");
		for (int i = 0; i < priceList.size(); i++) {
			System.out.println(priceList.get(i) + " ");
		}
		return new PropertyPage(driver);

	}

	public PropertyDetailsPage clickPropertyLink(int rowNumber) {
		
		allPropertyPricingLinks.get(rowNumber-1).click();
		return new PropertyDetailsPage(driver);
	}
}
