package com.singtel.pages.eCare;

import java.awt.Desktop.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.singtel.common.Utility;
//import com.thoughtworks.selenium.webdriven.Windows;

public class PayBillPage 
{
WebDriver driver;
Utility utility;

By overviewBillHistory=By.xpath("//h2[contains(.,'Overview of your invoices')]/ancestor::li[contains(@class,'accordion-navigation')]");
By overviewBillHistoryIcon=By.className("expand-collapse-icon");

	public  PayBillPage(WebDriver driver)
	{
		this.driver=driver;
		utility=new Utility(driver);
	}
	
	public boolean verifyPageTitle()
	{
		if(driver.getTitle().equals("Pay Bills"))
			return true;
		return false;
	}
	
	public boolean verifyPayBillPageLoaded()
	{
		if(!utility.waitForPageLoad())
		{
			return false;
		}
		else
		{
			if (verifyPageTitle())
			{
				return clickOverviewBillHistory();
			}
			else
				return false;
		}

	}
	public boolean clickOverviewBillHistory()
	{
		try
		{
		//	WebDriverWait wait=new WebDriverWait(driver, 10);
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");			
			WebElement billhistory=driver.findElement(overviewBillHistory);
			billhistory.click();
			

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
