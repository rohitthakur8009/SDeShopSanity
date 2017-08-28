package com.singtel.pages.eShop;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductServicesPage {

	WebDriver driver;
	
	By productServicesCat = By.linkText("Products & Services");
	
	public ProductServicesPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void launchPage(String launchURL)
	{
		driver.get(launchURL);
	}
	
	public boolean clickSubCategory(String subcat)
	{
		try
		{
			Actions action=new Actions(driver);
			WebElement myaccount=driver.findElement(productServicesCat);
			action.moveToElement(myaccount).build().perform();
			WebDriverWait wait=new WebDriverWait(driver, 1000);
			By subCat=By.linkText(subcat);
			
			//((JavascriptExecutor)driver).executeScript("arguments[0].click();", "Phones");
			

			wait.until(ExpectedConditions.visibilityOfElementLocated(subCat));
			action.moveToElement(driver.findElement(subCat)).click().build().perform();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
