package com.qa.opencart.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	protected LoginPage loginPage;
	protected Properties prop;
	protected AccountsPage accPage;
	protected SearchResultPage searchresultPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;;
	protected RegistrationPage regPage;

	@Description("Initialsing the browser..")
	@Step("Setup for test , initializing browser:{0}")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(@Optional("chrome") String browserName) {
		df=new DriverFactory();
		prop = df.initProp();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}

		driver=df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
		
	}
	
	@Description("Closing the browser..")
	@Step("Closing browser")
	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
