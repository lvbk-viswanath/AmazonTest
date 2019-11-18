package com.epam.AmazonTest.reports;

import java.io.IOException;


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
	}

	public void onTestSuccess(ITestResult result) {
		log.log(LogStatus.PASS, result.getName() + " test case Passed");
	}

	public void onTestFailure(ITestResult result) {
		String path = null;
		Object testClass = result.getInstance();
		WebDriver webDriver = ((TestBase) testClass).driver;
		try {
			path = ScreenshotFailed.getScreenshot(webDriver, result.getName());
		} catch (IOException e) {
			logger.error("In Report Listener " + e);
		}
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
