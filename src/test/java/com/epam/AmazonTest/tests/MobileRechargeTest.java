package com.epam.AmazonTest.tests;

import com.epam.AmazonTest.pages.*;
import com.epam.AmazonTest.utility.ExcelUtil;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class MobileRechargeTest extends TestBase {

	private static final Logger logger = Logger.getLogger(MobileRechargeTest.class);

	private LoginPage loginPage;
	private PaymentPage paymentPage;

	@DataProvider
	public Object[][] credentialsAuthentication() {
		Object[][] testObjArray = ExcelUtil.getExcelDataAsTableArray(1);
		System.out.println(testObjArray.toString());
		return (testObjArray);
	}

	@Test(priority = 0)
	public void enteringDetailsForMobileRecharge() throws PageNotFoundException {
		HomePage homePage = new HomePage(driver).openPage();
		AmazonPayPage amazonPayPage = homePage.goToAmazonPayPage().openPage();
		MobileRechargePage mobileRechargePage = amazonPayPage.goToMobileRechargePage().openPage();
		mobileRechargePage.waitSeconds(5);
		mobileRechargePage.enterMobileNo("8956237410");
		logger.info("Mobile Operator : " + mobileRechargePage.getOperatorAndCircle());
		mobileRechargePage.selectPlan();
		loginPage = mobileRechargePage.buyPlan().openPage();
		assertTrue(loginPage instanceof LoginPage);
	}

	@Test(priority = 1, dependsOnMethods = "enteringDetailsForMobileRecharge", dataProvider = "credentialsAuthentication")
	public void validatingCredentials(String userId, String password) {
		assertTrue(loginPage.checkUserId(userId));
		assertTrue(loginPage.checkPassword(password));
		logger.info("redirecting to payment section");
	}

	@Test(priority = 2, dependsOnMethods = "validatingCredentials")
	public void paymentSectionValidation() throws PageNotFoundException {
		paymentPage = new PaymentPage(driver).openPage();
		assertTrue(paymentPage instanceof PaymentPage);
		logger.info("redirection to payment section successful !");
	}

}
