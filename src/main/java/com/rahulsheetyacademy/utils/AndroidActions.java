package com.rahulsheetyacademy.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils{
	 AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver)
	{
		super();
		this.driver=driver;
		
	}
	
	
	public void longPressAction(WebElement element)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)element).getId(),
						
						"duration",2000));
		
	}
	
	public void scrollToEndAction() {
		
		boolean canScrollMore;
		// Java
		do
		{
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
		    "left", 100, "top", 100, "width", 200, "height", 200,
		    "direction", "down",
		    "percent", 3.0
		));
		}while(canScrollMore);
		
		
	}
	
	public void scrollToText(String text)
	{
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
		
		
	}
	
	
	public void swipeToAction(WebElement ele , String direction)
	{
		// Java
				((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
						"elementId",((RemoteWebElement)ele).getId(),
				    "direction", direction,
				    "percent", 0.75
				));
		
		
	}

}
