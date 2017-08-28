package com.singtel.pages.eCare;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.singtel.common.Utility;

public class ViewBillPage 
{

	WebDriver driver;
	Utility utility;
	
	public  ViewBillPage(WebDriver driver)
	{
		this.driver=driver;
		utility=new Utility(driver);
	}
	
	public boolean verifyPageTitle()
	{
		if(driver.getTitle().equals("View My Bill"))
			return true;
		return false;
	}
	
	public boolean verifyViewMyBillPageLoaded()
	{
		if(!utility.waitForPageLoad())
		{
			return false;
		}
		else
		{
			
			if( verifyPageTitle())
			{
				return verifyBasicBillDetails();
			}
			else
				return false;
		}

	}
	
	public boolean verifyBasicBillDetails()
	{
		try
		{
			//verify Bill History Text is displayed
			driver.findElement(By.xpath("//h3[text()='Your Bill History']"));
			driver.findElement(By.xpath("//div[contains(text(),'Account Number')]"));
			JavascriptExecutor jse = (JavascriptExecutor)driver;

			jse.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath("//h3[text()='Your Bill History']"))); 

		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
