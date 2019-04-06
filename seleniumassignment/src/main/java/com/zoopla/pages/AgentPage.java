package com.zoopla.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AgentPage {
	private WebDriver driver;
	@FindBy(xpath = "//div[@id='content']/div/h1/b[1]")
	private WebElement agentName;

	public AgentPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public String getAgentName()
	{
		return agentName.getText();
	}
	
}
