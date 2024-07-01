package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.Utils.TimeUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eutil;
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eutil = new ElementUtil(driver);
	}
	
	private By logoutlink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchicon = By.xpath("//span/button");
	
	/**
	 * 
	 * @return
	 */
	public String getAccountPageTitle() {
		String title = eutil.waitforTitletoBe(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("Account page title is : " + title);
		return title;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getAccountPageURL() {
		String URL = eutil.waitforURLContains(AppConstants.ACCOUNT_PAGE_FRACION_URL,TimeUtil.DEFAULT_TIME );
		System.out.println("Account page URL is : " + URL);
		return URL;
	}
	
	
	public boolean isLogoutLinkDisp() {
		return eutil.doIsDisplayed(logoutlink);
}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getAccountPageHeaders() {
		
		List<WebElement> headerList =eutil.waitforPresenceofElementsLocated(headers,TimeUtil.DEFAULT_TIME);
		List<String> HList = new ArrayList<String>();
		for(WebElement e : headerList ) {
			String Htext = e.getText();
			HList.add(Htext);
		}
		return HList;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean searchExist() {
		return eutil.doIsDisplayed(search);
}
	
	/**
	 * 
	 * @param searchKey
	 * @return
	 */
	public SearchResultPage doSearch(String searchKey) {
		System.out.println("Searching for product"+" "+searchKey);
		if(searchExist()) {
			
			eutil.doSendKeys(search,searchKey);
			eutil.doClick(searchicon);
			return new SearchResultPage(driver);
		}
		else {
			System.out.println("Search field is not present");
			return null;
		}
	}
}

