package com.singtel.common;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility 
{

	WebDriver driver;

	public Utility(WebDriver driver)
	{
		this.driver=driver;
	}

	public boolean waitForPageLoad() 
	{
		ExpectedCondition<Boolean> expectation = new
				ExpectedCondition<Boolean>() 
		{
			public Boolean apply(WebDriver driver) 
			{
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};

		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		}
		catch (Throwable error) 
		{
			return false;
		}

		return true;

	}

	public static void deleteAllFile(File  folderLocation)
	{
		if(folderLocation.isDirectory())
		{

			String files[] = folderLocation.list();
			for (String temp : files) 
			{

				if(!temp.contains("SpringDigitalAutomation"))
				{
					File fileDelete = new File(folderLocation, temp);
					fileDelete.delete();
					System.out.println("Deleted "+fileDelete);
				}
			}

		}


	}
	public boolean jsClick(WebElement element)
	{
		try
		{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
		
	}
}
