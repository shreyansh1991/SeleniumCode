package com.zoopla.utilities;

import org.openqa.selenium.WebDriver;

import com.zoopla.pages.HomePage;

public class PageObjectManager {
	private WebDriver driver;

	private HomePage homePage;

	public PageObjectManager(WebDriver driver) {

		this.driver = driver;

	}
/**
 * This method has two responsibilities:
To create an Object of Page Class only if the object is null.
To supply the already created object if it is not null 
 * @return
 */
	public HomePage getHomePage() {
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;

	}
}
