package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Errors.AppError;
import com.qa.opencart.Exceptions.BrowserException;
import com.qa.opencart.Exceptions.FrameWorkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static  String highlight;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This is used to initialize driver based on passed Browser Name
	 * @param browserName
	 * @return 
	 */
	public WebDriver initDriver(Properties prop) {
		//Cross Browser logic
		String browserName = prop.getProperty("browser");		
		System.out.println("browser name is :" + browserName);
		
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

	
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
//			driver = new ChromeDriver();
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
			
		case "firefox":
//			driver = new FirefoxDriver();
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

			break;
			
		case "edge":
//			driver = new EdgeDriver();
			tldriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));

			break;

		default:
			System.out.println("please pass valid browser name , i.e");
			throw new BrowserException(AppError.BROSWER_NOT_FOUND);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	
	
	/**
	 * get local thread copy of the driver	
	 * @return
	 */
	public static WebDriver getDriver() {
		return tldriver.get();
	}
	
	
	
	/**
	 * This method is used to initialize the properties from the .properties file
	 * this return properties(prop)
	 * @return
	 */
	public Properties initProp() {
		//cross Property logic
		
		//mvn clean install -Denv="qa"
		prop = new Properties();
		FileInputStream ip =null;
		String envName=System.getProperty("env");
		System.out.println("Running test suite on "+envName+" "+"environment");
		
		if(envName==null) {
			System.out.println("Env Name is not given , hence running it on QA env");
			 try {
				ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
		
				
		try {
		switch (envName.trim().toLowerCase()) {
		case "qa":
			 ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			break;
		case "stage":
			 ip = new FileInputStream(AppConstants.CONFIG_STG_FILE_PATH);
			break;
		case "prod":
			 ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			break;


		default:
			System.out.println("Please pass right env name");
			throw new FrameWorkException("==========WRONG ENV PASSED==========");
		}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		}

		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;	
	}
	
	
public static String getScreenshot(String methodName) {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp location
		
		String path = System.getProperty("user.dir")+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
		
		File destination = new File(path);
		
		try {
			org.openqa.selenium.io.FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}

}
