package com.singtel.pages.eShop.Phones;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PlanListPage {

	WebDriver driver;
	
	String pageTitle = "Postpaid Plan";
	
	By planCategory;
	By planName;
	
	public PlanListPage(WebDriver driver, String planCategory, String planName)
	{
		this.driver = driver;
		
		this.planCategory = By.xpath(".//*/div[.='"+ planCategory +"']");
		
		this.planName = By.xpath(".//*/span[text()='"+ planName +"']");
		
	}
	
	public boolean verifyPageTitle()
	{
		if(driver.getTitle().contains(pageTitle))
			return true;
		return false;
		
	}
	
	public boolean SelectPlan()
	{
		
		try
		{
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(planCategory));
	    
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",driver.findElement(planCategory));
		
		
		WebElement plan = driver.findElement(planName);

		//Bring the parent tag in focus 
		Thread.sleep(500); 
		plan =plan.findElement(By.xpath(".."));
		plan =plan.findElement(By.xpath(".."));
		
		Actions action = new Actions(driver).doubleClick(plan);
		action.build().perform();
		

		
		return true;
	
		}
		catch(Exception ex)
		{
			System.out.println("Plan Cannot be Selected");
			ex.printStackTrace();
			return false;
		}
		
	}
}
