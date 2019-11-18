package com.epam.AmazonTest.pages;

public class PageNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	PageNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
