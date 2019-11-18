package com.epam.AmazonTest.pages;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ElectricityPage extends Page {

	Logger logger = Logger.getLogger(ElectricityPage.class);

	public ElectricityPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	private WebElement selectState;
	private WebElement selectBoard;

	@Override
	public Page openPage() {
		try {
			if (!driver.getCurrentUrl().contains(pageURLProp.getProperty("electricityurl")))
				throw new PageNotFoundException(
						"Electricity Page not found " + pageURLProp.getProperty("electricityurl"));
		} catch (PageNotFoundException e) {
			logger.error(e);
		}
		return this;
	}

	public void selectState(String state) {
		selectState = getElement(xPathProp.getProperty("selectstate"), logger, 10);
		if (isValuePresent(selectState)) {
			waitForElementToBeClickable(selectState);
			selectOption(selectState, state);
		}
		waitForPageToLoad();
	}

	public void selectBoard(String board) {
		selectBoard = getElement(xPathProp.getProperty("selectboard"), logger, 10);
		if (isValuePresent(selectBoard)) {
			waitForElementToBeClickable(selectBoard);
			selectOption(selectBoard, board);
		}
		waitForPageToLoad();
	}

	public String getSelectedState() {
		if (isValuePresent(selectState))
			return new Select(selectState).getFirstSelectedOption().getText();
		return "";
	}

	public String getSelectedBoard() {
		if (isValuePresent(selectBoard))
			return new Select(selectBoard).getFirstSelectedOption().getText();
		return "";
	}

	public void enterCustomerNo(String customerNumber) {
		WebElement customerNo = getElement(xPathProp.getProperty("consumerno"), logger, 10);
		if (isValuePresent(customerNo)) {
			waitForElement(customerNo);
			writeText(customerNo, customerNumber);
			waitSeconds(5);
		}
		waitForPageToLoad();
	}

	public void fetchBill() {
		WebElement fetchBill = getElement(xPathProp.getProperty("fetchbill"), logger, 10);
		if (isValuePresent(fetchBill))
			click(fetchBill);
		waitForPageToLoad();
		waitSeconds(5);
	}

	public boolean getBillVisibility() {
		WebElement billDivElement = getElement(xPathProp.getProperty("billdiv"), logger, 10);
		return isValuePresent(billDivElement);
	}

}
