package com.qa.opencart.Constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String LOGIN_PAGE_FRACION_URL = "route=account/login";
	public static final String ACCOUNT_PAGE_FRACION_URL = "route=account/account";

	public static final String CONFIG_FILE_PATH = "./src/test/Resources/Config/Prod.config.properties";
	public static final String CONFIG_QA_FILE_PATH = "./src/test/Resources/Config/config.qa.properties";
	public static final String CONFIG_STG_FILE_PATH = "./src/test/Resources/Config/stg.qa.properties";

	public static final List<String> ACCOUNT_PAGE_HEADER_LIST = Arrays.asList("My Account" , "My Orders" , "My Affiliate Account", "Newsletter");
	public static final String USER_REGISTER_SUCCESS_MSG = "Your Account Has Been Created";

//********************************************************************************
	
	public static final String REGISTER_SHEET_NAME = "Register";
	public static final String PRODUCT_SHEET_NAME = "Product";

	
	
}
