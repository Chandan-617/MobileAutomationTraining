package org.rahulsheetyacademy.pageobject.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
//import org.testng.annotations.BeforeMethod;

import com.rahulsheetyacademy.utils.AndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {
	
	
	AndroidDriver driver;
	
	
	
	
	public FormPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	//driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Advik");
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;
	//driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']")).click();
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement countrySelection;
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	
	public void setNameField(String name)
	{
		
		nameField.sendKeys(name);
		driver.hideKeyboard();
		
	}
	
	public void setActivity()
	{
		
		//screen to home page
			
			Activity activity=new Activity("com.androidsample.generalstore","com.androidsample.generalstore.SplashActivity");
			
			driver.startActivity(activity);
		
		
	}
	
	public void setGender(String gender)
	{
		if(gender.contains("female"))
		{
			femaleOption.click();
			
		}
		else
		{
			maleOption.click();
			
		}
		
	}
	
	public void setCountrySelection(String countryName)
	{
		countrySelection.click();
		scrollToText(countryName);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
		
	}
	
	public ProductCatalogPage submitForm()
	{
		
		shopButton.click();
		return new ProductCatalogPage(driver);
	}
	

}
