package com.epam.AmazonTest.tests;

import com.epam.AmazonTest.utility.PropertyUtil;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.epam.AmazonTest.driver.DriverFactory;

import com.epam.AmazonTest.reports.ReportListener;

import java.util.Properties;

@Listeners(ReportListener.class)
public class TestBase {
	public WebDriver driver;
	private Properties urlPropertyObject = PropertyUtil.getURLPropertyObject();

	@BeforeTest
	@Parameters({ "browserType" })
	public void setUp(@Optional("chrome") String browsername) {
		driver = DriverFactory.getDriver(browsername);
		driver.navigate().to(urlPropertyObject.getProperty("basepageurl"));
	}
	
	

	@AfterClass(alwaysRun = true)
	public void stopBrowser() {
		DriverFactory.closeDriver(driver);
	}
}
