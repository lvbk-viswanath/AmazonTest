package com.epam.AmazonTest.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page {

	private final Logger logger = Logger.getLogger(LoginPage.class);

	private static final String LOGIN_URL = pageURLProp.getProperty("loginurl");

	private WebElement userIdInputElement;
	private WebElement userPasswordInputElement;
	private WebElement incorrectDialogBoxUserId;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public LoginPage openPage() {
		try {
			if (!driver.getCurrentUrl().contains(LOGIN_URL))
				throw new PageNotFoundException("Login Page not found " + LOGIN_URL);
		} catch (PageNotFoundException e) {
			logger.error(e);
		}
		return this;
	}

	private void clearUserIdInput() {
		userIdInputElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	private void clearUserPasswordInput() {
		userPasswordInputElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	private void clickOnSubmitButton() {
		WebElement userSubmitElement = getElement(xPathProp.getProperty("usersubmitbutton"), logger, 3);
		userSubmitElement.click();
	}

	private void setUserIdElement() {
		userIdInputElement = getElement(xPathProp.getProperty("useridinputbox"), logger, 8);
	}

	private void setUserPasswordElement() {
		userPasswordInputElement = getElement(xPathProp.getProperty("userpasswordinputbox"), logger, 8);
	}

	private void setIncorrectUserIdDialogBox() {
		incorrectDialogBoxUserId = getElement(xPathProp.getProperty("incorrectdialogboxuserid"), logger, 4);
	}

	public boolean isUserIdPresent() {
		setUserIdElement();
		return isValuePresent(userIdInputElement);
	}

	public boolean isPasswordPresent() {
		setUserPasswordElement();
		return isValuePresent(userPasswordInputElement);
	}

	public boolean checkUserId(String userId) {
		if (isUserIdPresent()) {
			waitForPageToLoad();
			clearUserIdInput();
			userIdInputElement.sendKeys(userId);
			clickOnSubmitButton();
			setIncorrectUserIdDialogBox();
		}
		return !isValuePresent(incorrectDialogBoxUserId);
	}

	public boolean checkPassword(String password) {
		if (isPasswordPresent()) {
			waitForPageToLoad();
			clearUserPasswordInput();
			userPasswordInputElement.sendKeys(password);
			clickOnSubmitButton();
		}
		return !driver.getCurrentUrl().contains(LOGIN_URL);
	}

}
