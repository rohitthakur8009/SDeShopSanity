package com.singtel.pages.eShop.Phones;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PhoneListPage {
	
	WebDriver driver;
	
	String pageTitle = "Phones";
	
	WebElement deviceImage;
	
	String device;
	
	public PhoneListPage(WebDriver driver, String device)
	{
		this.driver = driver;
		this.device = device;
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
		WebElement deviceImage = driver.findElement(By.xpath(".//*/div/img[@alt='"+ device + "']"));
		WebElement parent = deviceImage.findElement(By.xpath(".."));
		parent =parent.findElement(By.xpath(".."));
		parent =parent.findElement(By.xpath(".."));
		parent =parent.findElement(By.xpath(".."));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", parent);
		parent.findElement(By.tagName("button")).click();
		return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		
	
	}

}
