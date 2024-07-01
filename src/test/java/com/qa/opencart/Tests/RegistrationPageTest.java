package com.qa.opencart.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Errors.AppError;
import com.qa.opencart.Utils.CSVUtil;
import com.qa.opencart.Utils.ExcelUtil;
import com.qa.opencart.Utils.StringUtils;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void regSetup() {
		regPage=loginPage.navigatetoRegisterPage();
	}
//	@DataProvider
//	public Object[][] userRegTestData() {
//		return new Object[][] {
//			{"Teja","Test", "9123456788", "teja@98", "yes"},
//			{"Moyo","Test", "9123456787", "moyo@98", "no"},
//			{"Roy","Test", "9123456786", "roy@96", "yes"},
//		};
//		
//	}
	
	@DataProvider
	public Object[][] userRegTestDatafromSheet() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME.trim());
	}
	
	@DataProvider
	public Object[][] userRegTestDatafromCSV() {
		return CSVUtil.csvData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	
	@Test(dataProvider="userRegTestDatafromCSV")
	public void userRegistrationTest(String fname,String lname,String phone,String pass,String subscribe) {
		
		Assert.assertTrue(regPage.userRegister(fname, lname, StringUtils.getRandomEmailID(), phone, pass, subscribe),
				AppError.USER_REG_NOT_DONE);
	}
	

}
