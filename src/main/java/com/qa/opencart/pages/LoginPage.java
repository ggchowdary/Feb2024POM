package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.Utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {
		
		private WebDriver driver;
		private ElementUtil eutil;
	//1. Page Objects : By Locators(should be private)
		
		private By email = By.id("input-email");
		private By password = By.id("input-password");
		private By loginBtn = By.xpath("//div/following-sibling::input[@value='Login']");
		private By forgotpass = By.linkText("Forgotten Password");
		private By registerLink= By.linkText("Register");
	
	//2. public constructor of page
		
		public LoginPage(WebDriver driver) {
			this.driver=driver;
			eutil = new ElementUtil(driver);
		}
		
		 
	//3. public Page actions/methods
		
		@Step("Getting Login Page Title")
		public String getLoginPageTitle() {
			
			String title = eutil.waitforTitletoBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
			System.out.println("login page title is : " + title);
			return title;
		}
		
		@Step("Getting Login Page URL")
		public String getLoginPageURL() {
			
			String URL = eutil.waitforURLContains(AppConstants.LOGIN_PAGE_FRACION_URL,TimeUtil.DEFAULT_TIME );
			System.out.println("login page URL is : " + URL);
			return URL;
		}
		
		@Step("Getting state of Forgot Password Link")
		public boolean checkForgotPasswordLink() {
			
			return eutil.doIsDisplayed(forgotpass);
		}
		
		@Step("Logging into account with Username:{0} and Password{1}")
		public AccountsPage doLogin(String username , String pswd) {
			
			eutil.dosendKeys(email,username,TimeUtil.DEFAULT_MEDIUM_TIME);
			eutil.doSendKeys(password, pswd);
			eutil.doClick(loginBtn);			
			return new AccountsPage(driver);
		}
		
		@Step("Navigating to Register Page")
		public RegistrationPage navigatetoRegisterPage() {
			eutil.doClick(registerLink, 10);
			return new RegistrationPage(driver);
		}

	
	

}
