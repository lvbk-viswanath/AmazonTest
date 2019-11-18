package com.epam.AmazonTest.driver;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class HeadlessBrowser implements BaseBrowser {

	public WebDriver initDriver() {
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		firefoxBinary.addCommandLineOptions("--headless");
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setBinary(firefoxBinary);
		return new FirefoxDriver(firefoxOptions);
	}

}
