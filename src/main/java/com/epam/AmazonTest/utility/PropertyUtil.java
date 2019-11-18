package com.epam.AmazonTest.utility;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class PropertyUtil {

	private static final Logger logger = Logger.getLogger(PropertyUtil.class);
	private static final String ERROR_MSG = "In PropertyUtil class ";
	private static Properties urlprop = null;
	private static Properties xpathprop = null;

	private PropertyUtil() {

	}

	public static Properties getXPathPropertyObject() {
		if (xpathprop == null) {
			try (InputStream input = new FileInputStream("src/test/resources/xpath.properties")) {
				xpathprop = new Properties();
				xpathprop.load(input);
			} catch (IOException e) {
				logger.error(ERROR_MSG + e);
			}
		}
		return xpathprop;
	}

	public static Properties getURLPropertyObject() {
		if (urlprop == null) {
			try (InputStream input = new FileInputStream("src/test/resources/pageurl.properties")) {
				urlprop = new Properties();
				urlprop.load(input);
			} catch (IOException e) {
				logger.error(ERROR_MSG + e);
			}
		}
		return urlprop;
	}

}
