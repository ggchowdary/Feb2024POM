package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.Utils.TimeUtil;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}
	
	
	public boolean userRegister(String firstName, String lastName, String email, String telephone, String password,String subscribe) {
	
		eutil.dosendKeys(this.firstName,firstName,TimeUtil.DEFAULT_MEDIUM_TIME);
		eutil.doSendKeys(this.lastName, lastName);
		eutil.doSendKeys(this.email, email);
		eutil.doSendKeys(this.telephone, telephone);
		eutil.doSendKeys(this.password, password);
		eutil.doSendKeys(this.confirmpassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eutil.doClick(subscribeYes);
		}
		else {
			eutil.doClick(subscribeNo);
		}
		
		eutil.doClick(agreeCheckBox);
		eutil.doClick(continueButton);
		
		String successMesg = eutil.waitforElementVisibility(successMessg, TimeUtil.DEFAULT_TIME).getText();
		System.out.println(successMesg);	
		
		if(successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MSG)) {
			eutil.doClick(logoutLink);
			eutil.doClick(registerLink);
			return true;}
		else {
			return false;
		}
	}
}


