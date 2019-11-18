package com.epam.AmazonTest.pages;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {
	private final Logger logger = Logger.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	@Override
	public HomePage openPage() {
		try {
			if (!driver.getCurrentUrl().contains(pageURLProp.getProperty("basepageurl")))
				throw new PageNotFoundException("Home Page not found " + pageURLProp.getProperty("basepageurl"));
		} catch (PageNotFoundException e) {
			logger.error(e);
		}
		return this;
	}

	public LoginPage goToLoginPage() {
		WebElement signInButton = getElement(xPathProp.getProperty("signinbutton"), logger, 3);
		if (isValuePresent(signInButton))
			signInButton.click();
		waitForPageToLoad();
		return new LoginPage(driver);
	}

	public SearchResultPage searchForProduct(String productName) {
		WebElement searchBox = getElement(xPathProp.getProperty("searchbox"), logger, 3);
		if (isValuePresent(searchBox))
			writeText(searchBox, productName);
		WebElement searchGoButton = getElement(xPathProp.getProperty("searchgobutton"), logger, 3);
		if (isValuePresent(searchGoButton))
			click(searchGoButton);
		waitForPageToLoad();
		return new SearchResultPage(this.driver);
	}

	public AmazonPayPage goToAmazonPayPage() {
		WebElement amazonPayButton = getElement(xPathProp.getProperty("amazonpay"), logger, 3);
		if (isValuePresent(amazonPayButton))
			click(amazonPayButton);
		waitForPageToLoad();
		waitSeconds(5);
		return new AmazonPayPage(this.driver);
	}

}
