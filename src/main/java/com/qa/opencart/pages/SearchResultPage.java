package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.Utils.TimeUtil;

public class SearchResultPage {

private WebDriver driver;
private ElementUtil eutil;
private By searchResult = By.cssSelector("div.product-thumb");

	
	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		eutil = new ElementUtil(driver);
	}
	
	public int getSearchResultCount() {
		List<WebElement> resultList = eutil.waitforVisibiltyofElementsLocated(searchResult, TimeUtil.DEFAULT_TIME);
		int resultCount = resultList.size();
		System.out.println("Product Search count is :"+" "+resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String produtName) {
		eutil.doClick(By.linkText(produtName),TimeUtil.DEFAULT_TIME);
		return new ProductInfoPage(driver);
	}
	
}
