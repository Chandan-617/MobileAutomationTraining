package org.rahulshettyacademy;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rahulsheetyacademy.pageobject.android.CartPage;
import org.rahulsheetyacademy.pageobject.android.FormPage;
import org.rahulsheetyacademy.pageobject.android.ProductCatalogPage;
import org.rahulshettyacademy.TestUtils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommersetc_4 extends AndroidBaseTest {

	@Test(dataProvider="getData")
	public void fillForm(HashMap<String,String> input) throws InterruptedException {
		
		
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogPage productCatalogPage=formPage.submitForm();
		
		productCatalogPage.addItemToCartByIndex(0);
		productCatalogPage.addItemToCartByIndex(0);
		CartPage cartPage=productCatalogPage.goToCartPage();
		
	
		
		double totalSum=cartPage.getProductsSum();
		double displayFormattedSum=cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();
		
		
		
		
		
	}
	
	
	@BeforeMethod
	public void preSetUp()
	{
		formPage.setActivity();
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\org\\rahulshettyacademy\\testdata\\ecommerse.json");
		return new Object[][] {   {data.get(0)},{data.get(1)}  };
		
		
	}

}
