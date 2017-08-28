package com.singtel.pages.eCare;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.singtel.SingtelTest;

public class AccountDashboardPage 
{

	WebDriver driver;
	By accordinList=By.xpath("//div[@class='singtel-accordion']");
	By myAccountCat=By.linkText("My Account");
	By avatarImage = By.xpath("//li[@role='menuitem loginMenu']//a");
	
	
	public AccountDashboardPage(WebDriver driver) 
	{
		this.driver=driver;
	}
	
	public boolean verifyPageTitle(String title)
	{
		if(title.equals(driver.getTitle()))
			return true;
		
		return false;
	}
	
	public void verifyServices()
	{
		//Click Accordin and Take SS
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		List<WebElement> accordinlist=driver.findElements(accordinList);
	
		for(WebElement accordin:accordinlist)
		{
			WebDriverWait wait = new WebDriverWait(driver, 5); 
			try
			{
			wait.until(ExpectedConditions.elementToBeClickable(accordin));
			
			jse.executeScript("arguments[0].scrollIntoView(true)", accordin); 
			accordin.click();


			}
			catch(Exception e)
			{
				continue;
			}
		}
	}	
	
	
	public boolean clickSubCategory(String subcat)
	{
		try
		{
			Actions action=new Actions(driver);
			WebElement myaccount=driver.findElement(myAccountCat);
			action.moveToElement(myaccount).build().perform();
			WebDriverWait wait=new WebDriverWait(driver, 100);
			By subCat=By.linkText(subcat);
			
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", subcat);
			

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
	
	public boolean clickAvatarLinks(String sublink)
	{
		try
		{
			WebElement avatarImageLink = driver.findElement(avatarImage);
			
		    avatarImageLink.click();
				
		    By subLink=By.linkText(sublink);
			
		    WebDriverWait wait=new WebDriverWait(driver, 100);
		    
			wait.until(ExpectedConditions.visibilityOfElementLocated(subLink));
			
			driver.findElement(subLink).click();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}


}
