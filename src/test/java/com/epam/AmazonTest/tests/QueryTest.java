package com.epam.AmazonTest.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.AmazonTest.pages.HomePage;
import com.epam.AmazonTest.pages.LoginPage;
import com.epam.AmazonTest.pages.ProductPage;
import com.epam.AmazonTest.pages.SearchResultPage;
import com.epam.AmazonTest.utility.ExcelUtil;

public class QueryTest extends TestBase {
	private LoginPage loginPage;

	@DataProvider
	public Object[][] credentialsAuthentication() {
		Object[][] testObjArray = ExcelUtil.getExcelDataAsTableArray(1);
		System.out.println(testObjArray.toString());
		return (testObjArray);
	}

	@Test(priority = 0)
	public void haveAQuestion() {
		HomePage homePage = new HomePage(this.driver).openPage();
		SearchResultPage searchResultPage = homePage.searchForProduct("sd card").openPage();
		ProductPage productPage = searchResultPage.selectProduct(1).openPage();
		if (!productPage.getAnswer("juice")) {
			loginPage = productPage.askToCommunity().openPage();
			assertTrue(loginPage instanceof LoginPage);
		}
	}

	@Test(priority = 1, dependsOnMethods = "haveAQuestion", dataProvider = "credentialsAuthentication")
	public void validatingCredentials(String userId, String password) {
		assertTrue(loginPage.checkUserId(userId));
		assertTrue(loginPage.checkPassword(password));
	}

	@Test(priority = 2, dependsOnMethods = "validatingCredentials")
	public void validatepostquestion() {
		assertTrue(new ProductPage(driver).openPage().validatePost());
	}
}
