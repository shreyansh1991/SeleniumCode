package com.zoopla.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PropertyDetailsPage {
	private WebDriver driver;
	@FindBy(xpath = "//a[@data-track-label='Agent block 1']/following::p[1]/a")
	private WebElement agentNumberLabel;

	@FindBy(xpath = "//a[@data-track-label='Agent block 1']/div[2]/h4")
	private WebElement agentNameLabel;

	@FindBy(xpath = "//a[@data-track-label='Agent block 1']//img[1]")
	private WebElement agentLogo;

	public PropertyDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public AgentPage clickAgentLink() {
		agentNameLabel.click();
		return new AgentPage(driver);
	}
	
	public String getAgentName() {
		String agentName = agentNameLabel.getText();
		return agentName;
	}
	
	public PropertyDetailsPage printAgentDetails() {
	
		String agentName = agentNameLabel.getText();
		System.out.println("Agent Name is " + agentName);
		
		String agentNumber = agentNumberLabel.getText();
		// this below line is used to get the only Number part
		agentNumber = agentNumber.substring(4);
		
		System.out.println("Agent Number is " + agentNumber);

		// Below code is to verify that Logo is displayed.
		boolean logoStatus = agentLogo.isDisplayed();
		System.out.println("Logo is displayed ? "+logoStatus);
		return new PropertyDetailsPage(driver);
	}

}
