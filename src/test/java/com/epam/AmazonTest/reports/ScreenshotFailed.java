package com.epam.AmazonTest.reports;

import java.io.File;

import java.io.IOException;
import org.apache.log4j.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotFailed {

	private static final Logger logger = Logger.getLogger(ScreenshotFailed.class);

	private ScreenshotFailed() {
	}

	public static String getScreenshot(WebDriver driver, String testmethodname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] srcZipFileBytes = ts.getScreenshotAs(OutputType.BYTES);
		String path = System.getProperty("user.dir") + "/reports/" + testmethodname + System.currentTimeMillis()
				+ ".png";
		File destination = new File(path);
		try {
			FileUtils.writeByteArrayToFile(destination, srcZipFileBytes);

		} catch (IOException e) {
			logger.error("In ScreenshotFailed class, Capture Failed " + e.getMessage());
		}
		return path;
	}

}
