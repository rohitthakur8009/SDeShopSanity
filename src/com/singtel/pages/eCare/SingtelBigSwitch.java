package com.singtel.pages.eCare;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.singtel.common.Utility;

public class SingtelBigSwitch 
{
	WebDriver driver;
	Utility utility;
	By toggleSwitch=By.xpath("//label[@for='switchButtonIdx']");
	By toggleSwitcheShop = By.xpath("//label[@for='switchButtonEshopIdx']");
	
	public SingtelBigSwitch(WebDriver driver) 
	{
		this.driver=driver; 
		utility=new Utility(driver);
	}
	
	public boolean launchPage(String prodURL)
	{
		try
		{
		driver.get(prodURL);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	public boolean setProdCookie()
	{
		WebElement togleswitch=null;
		try
		{
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(toggleSwitch));
		togleswitch=driver.findElement(toggleSwitch);
		togleswitch.click();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//Try Java script click
			if(utility.jsClick(togleswitch))
				return true;
			return false;
		}
		
		return true;
		
	}
	
	public boolean setShopProdCookie()
	{
		WebElement togleswitch=null;
		try
		{
		WebDriverWait wait=new WebDriverWait(driver, 10);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");

	
		
		wait.until(ExpectedConditions.elementToBeClickable(toggleSwitcheShop));	
		
		togleswitch=driver.findElement(toggleSwitcheShop);
	
		togleswitch.click();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//Try Java script click
			if(utility.jsClick(togleswitch))
				return true;
			return false;
		}
		
		return true;
	}


}
