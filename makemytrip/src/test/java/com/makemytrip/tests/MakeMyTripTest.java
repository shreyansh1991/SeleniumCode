package com.makemytrip.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.makemytrip.listeners.TestListener;
import com.makemytrip.pages.FlightDetailsPage;
import com.makemytrip.pages.HomePage;
import com.makemytrip.utilities.MyScreenRecorder;

@Listeners(TestListener.class)
public class MakeMyTripTest extends BaseTest {
	@Test()
	public void makeMyTripTest() throws Exception {
		extentTest = extent.startTest("makeMyTripTest");
		MyScreenRecorder.startRecording("makeMyTripTest");
		HomePage homePage = pageObjectManager.getHomePage();
		FlightDetailsPage flightDetailsPage = homePage.searchRoundTripFlights("Delhi", "Bangalore,India");
		flightDetailsPage.printTotalNumberOfDepartureFlights();
		flightDetailsPage.clickNonStopCheckBox().printTotalNumberOfDepartureFlights();
		flightDetailsPage.clickNonStopCheckBox();
		flightDetailsPage.clickOneStopCheckBox().printTotalNumberOfDepartureFlights();
		flightDetailsPage.clickOneStopCheckBox();
		flightDetailsPage.selectDepartureAndReturnFlights(3, 4);
		MyScreenRecorder.stopRecording();

	}
}
