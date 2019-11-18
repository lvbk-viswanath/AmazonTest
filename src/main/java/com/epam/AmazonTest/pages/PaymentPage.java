package com.epam.AmazonTest.pages;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends Page {

	private static final Logger logger = Logger.getLogger(PaymentPage.class);

	public PaymentPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@Override
	public PaymentPage openPage() {
		try {
			if (!driver.getCurrentUrl().contains(pageURLProp.getProperty("paymenturl")))
				throw new PageNotFoundException("Payment Page not found " + pageURLProp.getProperty("paymenturl"));
		} catch (PageNotFoundException e) {
			logger.error(e);
		}
		return this;
	}

}
