package com.singtel.pages.eShop.Phones;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MobileAddonsPage {
	
	WebDriver driver;
	
	String pageTitle = "Add-ons";
	
	List<String> addonsList;
	
	By proceedtoCartButton = By.xpath("//*/button[.='Proceed to Cart']");
	
	public MobileAddonsPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public MobileAddonsPage(WebDriver driver, List<String> addonsList)
	{
		this.driver = driver;
		
		this.addonsList = addonsList;
		
	}
	
	public boolean verifyPageTitle()
	{
		if(driver.getTitle().contains(pageTitle))
			return true;
		return false;
		
	}

	
	public boolean proceedToCartPage()
	{
		
		try
		{
			/*if(addonsList.size()>0)
			for(String addon : addonsList)
			{
				addAddon(addon);
			}
			*/
			driver.findElement(proceedtoCartButton).click();
			
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		
	}
	
	public void addAddon(String addon)
	{
		
	}

}
