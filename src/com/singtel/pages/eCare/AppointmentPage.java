package com.singtel.pages.eCare;

import org.openqa.selenium.WebDriver;

import com.singtel.common.Utility;

public class AppointmentPage
{
WebDriver driver;
Utility utility;

	public  AppointmentPage(WebDriver driver)
	{
		this.driver=driver;
		utility=new Utility(driver);
	}
	
	public boolean verifyPageTitle()
	{
		if(driver.getTitle().equals("Order and Appointments"))
			return true;
		return false;
	}
	
	public boolean verifyBookAppointmentPageLoaded()
	{
		if(!utility.waitForPageLoad())
		{
			return false;
		}
		else
		{
			return verifyPageTitle();
		}

	}
}
