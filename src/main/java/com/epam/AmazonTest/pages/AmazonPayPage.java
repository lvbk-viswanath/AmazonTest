package com.epam.AmazonTest.pages;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AmazonPayPage extends Page {

	private static final Logger logger = Logger.getLogger(AmazonPayPage.class);

	public AmazonPayPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	public MobileRechargePage goToMobileRechargePage() {
		WebElement mobileRecharge = getElement(xPathProp.getProperty("mobilerecharge"), logger, 4);
		if (isValuePresent(mobileRecharge))
			click(mobileRecharge);
		waitForPageToLoad();
		return new MobileRechargePage(this.driver);
	}

	public ElectricityPage goToElectricityPge() {
		WebElement electricity = getElement(xPathProp.getProperty("electricity"), logger, 4);
		if (isValuePresent(electricity))
			click(electricity);
		waitForPageToLoad();
		return new ElectricityPage(this.driver);
	}

	@Override
	public AmazonPayPage openPage(){
		try {
			if (!driver.getCurrentUrl().contains(pageURLProp.getProperty("amazonpayurl")))
				throw new PageNotFoundException("Amazon Pay Page not found " + pageURLProp.getProperty("amazonpayurl"));
		} catch (PageNotFoundException e) {
			logger.error(e);
		}
		return this;
	}

}
