package com.qa.opencart.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.Exceptions.ElementException;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		jsUtil = new JavaScriptUtil(driver);
	}
	
	private void nullCheck(String value) {
		if(value==null) {
			throw new ElementException("Value is null:" + value );	
		}
	}
	
	private void highlightelement(WebElement element) {
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
	}
	
	public void doSendKeys (By locator , String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	
	public void doSendKeys (By locator , CharSequence[] value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	public void dosendKeys(By locator , String value,int timeOut) {
		waitforElementVisibility(locator,timeOut).clear();
		waitforElementVisibility(locator,timeOut).sendKeys(value);
	}
	
	@Step("Finding element using locator:{0}")
	public WebElement getElement(By locator) {
		try {
		WebElement element = driver.findElement(locator);
		highlightelement(element);
		return element;
	}
		catch(NoSuchElementException e) {
			System.out.println("Element not present on page	:" + locator);
			e.printStackTrace();
			return null;
		}
		
}
	public void doClick(By locator) {
		getElement(locator).click();
	}
	public void doClick(By locator , int timeOut) {
		waitforElementVisibility(locator,timeOut).click();
	}
	
	public String doGetText(By locator) {
	return getElement(locator).getText();
	}
	
	@Step("Checking the state of locator:{0}")
	public boolean doIsDisplayed(By locator) {
		try {
		boolean flag = getElement(locator).isDisplayed();
		return flag;
		}
		catch(NoSuchElementException e) {
			System.out.println("element with locator:"+locator+"is not displayed");
			return false;}
		
		}
	public boolean isElementDisplayed(By locator) {
		int elementCount = getElements(locator).size();
		if(elementCount==1) {
			System.out.println("Single element is displayed :"+locator);
			return true;
		}else {
			System.out.println("Multiple or Zero elements displayed : "+ locator);
		return false; 
	}}
	public boolean isElementDisplayed(By locator,int expectedEleCount) {
		int elementCount = getElements(locator).size();
		if(elementCount==expectedEleCount) {
			System.out.println("Element is displayed"+" " +locator+"with occurance of"+expectedEleCount);
			return true;
		}else {
			System.out.println("Multiple or Zero elements displayed : "+ locator+"with occurance of"+expectedEleCount);
		return false; 
	}}
		
	
	public String doGetAttribute(By locator , String attrName) {
		return getElement(locator).getAttribute(attrName);
	}
	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	public  int getElementsCount(By locator) {
		return getElements(locator).size();
	}
	//WAF : 
	//name : getElementTextList
	//return : List<String>
	public  List<String> getElementTextList(By locator) {
		List<WebElement> eleList=getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		
		for(WebElement e: eleList) {
			String text = e.getText();
			if(text.length()!=0) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}
	public List<String> getElementAttributeList(By locator,String attrName) {
		List<String> attrList = new ArrayList<String>();
		List<WebElement> imglist = getElements(locator);
		for (WebElement e : imglist) {
			String attrVal = e.getAttribute(attrName);
			if(attrVal!=null&&attrVal.length()!=0) {
				attrList.add(attrVal);
				System.out.println(attrVal);
			}
	}
return attrList;
	}	
//-------------------------------------SelectDropdownUtils-----------------------------------

	public  void doSelectByValue(By locator , String value) {
		Select select=new Select(getElement(locator));
		select.selectByVisibleText(value);
		}
	
	public  void doSelectByIndex(By locator , int index) {
		Select select=new Select(getElement(locator));
		select.selectByIndex(index);}
		
		public  void doSelectByVisibleText(By locator , String VisibleText) {
			Select select=new Select(getElement(locator));
			select.selectByVisibleText(VisibleText);}
	
		public void getDropdownOptionCount(By locator) {
			Select select = new Select(driver.findElement(locator));
			select.getOptions().size();
			}
		
		public List<String> dogetOptionsTextList(By locator) {
			Select select = new Select(driver.findElement(locator));
			List<WebElement> optionList = select.getOptions();
			List<String> optionsText = new ArrayList<String>();
			for(WebElement e:optionList) {
				String text = e.getText();
				optionsText.add(text);
		}
			return optionsText;
		}
		public void SelectValuefromDropdown(By locator , String OptionText) {
			Select select = new Select(getElement(locator));
			List <WebElement> optionList = select.getOptions();
			for(WebElement e: optionList) {
				String text = e.getText();
				if(text.equals(OptionText.trim()));
				e.click();
				break;
			}		
			}
		public void SelectValuefromDropdownwithoutSelect(By locator , String optionText) {
			List<WebElement> optionsList = getElements	(locator);
			 for(WebElement e:optionsList) {
				 String text=e.getText();
				 if(text.equals("optionText")) {
					 e.click();
					 break;
				 }
			 }
			}		
		public void doSearch(By searchfield , String searchKey , By suggestions , String value) throws InterruptedException {
			doSendKeys(searchfield , searchKey);
			Thread.sleep(3000);
			List<WebElement> slist = getElements(suggestions);
			System.out.println(slist.size());
		
			for(WebElement e:slist) {
				String text = e.getText();
				System.out.println(text);
				if(text.contains(value)) {
					e.click();
					break;
				}
			}
			
			
		}
		
//		*******************-------------Action Class Utilities---------------------********************************
		public void handleParentSubMenu(By parentLocator , By subLocator) throws InterruptedException 
		{
			Actions act = new Actions(driver);
			act.moveToElement(getElement(parentLocator)).perform();
			Thread.sleep(2000);
			doClick(subLocator);
		}
		public void doDragandDrop(By sourceLocator , By targetLocator) {
		 	Actions act = new Actions(driver);
		 	act.dragAndDrop(getElement(sourceLocator),getElement(targetLocator)).perform();

		}
		
		public  void doActionSendKeys(By locator, String value) {
		 	Actions act = new Actions(driver);
		 	act.sendKeys(getElement(locator), value).perform();
		 	}
		 	
		 	public  void doActionsClick(By locator) {
			 	Actions act = new Actions(driver);
			 	act.click(getElement(locator)).perform();	
			 	}
		 	public  void doActionSendKeyswithPause(By locator , String value , long pausetime) {
				Actions act = new Actions(driver);
			 	char[] array = value.toCharArray();
			 	for (char c:array) {
				 	act.sendKeys(getElement(locator),String.valueOf(c)).pause(pausetime).perform();
			 	}}
				
			 	public  void doActionSendKeyswithPause(By locator , String value) {
					Actions act = new Actions(driver);
				 	char[] array = value.toCharArray();
				 	for (char c:array) {
					 	act.sendKeys(getElement(locator),String.valueOf(c)).pause(500).perform();
				 	}
			 	}
	
public  void level4MenuHandlingUsingClick(By label1 , String label2 , String label3 , String label4) throws InterruptedException {
	doClick(label1);
 	Thread.sleep(2000);
 	Actions act = new Actions(driver);
 	act.moveToElement(getElement(By.linkText(label2))).perform();
 	Thread.sleep(1000);
 	act.moveToElement(getElement(By.linkText(label3))).perform();
 	Thread.sleep(1000);
 	doClick(By.linkText(label4));
 	}
public  void level4MenuHandlingUsingClick(By label1 , By label2 , By label3 , By label4) throws InterruptedException {
	doClick(label1);
 	Thread.sleep(2000);
 	Actions act = new Actions(driver);
 	act.moveToElement(getElement(label2)).perform();
 	Thread.sleep(1000);
 	act.moveToElement(getElement(label3)).perform();
 	Thread.sleep(1000);
 	doClick(label4);
}
public  void level4MenuHandlingUsingMouseHover(By label1 , By label2 , By label3 , By label4) throws InterruptedException {
 	Actions act1 = new Actions(driver);
 	act1.moveToElement(getElement(label1)).perform();
 	Thread.sleep(1000);
 	act1.moveToElement(getElement(label2)).perform();
 	Thread.sleep(1000);
 	act1.moveToElement(getElement(label3)).perform();
 	Thread.sleep(1000);
 	doClick(label4);
}
//		------------------------------Wait Utils--------------------------------------------//


public WebElement waitforElementPresence(By locator , int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	highlightelement(element);
	return element;
}


public WebElement waitforElementVisibility(By locator , int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	highlightelement(element);
	return element;
}



public void clickWhenready(By locator , int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
}




public String waitforTitleContains(String TitleFraction , int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	try {
	if(wait.until(ExpectedConditions.titleContains(TitleFraction))) {
		return driver.getTitle();
	}}
	catch(TimeoutException e){
		System.out.println("Title not found");
	}
	return driver.getTitle();
}



@Step("Waiting for Title and Capturing it")
public String waitforTitletoBe(String TitleVal , int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	try {
	if(wait.until(ExpectedConditions.titleContains(TitleVal))) {
		return driver.getTitle();
	}}
	catch(TimeoutException e){
		System.out.println("Title not found");
	}
	return driver.getTitle();
}




public String waitforURLContains(String URLFraction , int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	try {
	if(wait.until(ExpectedConditions.urlContains(URLFraction))) {
		return driver.getCurrentUrl();
	}}
	catch(TimeoutException e){
		System.out.println("URL not found");
	}
	return driver.getCurrentUrl();
}



public String waitforURLtoBe(String URL , int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	try {
	if(wait.until(ExpectedConditions.urlToBe(URL))) {
		return driver.getCurrentUrl();
	}}
	catch(TimeoutException e){
		System.out.println("URL not found");
	}
	return driver.getCurrentUrl();
}


public  Alert waitforJSAlert(int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.alertIsPresent());
}



public  String getJSAlertText(int timeOut) {
	Alert alert = waitforJSAlert(timeOut);
	String text = alert.getText();
	alert.accept();
	return text;
}



public  void aceeptAlert(int timeOut) {
	 waitforJSAlert(timeOut).accept();
}



public  void dismissAlert(int timeOut) {
	 waitforJSAlert(timeOut).dismiss();
}




public  void AlertSendKeys(int timeOut , String value) {
	 Alert alert = waitforJSAlert(timeOut);
		alert.sendKeys(value);
		alert.accept();
}


//----------------------------Wait for Iframes--------------------------------//////////////////////

public void waitforFrameBylocator(By framelocator , int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));
}


public void waitforFrameByIndex(int frameIndex , int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
}


public void waitforFrameByName(By frameName , int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
}


public void waitforFrameByWebElement(WebElement frameElement , int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
}

/**
 * 
 * @param totalWindows
 * @param timeOut
 * @return
 */
public Boolean WaitforWindowstobe(int totalWindows , int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.numberOfWindowsToBe(totalWindows));
}

/**
 * 
 * @param locator
 * @param timeOut
 * @return
 */
	public List<WebElement> waitforPresenceofElementsLocated(By locator , int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));	
	}
	
/**
 * 	
 * @param locator
 * @param timeOut
 * @return
 */
	public List<WebElement> waitforVisibiltyofElementsLocated(By locator , int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			return List.of();// will return empty arraylist
		}
		
	}

}	
	
	
	
	



