package com.epam.AmazonTest.pages;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.PageFactory;

public class MobileRechargePage extends Page {

	private static final Logger logger = Logger.getLogger(MobileRechargePage.class);

	public MobileRechargePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@Override
	public MobileRechargePage openPage() {
		try {
			if (!driver.getCurrentUrl().contains(pageURLProp.getProperty("mobilerechargeurl")))
				throw new PageNotFoundException(
						"Mobile Recharge Page not found " + pageURLProp.getProperty("mobilerechargeurl"));
		} catch (PageNotFoundException e) {
			logger.error(e);
		}
		return this;

	}

	private void clickOnViewPlanLink() {
		WebElement viewPlanLink = getElement(xPathProp.getProperty("viewplanlink"), logger, 4);
		if (isValuePresent(viewPlanLink)) {
			new Actions(driver).moveToElement(viewPlanLink).build().perform();
			click(viewPlanLink);
		}
	}

	private void clickOnPlanValueButton() {
		WebElement planValueButton = getElement(xPathProp.getProperty("planvaluebutton"), logger, 4);
		if (isValuePresent(planValueButton))
			click(planValueButton);
	}

	public void enterMobileNo(String mobileNumber) {
		WebElement mobileNoField = getElement(xPathProp.getProperty("mobilenofield"), logger, 4);
		if (isValuePresent(mobileNoField))
			writeText(mobileNoField, "" + mobileNumber);
	}

	public String getOperatorAndCircle() {
		WebElement operatorAndCircleField = getElement(xPathProp.getProperty("operatorcircle"), logger, 4);
		if (isValuePresent(operatorAndCircleField))
			return readText(operatorAndCircleField);
		return "";
	}

	public void setPlan(int planValue) {
		WebElement amountField = getElement(xPathProp.getProperty("amountfield"), logger, 4);
		if (isValuePresent(amountField))
			writeText(amountField, "" + planValue);
	}

	public LoginPage buyPlan() {
		waitSeconds(5);
		WebElement payButton = getElement(xPathProp.getProperty("paybutton"), logger, 4);
		if (isValuePresent(payButton)) {
			payButton.submit();
		}
		waitSeconds(5);
		waitForPageToLoad();
		return new LoginPage(this.driver);
	}

	public void selectPlan() {
		clickOnViewPlanLink();
		PageFactory.initElements(driver, this);
		waitSeconds(3);
		clickOnPlanValueButton();
		waitSeconds(3);
		PageFactory.initElements(driver, this);
	}

}
