package com.singtel.pages.eCare;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.singtel.common.Utility;

public class PaymentHistoryPage 
{

	WebDriver driver;
	Utility utility;
	
	public  PaymentHistoryPage(WebDriver driver)
	{
		this.driver=driver;
		utility=new Utility(driver);
	}
	
	public boolean verifyPageTitle()
	{
		if(driver.getTitle().equals("Payment History"))
			return true;
		return false;
	}
	
	public boolean verifyPaymentHistoryPageLoaded()
	{
		if(!utility.waitForPageLoad())
		{
			return false;
		}
		else
		{
			
			if( verifyPageTitle())
			{
				return verifyPaymentHistoryDetails();
			}
			else
				return false;
		}

	}
	
	public boolean verifyPaymentHistoryDetails()
	{
		try
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(400,0)", "");
			
			//verify Payment  History Text is displayed
			
			By tableElement=By.xpath("//table[contains(@class,'paymentHistory')]//tr");
			List<WebElement> paymentHistoryRows=driver.findElements(tableElement);
			System.out.println(paymentHistoryRows.size());
			if(paymentHistoryRows.size() >=2)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}