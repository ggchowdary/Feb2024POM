package com.qa.opencart.Tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Errors.AppError;
import com.qa.opencart.Listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic:100 - Design OpenCart application")
@Story("Story :1001 - Design Login Page ")
@Listeners({TestAllureListener.class})
public class LoginPageTest extends BaseTest {
	
	@Feature("Login")
	@Description("checking login page title test...---")
	@Severity(SeverityLevel.MINOR)
	@Owner("GG")
	@Issue("LOG-11")
	@Test(priority=1)
	public void loginPageTitleTest() {
	
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle,AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
	
	@Description("checking login page URL test...")
	@Severity(SeverityLevel.NORMAL)
	@Owner("GG")
	@Issue("LOG-11")
	@Test
	(priority=2)
	public void loginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_FRACION_URL));
	}
	
	@Description("checking forgot password linke exists test...")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("GG")
	@Issue("LOG-11")
	@Test(priority=3)
	public void forgotpswdexistTest() {
		Assert.assertTrue(loginPage.checkForgotPasswordLink(),AppError.ELEMENT_NOT_FOUND);
	}
	
	@Description("checking login test...")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("GG")
	@Issue("LOG-11")
	@Test(priority=4)
	public void LoginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
		
	}
}

