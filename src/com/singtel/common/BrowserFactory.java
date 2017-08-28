package com.singtel.common;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BrowserFactory
{

	private DesiredCapabilities dc;
	private WebDriver driver;


	public WebDriver getWebDriver(String browserType)
	{
		try{		

			if(driver != null)
			{
				driver.manage().deleteAllCookies();

				if(browserType.contains("MSIE"))
				{
					Utility.deleteAllFile(new File(System.getProperty("user.home") + "\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files"));
					Utility.deleteAllFile(new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Microsoft\\Windows\\Cookies"));
				}

				driver.quit();
			}

			Proxy proxy = new Proxy();
			proxy.setProxyType(ProxyType.AUTODETECT); 

			if (browserType.contains("FIREFOX"))
			{
				//				FirefoxProfile profile=new FirefoxProfile();
				//				profile.setPreference("browser.startup.homepage", "about:blank");
				//				profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
				//				profile.setPreference("startup.homepage_welcome_url.additional",  "about:blank");
				////
				//			//profile.setPreference("browser.usedOnWindows10.introURL", "about:blank");
				//
				//				profile.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
				//dc = DesiredCapabilities.firefox();
				
				System.setProperty("webdriver.gecko.driver", "C://JARs/geckodriver.exe");
				driver = new FirefoxDriver();

				dc.setCapability(CapabilityType.PROXY, proxy);
				//driver = new FirefoxDriver(dc);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait( 10000,TimeUnit.MILLISECONDS);


			}
			else if(browserType.contains("CHROME")){	
				System.setProperty("webdriver.chrome.driver", "C://JARs/chromedriver.exe");

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				dc = DesiredCapabilities.chrome();
				dc.setCapability(CapabilityType.PROXY, proxy);
				//options.CAPABILITY = dc;
				
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait( 10000,TimeUnit.MILLISECONDS);


			}
			else if(browserType.contains("MSIE"))
			{	        	

				System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");

				dc = DesiredCapabilities.internetExplorer();
				dc.setCapability(CapabilityType.PROXY, proxy);
				dc.setCapability("EnableNativeEvents", false);
				dc.setCapability("ignoreZoomSetting", true);

				// Press CTRL + 0 keys of keyboard to set IEDriver Instance zoom level to 100%.

				driver = new InternetExplorerDriver(dc);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait( 15000,TimeUnit.MILLISECONDS);
				driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));


			}
			

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return driver;
	}


}



