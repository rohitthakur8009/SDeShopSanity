package com.singtel.TestCases;

import org.openqa.selenium.WebDriver;

import com.singtel.pages.eShop.ProductServicesPage;
import com.singtel.pages.eShop.fibrebb.FhbPlansPage;
import com.singtel.pages.eShop.fibrebb.FixedLineSelectionPage;
import com.singtel.pages.eShop.fibrebb.InstallationAddress;

public class FHBTestCases {

	WebDriver driver;
	ProductServicesPage productServicesPage;
	FhbPlansPage fhbPlansPage;
	InstallationAddress installationAddress;
	FixedLineSelectionPage fixedLineSelectionPage;
	
	public FHBTestCases(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public boolean FHBNew(int row)
	{
		productServicesPage = new ProductServicesPage(driver);
		fhbPlansPage = new FhbPlansPage(driver, "1G");
		installationAddress = new InstallationAddress(driver, "New Customer", "530674", "08", "625");
		fixedLineSelectionPage = new FixedLineSelectionPage(driver, "New Land Line", "sfsarets");
		try
		{
		
			productServicesPage.clickSubCategory("Fibre Broadband Plans");
			
			Thread.sleep(5000);
			
			fhbPlansPage.selectFHBPlan();
			
			Thread.sleep(5000);
			
			installationAddress.proceedtoNextStep();
			
			Thread.sleep(5000);
			
			fixedLineSelectionPage.proceedToAddonsPage();
			
			Thread.sleep(5000);
			
		return true;	
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			return false;
		
		}
	}
	
	
}
