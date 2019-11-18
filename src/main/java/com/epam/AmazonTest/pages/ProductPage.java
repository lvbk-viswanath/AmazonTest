package com.epam.AmazonTest.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;

public class ProductPage extends Page {

	private static final Logger logger = Logger.getLogger(ProductPage.class);

	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean checkAvailability() {
		boolean availabilityStatus = false;
		WebElement availabilityOfProduct = getElement(xPathProp.getProperty("stockavailabilty"), logger, 4);
		if (isValuePresent(availabilityOfProduct))
			availabilityStatus = availabilityOfProduct.getText().contains("In stock.");
		return availabilityStatus;
	}

	public void addToCart() {
		WebElement addToCartButton = getElement(xPathProp.getProperty("addtocartbutton"), logger, 4);
		if (isValuePresent(addToCartButton))
			click(addToCartButton);
		waitForPageToLoad();
	}

	public boolean getCartStatus() {
		WebElement cartStatusElement = getElement(xPathProp.getProperty("cartvalidation"), logger, 10);
		return isValuePresent(cartStatusElement);
	}

	private void askQuestion(String que) {
		WebElement queInputBox = getElement(xPathProp.getProperty("haveaquestion"), logger, 4);
		writeText(queInputBox, que);
	}

	public boolean getAnswer(String que) {
		JavascriptExecutor js = (JavascriptExecutor) this.driver;
		askQuestion(que);
		List<WebElement> answerBox = getElements(xPathProp.getProperty("queryanswers"), logger, 4);
		js.executeScript("window.scrollBy(0,900)");
		StringBuilder data = new StringBuilder("");
		for (WebElement webElement : answerBox)
			data.append(webElement.getText());
		logger.info("information : " + data.toString());
		return data.toString().contains(que);
	}

	public LoginPage askToCommunity() {
		WebElement askThecommunity = getElement(xPathProp.getProperty("askthecommunity"), logger, 4);
		click(askThecommunity);
		WebElement postQuestion = getElement(xPathProp.getProperty("postcommunity"), logger, 4);
		click(postQuestion);
		waitSeconds(3);
		click(postQuestion);
		waitForPageToLoad();
		return new LoginPage(this.driver);
	}

	public boolean validatePost() {
		waitForPageToLoad();
		WebElement postvalidate = getElement(xPathProp.getProperty("validatepost"), logger, 6);
		return isValuePresent(postvalidate);
	}

	@Override
	public ProductPage openPage() {
		try {
			if (!driver.getCurrentUrl().contains(pageURLProp.getProperty("basepageurl")))
				throw new PageNotFoundException("Product Page not found " + pageURLProp.getProperty("basepageurl"));
		} catch (PageNotFoundException e) {
			logger.error(e);
		}
		return this;
	}
}
