package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.Utils.TimeUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	private By productHeader = By.cssSelector("div#content h1");
	private By productImageCount = By.xpath("//li/a[@class='thumbnail']");
	private By productQuantity = By.cssSelector("input#input-quantity");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private Map<String, String> productMap;
	private By addtocart = By.linkText("Add to Cart");
	private By shoppingcart = By.linkText("Shopping Cart");


	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eutil = new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		String header = eutil.doGetText(productHeader);
		System.out.println("Product header is : "+" "+header);
		return header;
	}
	
	public int getProductImagesCount() {
		int imageCount = eutil.waitforVisibiltyofElementsLocated(productImageCount, TimeUtil.DEFAULT_MEDIUM_TIME).size();
		System.out.println("Total Image Count is:" + " "+imageCount);
		return imageCount;
	}
	
	public Map<String, String> getProductInfoMap() {
		productMap = new HashMap<String,String>();
		productMap.put("productname",getProductHeader());
		productMap.put("productimagescount",String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaList = eutil.getElements(productMetaData);
		for(WebElement e:metaList) {
			String metaData = e.getText();
			String[] meta = metaData.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
			}
	}
	
	private void getProductPriceData() {
		List<WebElement> priceList = eutil.getElements(productPriceData);
		String productPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productprice", productPrice);
		productMap.put("exTaxPrice", exTaxPrice);	
	}
	
	
	
	
	
	
	public ShoppingCartPage doAddtoCart(String quantity) {
		eutil.doSendKeys(productQuantity, quantity);
		eutil.doClick(addtocart);
		eutil.doClick(shoppingcart);
		return new ShoppingCartPage(driver);
	}
}
