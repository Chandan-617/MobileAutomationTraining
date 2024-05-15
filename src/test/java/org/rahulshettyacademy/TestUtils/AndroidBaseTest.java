package org.rahulshettyacademy.TestUtils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.rahulsheetyacademy.pageobject.android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import com.rahulsheetyacademy.utils.AppiumUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.net.URISyntaxException;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest extends AppiumUtils{
	//public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public FormPage formPage;

	@BeforeClass
	public void configureAppium() throws URISyntaxException, InterruptedException, IOException {

		
		
		//File f=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\data.properties");
		
		File f=new File("E:\\MobileAutomationTraining\\AppiumFrameWorkDesign\\src\\test\\resources\\data.properties");
		FileInputStream fis=new FileInputStream(f);
		Properties prop=new Properties();
		prop.load(fis);
		
		String ipAddress=prop.getProperty("ipAddress");
		String port=prop.getProperty("port");	
		service = startAppiumServer(ipAddress,Integer.parseInt(port));
		
		

		
		Thread.sleep(7000);
		UiAutomator2Options options = new UiAutomator2Options();

		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
		options.setChromedriverExecutable("E:\\MobileAutomationTraining\\Appium\\Driver\\chromedriver.exe");

		options.setApp(System.getProperty("user.dir")+"\\src\\test\\resources\\App\\General-Store.apk");

		
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		formPage=new FormPage(driver);

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
	
	
	public void swipeToAction(WebElement ele , String direction)
	{
		// Java
				((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
						"elementId",((RemoteWebElement)ele).getId(),
				    "direction", direction,
				    "percent", 0.75
				));
		
		
	}
	
	public Double getFormattedAmount(String amount)
	{
		
		Double price=Double.parseDouble(amount.substring(1));
		return price;
		
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
		service.stop();

	}

}
