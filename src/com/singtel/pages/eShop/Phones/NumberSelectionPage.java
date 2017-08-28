package com.singtel.pages.eShop.Phones;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.singtel.common.Utility;

public class NumberSelectionPage {
	
	WebDriver driver;
	
	String pageTitle = "";
	
	String flowType  = "New";
	
	String portIn;
	
	String loginId;
	
	String password;
	
	By accordinList = By.xpath("//h2");
	
	By listElements = By.xpath(".//li");
	
	By numberList = By.xpath("//div[contains(text(),'9') or contains(text(),'8')]");
	
	//To try later for number selection link .//li[contains(@class,'newNumber')]
	
	
	Utility utility;
	
	public NumberSelectionPage(WebDriver driver, String flowType)
	{
		this.driver = driver;
		this.flowType = flowType;
		
		utility = new Utility(driver);
	}
	
	public NumberSelectionPage(WebDriver driver, String flowType, String portIn)
	{
		this.driver = driver;
		
		this.flowType = flowType;
		
		this.portIn = portIn;
		
	}
	
	public NumberSelectionPage(WebDriver driver, String flowType, String loginId, String password)
	{
		this.driver = driver;
		
		this.flowType = flowType;
		
		this.loginId = loginId;
		
		this.password = password;
		
	}
	
	public boolean proceedfromNumberSelection()
	{

		try
		{
		
			switch(flowType)
			{
			
			case "New":
				selectNewNumber();
				return true;
				
			case "PortIn":
				selectPortIn();
				return true;
			
			case "Recon":
				selectReconNumber();
				return true;
			
			default:
				selectNewNumber();
				return true;
			
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Flow Type Failed");
			ex.printStackTrace();
			return false;
		}
	}
	
	
	public void  selectNewNumber() throws Exception
	{
		try
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			//Expand Number Selection Accordin
			WebDriverWait wait = new WebDriverWait(driver, 10); 
			
			/*
			List<WebElement> accordinlist=driver.findElements(accordinList);
			System.out.println("According Size"+accordinlist.size());
			
			wait.until(ExpectedConditions.presenceOfElementLocated(accordinList));
			
		
			Thread.sleep(20000);
			for(WebElement accordin:accordinlist)
			{
				
				if(accordin.getText().contains("get a new number"))
				{
				wait.until(ExpectedConditions.elementToBeClickable(accordin));
				
				jse.executeScript("arguments[0].scrollIntoView(true)", accordin); 
				
				accordin.click();	
				}
			}			*/
			
			Thread.sleep(20000);
			
			
			List<WebElement> links = driver.findElements(listElements);
			
			for(WebElement link : links )
			{
				System.out.println(link.getAttribute("class"));
				
				if(link.getAttribute("class").contains("newNumber"))
				{
					
					link.click();
					
					break;
				}
				
			}
			
			

			
			Thread.sleep(10000);
			
			List<WebElement> numberlist = driver.findElements(numberList);
			
			for(WebElement number: numberlist)
				if(number.getText().length() == 9)
				{
					System.out.println(number.getText());
					
					try
					{
					jse.executeScript("window.scrollTo(0,"+ number.getLocation().x+")");
					
					Actions action = new Actions(driver);
					
					action.moveToElement(number).build().perform();
					
					
					
					wait.until(ExpectedConditions.elementToBeClickable(number));
					
					
					number.click();	
					 break;
					}
					catch(Exception ex)
					{
						System.out.println(ex.getMessage());
					}
				}
		}
		catch(Exception ex)
		{
			throw ex;
		}
			Thread.sleep(10000);
	}
		
	public void  selectPortIn() throws Exception
	{
			
	}
	
	public void selectReconNumber() throws Exception
	{
		
		
	}

	public boolean verifyPageTitle()
	{
		if(driver.getTitle().contains(pageTitle))
			return true;
		return false;
	}
		
		

}
