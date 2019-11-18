package com.epam.AmazonTest.pages;

import java.util.Collections;

import java.util.List;

import java.util.Optional;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.AmazonTest.utility.PropertyUtil;

public abstract class Page {

	protected WebDriver driver = null;

	private WebDriverWait wait = null;

	protected abstract Page openPage();

	protected static Properties pageURLProp = PropertyUtil.getURLPropertyObject();

	protected static Properties xPathProp = PropertyUtil.getXPathPropertyObject();

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	protected WebElement getElement(String xpathname, Logger logger, int waitTime) {
		try {
			waitForElement(xpathname, waitTime);
			return driver.findElement(By.xpath(xpathname));
		} catch (NoSuchElementException | TimeoutException e) {
			logger.error("Required Element is not found : " + e);
		}
		return null;
	}

	protected List<WebElement> getElements(String xpathname, Logger logger, int waitTime) {
		try {
			waitForElement(xpathname, waitTime);
			return driver.findElements(By.xpath(xpathname));
		} catch (NoSuchElementException | TimeoutException e) {
			logger.error("Required Element is not found : " + e);
		}
		return Collections.emptyList();
	}

	protected void waitForElement(String elementXPath, int waitTime) {
		wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
	}

	protected void waitForPageToLoad() {
		wait = new WebDriverWait(driver, 15);
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
	}

	protected boolean isValuePresent(WebElement element) {
		Optional<WebElement> checkNull = Optional.ofNullable(element);
		return checkNull.isPresent();
	}

	public void waitSeconds(int seconds) {
		JavascriptExecutor jsexcecutor = (JavascriptExecutor) driver;
		String query = "window.setTimeout(arguments[arguments.length - 1],";
		jsexcecutor.executeAsyncScript(query + seconds + ");");
	}

	public void writeText(WebElement webElement, String text) {
		waitForPageToLoad();
		if (webElement.isDisplayed()) {
			webElement.sendKeys(text);
			waitSeconds(11);
		} else {
			throw new ElementNotVisibleException("WebElement is not visible for write text");
		}
		waitForPageToLoad();
	}

	public String readText(WebElement webElement) {
		waitForElement(webElement);
		if (webElement.isDisplayed()) {
			return webElement.getText();
		} else {
			throw new ElementNotVisibleException("WebElement is not visible for reading text");
		}
	}

	public void click(WebElement webElement) {
		waitForElementToBeClickable(webElement);
		if (webElement.isDisplayed()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", webElement);
		} else {
			throw new ElementNotVisibleException("WebElement is not visible for click");
		}
		waitForPageToLoad();
	}

	public void selectOption(WebElement webElement, String optionToBeSelected) {
		List<WebElement> options = new Select(webElement).getOptions();

		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(optionToBeSelected)) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + webElement.getLocation().x + ")");
				option.click();
				break;
			}

		}
		waitForPageToLoad();
	}

	public void waitForElement(WebElement webElement) {
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void waitForElementToBeClickable(WebElement webElement) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

}
