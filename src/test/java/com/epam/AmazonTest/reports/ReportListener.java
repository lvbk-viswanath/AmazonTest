package com.epam.AmazonTest.reports;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.epam.AmazonTest.tests.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportListener implements ITestListener {

	private static final Logger logger = Logger.getLogger(ReportListener.class);
	private static ExtentReports extent = new ExtentReports(
			System.getProperty("user.dir") + "/reports/test_reports.html");
	private ExtentTest log;
	ScreenshotFailed screenshot;

	public void onTestStart(ITestResult result) {
		log = extent.startTest(result.getName() + " test case of class : " + result.getInstanceName());
		logger.info("In test start "+result.getName() + " test case of class : " + result.getInstanceName());
	}

	public void onTestSuccess(ITestResult result) {
		log.log(LogStatus.PASS, result.getName() + " test case Passed");
		logger.info("In test Success "+result.getName() + " test case Passed");
	}

	public void onTestFailure(ITestResult result) {
		logger.info("In test failure : "+result.getName());
		String path = "";
		Object testClass = result.getInstance();
		logger.info("testClass is : "+testClass);
		logger.info("testbase casted testClass is : "+(TestBase)testClass);
		try {
			WebDriver webDriver = ((TestBase) testClass).driver;
			logger.info("WebDriver in Report Listener : "+webDriver);
			path = ScreenshotFailed.getScreenshot(webDriver, result.getName());
		} catch (NullPointerException e) {
			logger.error("In Report Listener " + e);
		}
		logger.info("The screenshot path in Report Listener : " + path);
		log.log(LogStatus.FAIL, result.getName() + " test Case Failed due to : " + result.getThrowable(),
				log.addScreenCapture(path));
	}

	public void onTestSkipped(ITestResult result) {
		log.log(LogStatus.SKIP, result.getName() + " test case skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Nothing

	}

	public void onStart(ITestContext context) {
		// Nothing
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
