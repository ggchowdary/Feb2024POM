package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementUtil;

public class ShoppingCartPage {
	private WebDriver driver;
	private ElementUtil eutil;
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver=driver;
		eutil = new ElementUtil(driver);
	}

}
