package com.singtel.pages.eCare;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.singtel.common.Utility;

public class CustomerProfilePage {
	
	WebDriver driver;
	Utility utility;
	
	
	//By overviewBillHistory=By.xpath("//h2[contains(.,'Overview of your invoices')]/ancestor::li[contains(@class,'accordion-navigation')]");
	//By overviewBillHistoryIcon=By.className("expand-collapse-icon");

		public  CustomerProfilePage(WebDriver driver)
		{
			this.driver=driver;
			utility=new Utility(driver);
		}
		
		public boolean verifyPageTitle()
		{
			if(driver.getTitle().equals("Profile"))
				return true;
			return false;
		}
		
		public boolean verifyCustomerProfilePageLoaded()
		{
			if(!utility.waitForPageLoad())
			{
				return false;
			}
			else
			{
				if (verifyPageTitle())
				{
					return true;
				}
				else
					return false;
			}

		}

}
