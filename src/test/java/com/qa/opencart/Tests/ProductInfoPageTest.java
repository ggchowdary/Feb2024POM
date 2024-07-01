package com.qa.opencart.Tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Errors.AppError;
import com.qa.opencart.Utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
			{"canon","Canon EOS 5D"}
		};
	}
	
	@DataProvider
	public Object[][] getProductImageData() {
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7},
			{"canon","Canon EOS 5D",3}
		};
	}
	
	@DataProvider
	public Object[][] getProductImageDataSheet() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
	}
	
	@Test(dataProvider="getProductData")
	public void productHeaderTest(String searchKey , String ProductName) {
		searchresultPage=accPage.doSearch(searchKey);
		productInfoPage=searchresultPage.selectProduct(ProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(),ProductName,AppError.HEADER_NOT_FOUND);
	}
	
	@Test(dataProvider="getProductImageDataSheet")
	public void productImageTest(String searchKey , String ProductName, String imageCount) {
		searchresultPage=accPage.doSearch(searchKey);
		productInfoPage=searchresultPage.selectProduct(ProductName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(),Integer.parseInt(imageCount),AppError.IMAGE_COUNT_MISMATCH);
	}
	
	// Test with multiple assertions
	//Hard Assert(Static) and SoftAssert(Non-static) --> create object for SoftAssert
	@Test
	/**
	 * 
	 */
	public void productInfoTest() {
		searchresultPage=accPage.doSearch("macbook");
		productInfoPage=searchresultPage.selectProduct("MacBook Pro");
		Map<String, String> productInfoMap = productInfoPage.getProductInfoMap();
		System.out.println("==============Product information============");
		System.out.println(productInfoMap);
		softAssert.assertEquals(productInfoMap.get("productname"),"MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(productInfoMap.get("Reward Points"),"800");
		softAssert.assertEquals(productInfoMap.get("Availability"),"In Stock");
		softAssert.assertEquals(productInfoMap.get("productprice"),"$2,000.00");
		softAssert.assertEquals(productInfoMap.get("exTaxPrice"),"$2,000.00");
		
		softAssert.assertAll();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
