package com.epam.AmazonTest.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeBrowser implements BaseBrowser {

	public WebDriver initDriver() {
		WebDriverManager.chromedriver().version("78.0.3904.105").setup();
		return new ChromeDriver();
	}

}
