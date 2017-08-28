package com.singtel.pages.eShop.fibrebb;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FixedLineSelectionPage {

	WebDriver driver;
	
	String pageTitle = "Fixed Line";
	
	String numberType;
	
	String userName;
	
	By accordionList = By.xpath(".//div[@class='singtel-accordion']//li");
	
	By numberList = By.xpath("//div[contains(text(),'9') or contains(text(),'8')]");
	
	By userIdInput = By.xpath("//input[@id='singnetId']");
	
	By checkAvailabilityButton = By.xpath("//button[contains(text(),'Check Availability')]");
	
	By continueButton = By.xpath("//button[contains(text(),'Continue to Addons')]");
	
	By successText = By.xpath("//div[contains(text(),'Success! Your Singnet ID is validated')]");
	
	JavascriptExecutor jse;
	
	WebDriverWait wait;
	
	
	
	
	public FixedLineSelectionPage(WebDriver driver, String numberType, String userName) {
		
		this.driver = driver;
		this.numberType = numberType;
		this.userName = userName;
		
		jse = (JavascriptExecutor)driver;
		wait = new WebDriverWait(driver, 20);
		
	}




	public boolean verifyPageTitle()
	{
		if(driver.getTitle().contains(pageTitle))
			return true;
		return false;
		
	}
	
	
	public boolean proceedToAddonsPage()
	{
		
		//Select Number
		if(!selectFixedNumber())
			return false;
		
		//Select Username
		if(!selectUsername())
			return false;
		
		//Click Continue to Addons button
		wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton));
		
		driver.findElement(continueButton).click();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
		
		}




	private boolean selectUsername() {
		
		try
		{
			
			//Enter user ID
			wait.until(ExpectedConditions.invisibilityOfElementLocated(userIdInput));
			
			driver.findElement(userIdInput).sendKeys(userName);
			
			//Click the Check Availability button
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(checkAvailabilityButton));
			
			driver.findElement(checkAvailabilityButton).click();
			
			//Verify validation is successfull
			wait.until(ExpectedConditions.invisibilityOfElementLocated(successText));
			
			return true;
		}
		catch(Exception ex)
		{
			
		}
		return false;
	}

	private WebElement expandAccordion() {
		
		List <WebElement> list;
		try{
		list = driver.findElements(accordionList);
		
		for(WebElement x : list)
		{
			System.out.println(x.getAttribute("innerHTML"));
			if(x.getAttribute("innerHTML").contains(numberType))
			{
				x.click();
				return x;
			}
		}
		list.get(0).click();
		return list.get(0);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
			return driver.findElement(accordionList);
		}

		
	}



	private boolean selectFixedNumber() {
		WebElement pageSection;
		try
		{
			
		pageSection = expandAccordion();
		
		
		switch(numberType)
		{
		case "New Land Line":
			
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
					 return true;
					}
					catch(Exception ex)
					{
						return false;
					}
				}
			
			
			
			
			
		case "existing":
			return false;
			
		}
		
		return false;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
}


	
