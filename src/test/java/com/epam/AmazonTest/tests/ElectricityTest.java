package com.epam.AmazonTest.tests;

import com.epam.AmazonTest.pages.AmazonPayPage;

import com.epam.AmazonTest.pages.ElectricityPage;
import com.epam.AmazonTest.pages.HomePage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ElectricityTest extends TestBase {

	private static final Logger logger = Logger.getLogger(ElectricityTest.class);

	private ElectricityPage electricityPage;

	@Test(priority = 0)
	public void checkStateSelection() {
		HomePage homePage = new HomePage(driver).openPage();
		AmazonPayPage amazonPayPage = homePage.goToAmazonPayPage();
		electricityPage = amazonPayPage.goToElectricityPge();
		electricityPage.selectState("Maharashtra");
		assertEquals(electricityPage.getSelectedState(), "Maharashtra");
	}

	@Test(priority = 1, dependsOnMethods = "checkStateSelection")
	public void checkBoardSelection() {
		electricityPage.selectBoard("B.E.S.T Mumbai");
		assertEquals(electricityPage.getSelectedBoard(), "B.E.S.T Mumbai");

	}

	@Test(priority = 2, dependsOnMethods = "checkBoardSelection")
	public void checkElectricityBillStatus() {
		electricityPage.enterCustomerNo("8956110080");
		electricityPage.fetchBill();
		assertTrue(electricityPage.getBillVisibility());
		logger.info(" Test Passed");
	}
}
