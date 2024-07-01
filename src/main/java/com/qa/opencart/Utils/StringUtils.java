package com.qa.opencart.Utils;

public class StringUtils {
	
	public static String getRandomEmailID() {
		String emailId = "userauto"+System.currentTimeMillis()+"@opencart.com";
		System.out.println("USer email id is :" +" "+emailId);
		return emailId;
	}

}

