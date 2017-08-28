package com.singtel.pages.eShop.fibrebb;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class FhbPlansPage {
	
	WebDriver driver;
	
	String pageTitle  = "Fibre Broadband Plans";
	
	By planList = By.xpath("//div[@class='ux-broadband-block']");
	
	String plan = "1G";
	
	public FhbPlansPage(WebDriver driver, String plan)
	{
		this.driver = driver;
		
		plan = this.plan;
		
	}
	
	public boolean verifyPageTitle()
	{
		if(driver.getTitle().contains(pageTitle))
			return true;
		return false;
		
	}
	
    public boolean selectFHBPlan()
    {
    	
    	List<WebElement> plans = driver.findElements(planList);
    	
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	
    	boolean flag = false;
    	for(WebElement x : plans)
    	{
    		jse.executeScript("arguments[0].scrollIntoView();",x);
    		
    		String xinnerHtml = x.getAttribute("innerHTML");
    		
    		System.out.println(xinnerHtml);
    		if(xinnerHtml.contains(plan))
    		{
    			
    			List<WebElement> buttons = x.findElements(By.tagName("button"));
    			
    			for (WebElement button : buttons)
    			{
    				try
    				{
    					button.click();
    					flag = true;
    				}
    				catch(Exception ex)
    				{
    					
    				}
    			}
    		}
    		if(flag) break;
    	}
   
    	
    	return true;
    }
	
    

}
