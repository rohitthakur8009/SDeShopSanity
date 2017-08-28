package com.singtel.pages.eShop;

import org.openqa.selenium.WebDriver;

public class ShoppingCartPage {
	
	WebDriver driver;
	
	String pageTitle = "Shopping Cart";
			
	public ShoppingCartPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public boolean verifyPageTitle()
	{
		if(driver.getTitle().contains(pageTitle))
			return true;
		return false;	
	}


}
