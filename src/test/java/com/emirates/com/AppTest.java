package com.emirates.com;
import com.emirates.utils.Utils;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;

/**
 * UI tests for Android App.
 */
public class AppTest
{
//	public AppiumDriver<MobileElement> driver;
	Utils mobileApp;
	
	@BeforeSuite
	public void setUp() throws IOException
	{
		mobileApp = new Utils("MyLenovoK4Note");
	}

	@AfterSuite
	public void tearDown() throws IOException
	{
		mobileApp.closeMobileApp();
	}

	@Test(enabled=true,priority=0)
	public void testWebViewControls() throws InterruptedException
	{
		mobileApp.clickElement(mobileApp.getElementByXpath("uname"));
        mobileApp.switchToWebView();
        mobileApp.enterTextInElement(
        		mobileApp.getElementByXpath("enterNameOnWebwiew"),
        		mobileApp.getValueFromPropertiesFile("testDataName"));
        mobileApp.clickElement(mobileApp.getElementByXpath("selectCarOnWebView"));
        mobileApp.switchToNativeApp();
        List<MobileElement> allCarsOnWebView = mobileApp.getElementsByXpath("allCarsOnWebView");
        for (MobileElement carName : allCarsOnWebView) {
        	if (carName.getText().equals("Audi")) {
        		carName.click();
        		break;
        	}
        }
        mobileApp.switchToWebView();
        mobileApp.clickElement(mobileApp.getElementByXpath("sendButtonOnWebView"));
        List<MobileElement> nameDisplayedOnWebView = mobileApp.getElementsByXpath("nameDisplayedOnWebView");
        for (MobileElement el : nameDisplayedOnWebView) {
        System.out.println(el.getText());
        }
        Assert.assertEquals(mobileApp.getValueFromPropertiesFile("testDataName"), nameDisplayedOnWebView.get(3).getText());
        mobileApp.switchToNativeApp();
	}
	
	@Test(enabled=true,priority=1)
	public void testExceptionScenario() {
		
	}
	
}
