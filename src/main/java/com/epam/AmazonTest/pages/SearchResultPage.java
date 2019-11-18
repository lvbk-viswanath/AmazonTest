package com.epam.AmazonTest.pages;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage extends Page {

	private static final Logger logger = Logger.getLogger(SearchResultPage.class);

	public SearchResultPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public ProductPage selectProduct(int index) {
		List<WebElement> searchResult = getElements(xPathProp.getProperty("searchresults"), logger, 4);
		if (!searchResult.isEmpty()) {
			click(searchResult.get(index));
			waitSeconds(2);
		}
		waitForPageToLoad();
		for (String Child_Window : driver.getWindowHandles())
			driver.switchTo().window(Child_Window);
		waitSeconds(2);
		waitForPageToLoad();
		return new ProductPage(this.driver);
	}

	@Override
	public SearchResultPage openPage() {
		try {
			if (!driver.getCurrentUrl().contains(pageURLProp.getProperty("basepageurl")))
				throw new PageNotFoundException(
						"SearchResult Page not found " + pageURLProp.getProperty("basepageurl"));
		} catch (PageNotFoundException e) {
			logger.error(e);
		}
		return this;
	}
}
