package com.zoopla.tests;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.zoopla.pages.AgentPage;
import com.zoopla.pages.HomePage;
import com.zoopla.pages.PropertyDetailsPage;
import com.zoopla.pages.PropertyPage;
import com.zoopla.utilities.TestUtil;

public class ZooplaTest extends BaseTest {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void zooplaTest(Hashtable<String,String> data) {
		
		HomePage homePage = pageObjectManager.getHomePage();
		PropertyPage propertyPage = homePage.searchCity(data.get("City"));
		PropertyDetailsPage propDetailsPage = propertyPage.printPropertyPricing().clickPropertyLink(Integer.parseInt(data.get("Row")));
		String agentName = propDetailsPage.getAgentName();
		AgentPage agentPage = propDetailsPage.printAgentDetails().clickAgentLink();
		//Below is the verification to validate agentName in AgentPage.
		Assert.assertEquals(agentName, agentPage.getAgentName(),"Mismatch.. ");
		System.out.println("Test Passed");
	}
}
