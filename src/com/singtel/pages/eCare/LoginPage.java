package com.singtel.pages.eCare;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage 
{
	WebDriver driver;
	By loginFrame=By.id("ssoLogin");
	By loginId=By.id("loginid");
	By password=By.id("pass");
	By submitCTA=By.xpath("//input[@value='Log in']");

	public LoginPage(WebDriver driver) 
	{
		this.driver=driver; 
	}

	public void launchPage(String loginURL)
	{
		driver.get(loginURL);
	}

	public boolean personalAccountLoginPage(String userId,String Password)
	{

		try
		{
			WebElement frameId=driver.findElement(loginFrame);
			driver.switchTo().frame(frameId);

			WebElement loginid=driver.findElement(loginId);

			WebElement pwd=driver.findElement(password);

			WebElement submit=driver.findElement(submitCTA);

			loginid.clear();
			loginid.sendKeys(userId);
			pwd.clear();
			pwd.sendKeys(Password);
			submit.click();
			driver.switchTo().defaultContent();

		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	public boolean checkSuccessfullLogin()
	{

		String currentURL=driver.getCurrentUrl();
		System.out.println(currentURL);
		if(currentURL.contains("loginstatus=fail"))
			return false;

		return true;

	}


}
