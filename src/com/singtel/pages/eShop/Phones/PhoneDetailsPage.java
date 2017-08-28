package com.singtel.pages.eShop.Phones;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PhoneDetailsPage {
	
	WebDriver driver;
	String pageTitle;
	
	By deviceColor;
	By deviceSize;
	By nextStep = By.xpath("//*/button[.='Next Step']");
	
	public PhoneDetailsPage(WebDriver driver, String device, String color, String size)
	{
		this.driver = driver;
		this.pageTitle = device;
		this.deviceColor = By.xpath("//*/div[.='"+color+"']");
		this.deviceSize= By.xpath("//*/div[.='"+size+"']");
	}		
	
	
	public boolean verifyPageTitle()
	{
		if(driver.getTitle().contains(pageTitle))
			return true;
		return false;
	}
	
	public boolean selectDevice()
	{
		try
		{
		driver.findElement(deviceColor).click();
		
		driver.findElement(deviceSize).click();
		
		driver.findElement(nextStep).click();
		
		return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	
	}
	

}
