package com.qa.opencart.Tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Errors.AppError;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void AccountPageTitleTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(),AppConstants.ACCOUNT_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
	
	@Test
	public void AccountPageURLTest() {
		Assert.assertTrue(accPage.getAccountPageURL().contains(AppConstants.ACCOUNT_PAGE_FRACION_URL),AppError.URL_NOT_FOUND);
	}
	
	@Test
	public void AccountPageHeadersTest() {
		List<String> accPageHeaderList = accPage.getAccountPageHeaders();
		Assert.assertEquals(accPageHeaderList,AppConstants.ACCOUNT_PAGE_HEADER_LIST,AppError.HEADER_LIST_NOT_MATCHED);
	}
		
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2},
			{"airtel",0}
		};
	}
	@Test(dataProvider="getSearchData")
	public void searchTest(String searckKey , int searchCount) {
		searchresultPage=accPage.doSearch(searckKey);
		Assert.assertEquals(searchresultPage.getSearchResultCount(),searchCount,AppError.RESULT_COUNT_MISMATCH);
	}
	
}
