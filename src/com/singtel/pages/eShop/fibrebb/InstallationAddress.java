package com.singtel.pages.eShop.fibrebb;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InstallationAddress {
	
	WebDriver driver;
	
	String pageTitle = "Fibre Coverage Check"; 
	
	String flowType;
			
	By accordionList = By.xpath(".//div[@class='singtel-accordion']//li");
	
	By postCodeTextBox = By.xpath("//input[@placeholder='EG. 612356']");
	
	By searchButton = By.xpath("//button[@aria-label='Retrieve']");
	
	By floorNoSelect = By.xpath("//select[@id='select-floor']");
	
	By unitNoSelect = By.xpath("//select[@id='select-unit']");
	
	By landedCheckBox = By.xpath("//input[@type='checkbox']");
	
	By checkAvailability = By.xpath("//button[contains(text(),'Check Fibre Availability')]");
	
	By continueButton = By.xpath("//button[contains(text(),'Continue')]");
	
	WebDriverWait wait;
	
	JavascriptExecutor jse;
	
	WebElement pageSection;

	String postCode;
	String floorNo = "Landed";
	String unitNo;

	public boolean verifyPageTitle()
	{
		if(driver.getTitle().contains(pageTitle))
			return true;
		return false;
		
	}
	
	public InstallationAddress(WebDriver driver, String flowType, String postCode, String floorNo, String unitNo) {
		this.driver = driver;
		this.flowType = flowType;
		this.postCode = postCode;
		this.floorNo = floorNo;
		this.unitNo = unitNo;
		wait = new WebDriverWait(driver, 30);
		jse = (JavascriptExecutor)driver;
	}
	
	public boolean proceedtoNextStep()
	{
		switch(flowType)
		{
		
		case "New Customer":
			if(!proceedasNewCustomer())
			return true;
			
		case "Existing Singtel Customer":
			if(!proceedasExistingCustomer())
			return true;
			
		case "Re-contract":
			if(!proceedasRecon())
			return true;
			
		
		}
	
		return false;
		
	}

	private boolean proceedasNewCustomer() {
		
		try
		{
		pageSection = expandAccordion();
		
		if(!enterAddress(pageSection))
			return false;
		
		//Click Check Availability
		wait.until(ExpectedConditions.elementToBeClickable(checkAvailability));
		
		pageSection.findElement(checkAvailability).click();
		
		//Click Continue
		
		jse.executeScript("arguments[0].scrollIntoView();",driver.findElement(continueButton));
		
		new Actions(driver).moveToElement(driver.findElement(continueButton)).perform();
		
		//wait.until(ExpectedConditions.elementToBeClickable(continueButton));
		
		for(WebElement button : driver.findElements(continueButton))
		{
			try{
				button.click();
				break;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		Thread.sleep(10000);
		
		return true;
		}
		catch(Exception ex)
		{
		ex.printStackTrace();
		return false;
		}
	}

	private WebElement expandAccordion() {
	
		List <WebElement> list;
		try{
		list = driver.findElements(accordionList);
		
		for(WebElement x : list)
		{
			System.out.println(x.getAttribute("innerHTML"));
			if(x.getAttribute("innerHTML").contains(flowType))
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

	private boolean proceedasRecon() {
		
		return false;
	}

	private boolean proceedasExistingCustomer() {	
		return false;
	}
	
	private boolean enterAddress(WebElement pageSection)
	{
		
		
		try
		{
			pageSection.findElement(postCodeTextBox).sendKeys(postCode);
			
			wait.until(ExpectedConditions.elementToBeClickable(searchButton));
			
			pageSection.findElement(searchButton).click();
			
			jse.executeScript("arguments[0].scrollIntoView();",pageSection.findElement(searchButton));
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(floorNoSelect));
			
			
			
			if(floorNo.equalsIgnoreCase("landed"))
			{
				pageSection.findElement(landedCheckBox).click();
			}	
			else
			{
			Select select  = new Select(pageSection.findElement(floorNoSelect));
			
			select.selectByValue(floorNo);
			
			select = new Select(pageSection.findElement(unitNoSelect));
			
			
			select.selectByValue("#"+floorNo+"-"+unitNo);
			
			}
			
			
			
			
			
			
			return true;
		}
		catch(Exception ex)
		{
			LogEntries log = driver.manage().logs().get("browser");
			System.out.println(log.toString());
			ex.printStackTrace();
			return false;
		}
	}
	

}
