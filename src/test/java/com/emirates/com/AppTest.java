package com.emirates.com;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.emirates.utils.Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * UI test for Android App.
 */
public class AppTest
{
	public AppiumDriver<MobileElement> driver;
	Utils app_methods;
	
	@BeforeSuite
	public void setUp() throws IOException
	{
		File directory = new File ("./build");
		File app=new File(directory,"selendroid-test-app-0.17.0.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.1.2");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.APP, app);
		
		capabilities.setCapability("appPackage", "io.selendroid.testapp");
		capabilities.setCapability("appActivity","io.selendroid.testapp.HomeScreenActivity");
		
		driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4728/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
		
		app_methods = new Utils(driver);
	}
	@Test(enabled=true,priority=0)
	public void test_app() throws InterruptedException
	{
		MobileElement uname = app_methods.getElementByXpath("uname");
		app_methods.clickElement(uname);;
        System.out.println("_________");
        System.out.println(driver.getContextHandles());
        driver.context("WEBVIEW");
        MobileElement webView = driver.findElementByXPath(".//*[@class='android.webkit.WebView']//*[contains(@text, 'Enter your name here!')]");
        webView.click();
        System.out.println("_________");
        System.out.println(driver.getContextHandles());
//        MobileElement paswd = driver.findElementById(".//*[@text='Username or email']");
//        paswd.sendKeys("Nana@1234"); 
//        driver.hideKeyboard();
//        app_methods.wait(100);
//        MobileElement els = driver.findElementByXPath(".//*[@text='Sign in']");
//        els.click();
//        Thread.sleep(5000);
//        
//        MobileElement clickhome = driver.findElementsById("org.wordpress.android:id/tab_icon").get(0);
//        clickhome.click();
//        
//        MobileElement mysite = driver.findElementById("org.wordpress.android:id/my_site_view_site_text_view");
//        mysite.click();
//        
//        app_methods.wait(5000);
//        
//        app_methods.switchToWebView();
//        app_methods.wait(1000);
//        
//        MobileElement clickshow= driver.findElementByXPath("//*[@id='masthead']/button/span");
//        clickshow.click();
//        app_methods.wait(2000);
//        
//        app_methods.switchToNativeApp();
//        app_methods.wait(1000);
//        
//        MobileElement navigate_up=driver.findElementByAccessibilityId("Navigate up");
//        navigate_up.click();
//        app_methods.wait(2000);
//        
//        MobileElement clickme = driver.findElementsById("org.wordpress.android:id/tab_icon").get(2);
//        clickme.click();
//        
//        MobileElement disconnect = driver.findElementById("org.wordpress.android:id/me_login_logout_text_view");
//        disconnect.click();
//        
//        MobileElement logout = driver.findElementById("android:id/button1");
//        logout.click();
//        
//        Thread.sleep(2000);
	}
	@AfterSuite
	public void tearDown() throws IOException
	{
		driver.quit();
	}
	
}
