package com.epam.AmazonTest.tests;

import com.epam.AmazonTest.pages.HomePage;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import com.epam.AmazonTest.pages.LoginPage;
import com.epam.AmazonTest.pages.PageNotFoundException;
import com.epam.AmazonTest.utility.ExcelUtil;

public class LoginTest extends TestBase {

	private HomePage homePage = null;
	private LoginPage loginPage = null;
	private static final Logger logger = Logger.getLogger(LoginTest.class);

	@Test(priority = 0)
	public void setUpLoginPage() throws PageNotFoundException {
		homePage = new HomePage(driver).openPage();
		assertTrue(homePage instanceof HomePage);
	}

	@Test(priority = 1, dependsOnMethods = "setUpLoginPage")
	public void goToSignInPagetest() throws InterruptedException, PageNotFoundException {
		loginPage = homePage.goToLoginPage();
		loginPage = loginPage.openPage();
		assertTrue(loginPage instanceof LoginPage);
	}

	@DataProvider
	public Object[][] credentialsAuthentication() throws Exception {
		Object[][] testObjArray = ExcelUtil.getExcelDataAsTableArray(0);
		System.out.println(testObjArray.toString());
		return (testObjArray);
	}

	@Test(priority = 2, dataProvider = "credentialsAuthentication", dependsOnMethods = "goToSignInPagetest")
	public void validateCredentialsTest(String userId, String password) throws InterruptedException {
		assertTrue(loginPage.checkUserId(userId));
		assertTrue(loginPage.checkPassword(password));
		logger.info("validation of user is successful");
	}

}
