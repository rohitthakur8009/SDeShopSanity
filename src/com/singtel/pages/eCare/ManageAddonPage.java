package com.singtel.pages.eCare;

import com.singtel.common.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageAddonPage
{
  WebDriver driver;
  Utility utility;
  By loginFrame = By.id("ssoLogin");
  
  public ManageAddonPage(WebDriver driver)
  {
    this.driver = driver;
    this.utility = new Utility(driver);
  }
  
  public boolean verifyPageTitle()
  {
    if (this.driver.getTitle().equals("Manage Add-ons")) {
      return true;
    }
    return false;
  }
  
  public boolean verifyManageAddOnPageLoaded()
  {
    if (!this.utility.waitForPageLoad()) {
      return false;
    }
    if (verifyPageTitle()) {
      return verifyManageAddonList();
    }
    return false;
  }
  
  public boolean verifyManageAddonList()
  {
    return false;
  }
}
