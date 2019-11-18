package com.epam.AmazonTest.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxBrowser implements BaseBrowser {

	public WebDriver initDriver() {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	}

}
