package com.emirates.utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Utils 
{
	
	String XPATH = "xpath";
	String ID = "id";
	String NAME = "name";
	String AID = "accessabilityId";
	String CLASSNAME = "classname";
	
	public AppiumDriver<MobileElement> driver;
	Logger logger = Logger.getLogger(Utils.class);
	
	public Utils(AppiumDriver<MobileElement> driver) 
	{
		this.driver = driver;
	}

	/**
	 *Set the timeout
	 *@param n is the timout in seconds
	 */
	public void wait(int n)
	{
		long t0,t1;
		t0=System.currentTimeMillis();
		do{
			t1=System.currentTimeMillis();
		}
		while(t1-t0<n);
	}
	
	public String getValueFromPropertiesFile(String pkey) {
		String pvalue=null;
		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = new FileInputStream("./src/test/java/com/emirates/utils/constants.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			pvalue = prop.getProperty(pkey);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return pvalue;
	}

	public MobileElement waitForElementToAppear(String locatorType, String locator) {
		MobileElement mel = null;
		String pvalue = getValueFromPropertiesFile(locator);
		if (locatorType == ID){
			mel = driver.findElementById(pvalue);
		}
		else if(locatorType == NAME) {
			mel = driver.findElementByName(pvalue);
		}
		else if(locatorType == XPATH) {
			mel = driver.findElementByXPath(pvalue);
		}
		WebDriverWait wait = new WebDriverWait(driver, 60);
		try
		{
			wait.until(ExpectedConditions.visibilityOf(mel));
			
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		return mel;
	}
	
	public List<MobileElement> waitForElementsToAppear(String locatorType, String locator){
		List<MobileElement> mels = new ArrayList<MobileElement>();
		String pvalue = getValueFromPropertiesFile(locator);
		if (locatorType == AID){
			mels = driver.findElementsByAccessibilityId(pvalue);
		}
		else if(locatorType == NAME) {
			mels = driver.findElementsByName(pvalue);
		}
		else if(locatorType == XPATH) {
			mels = driver.findElementsByXPath(pvalue);
		}
		else if(locatorType == CLASSNAME) {
			mels = driver.findElementsByClassName(pvalue);
		}
		WebDriverWait wait = new WebDriverWait(driver, 60);
		for(MobileElement mel :mels) {
			try
			{
				wait.until(ExpectedConditions.visibilityOf(mel));
				
			}catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return mels;
	}
	
	public MobileElement getElementByID(String id) {
		return waitForElementToAppear(ID, id);
	}
	
	public MobileElement getElementByXpath(String xpath) {
		return waitForElementToAppear(XPATH, xpath);
	}
	
	public List<MobileElement> getElementsByXpath(String xpath){
		return waitForElementsToAppear(XPATH, xpath);
	}
	
	public void waitForElementToDisAppear(MobileElement mel) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try
		{
			wait.until(ExpectedConditions.invisibilityOf(mel));
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void swipeRightUntilTextExists(String expected) {
		do {
			swipeRight();
		} while (!driver.getPageSource().contains(expected));
	}

	public void swipeLeftUntilTextExists(String expected) {
		do {
			swipeLeft();
		} while (!driver.getPageSource().contains(expected));
	}

	public void swipeRight() {
		Dimension size = driver.manage().window().getSize();
		int startx = (int) (size.width * 0.9);
		int endx = (int) (size.width * 0.20);
		int starty = size.height / 2;
		TouchAction ta = new TouchAction(driver);
		ta.press(startx, starty).waitAction().moveTo(endx, starty).release().perform();
//		driver.swipe(startx, starty, endx, starty, 1000);
	}

	public void swipeLeft() {
		Dimension size = driver.manage().window().getSize();
		int startx = (int) (size.width * 0.8);
		int endx = (int) (size.width * 0.20);
		int starty = size.height / 2;
		TouchAction ta = new TouchAction(driver);
		ta.press(startx, starty).waitAction().moveTo(endx, starty).release().perform();
//		driver.swipe(startx, starty, endx, starty, 1000);
	}

	public void scrollToElement(String Id) {
		MobileElement e = (MobileElement) driver.findElementById(Id);
		TouchAction ta = new TouchAction(driver);
		ta.moveTo(e);
	}
	/**
	 *Swipe Up Element
	 *@param MobileElement
	 *@param Int Duration
	 */
	public void swipeUpElement(MobileElement element, int duration)
	{
		int topY = element.getLocation().getY();
		int bottomY = topY + element.getSize().getHeight();
		int centerX = element.getLocation().getX() + (element.getSize().getWidth()/2);
		TouchAction ta = new TouchAction(driver);
		ta.press(centerX, bottomY).waitAction().moveTo(centerX, topY).release().perform();
//		driver.swipe(centerX, bottomY, centerX, topY, duration);
	}
	/**
	 *Scroll Up Screen
	 */
	public void scrollUp()
	{
//		driver.swipe(190, 500, 190, 180, 1000);
	}
	/**
	 *Scroll Down Screen
	 */
	public void scrollDown()
	{
//		driver.swipe(190, 180, 190, 500, 1000);
	}

	/**
	 * method to set the context to required view.
	 *
	 * @param context view to be set
	 */
	public void setContext(String context) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
			System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
		}
		driver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW_1

		logger.info("Current context" + driver.getContext());
	}
	/**
	 *Switch from Native to WebView
	 */
	public void switchToWebView()
	{
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) 
		{
			System.out.println(contextName);
			if (contextName.contains("WEBVIEW"))
			{
				driver.context(contextName);
				logger.info("Current context" + driver.getContext());
			}
		}
	}
	/**
	 *Switch from WebView to Native
	 */
	public void switchToNativeApp()
	{
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) 
		{
			System.out.println(contextName);
			if (contextName.contains("NATIVE"))
			{
				driver.context(contextName);
				logger.info("Current context" + driver.getContext());
			}
		}
	}

	public void clickBackButton() 
	{
		driver.navigate().back();
	}


	public String getCurrentMethodName() 
	{
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
	/**
	 *Click on the element
	 *@param element is the mobile element
	 */
	public void clickElement(MobileElement element)
	{
		if(element!=null)
		{
			element.click();
		}
	}
	/**
	 *Set the test field with the value
	 *@param element is the mobile element and string is the value which will pass in the text field
	 */
	public void setTextField(MobileElement element,String value)
	{
		if(element!=null)
		{
			element.clear();
			element.sendKeys(value);
		}
	}
	/**
	 *Return true if element is present else return false
	 *@param element
	 */
	public Boolean IsElementPresent(MobileElement element) 
	{
		try
		{
			element.isDisplayed();
			return true;
		}
		catch (NoSuchElementException e) 
		{ return false; }
	}
	
//	public String getAppPath(String env) {
//		app_path = Null
//		if env.contains('dev'){
//			
//		}
//	}
}

