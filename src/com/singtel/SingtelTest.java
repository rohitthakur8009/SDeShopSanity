package com.singtel;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.singtel.TestCases.FHBTestCases;
import com.singtel.common.BrowserFactory;
import com.singtel.common.Utility;
import com.singtel.common.Xls_Reader;
import com.singtel.pages.eCare.AccountDashboardPage;
import com.singtel.pages.eCare.AppointmentPage;
import com.singtel.pages.eCare.CustomerProfilePage;
import com.singtel.pages.eCare.LoginPage;
import com.singtel.pages.eCare.PayBillPage;
import com.singtel.pages.eCare.PaymentHistoryPage;
import com.singtel.pages.eCare.SingtelBigSwitch;
import com.singtel.pages.eCare.ViewBillPage;
import com.singtel.pages.eShop.ProductServicesPage;
import com.singtel.pages.eShop.ShoppingCartPage;
import com.singtel.pages.eShop.Phones.MobileAddonsPage;
import com.singtel.pages.eShop.Phones.NumberSelectionPage;
import com.singtel.pages.eShop.Phones.PhoneDetailsPage;
import com.singtel.pages.eShop.Phones.PhoneListPage;
import com.singtel.pages.eShop.Phones.PlanListPage;

public class SingtelTest {

	WebDriver driver;
	SingtelBigSwitch sing_home_page;
	LoginPage sing_login_page;
	AccountDashboardPage sing_acct_dashboard_page;
	ViewBillPage sing_view_bill_page;
	PayBillPage sing_pay_bill_page;
	AppointmentPage sing_appointment_page;
	CustomerProfilePage sing_profile_page;
	PaymentHistoryPage sing_payment_history_page;
	
	
	ProductServicesPage productServicesPage;
	PhoneListPage phoneListPage;
	PhoneDetailsPage phoneDetailsPage;
	PlanListPage planListPage;
	NumberSelectionPage numberSelectionPage;
	MobileAddonsPage mobileAddonsPage;
	ShoppingCartPage shoppingCartPage;	
	
	Utility utility;

	public static Xls_Reader xls;
	public String sanityResult="";
	public static int sscount=0;
	public static File screenshot = null;
	public static String folderLocation="";
	public String ssDocumentFileName="";
	public Map<String,String> testResults = new HashMap<String,String>();

	SingtelTest(String browserType)
	{

		driver=new BrowserFactory().getWebDriver(browserType);
		sing_home_page=new SingtelBigSwitch(driver);
		sing_login_page=new LoginPage(driver);
		sing_acct_dashboard_page=new AccountDashboardPage(driver);
		sing_view_bill_page=new ViewBillPage(driver);
		sing_pay_bill_page=new PayBillPage(driver);
		sing_appointment_page=new AppointmentPage(driver);
		sing_profile_page=new CustomerProfilePage(driver);
		sing_payment_history_page=new PaymentHistoryPage(driver);

		
		utility=new Utility(driver);

	}

	public void SanityTest(int row)
	{
		//Delete All Files Except Configuration File
		deleteAllFile(folderLocation);

		//Get Excel Data
		String prodURL=xls.getCellData("ENV_CONFIG", "PROD_URL", 2);
		String loginURL=xls.getCellData("ENV_CONFIG", "LOGIN_URL", 2);
		String testName=xls.getCellData("TEST","TEST_CASE_NAME", row);	
		String userId=xls.getCellData("TEST", "LOGIN_ID", row);
		String password=xls.getCellData("TEST", "PASSWORD", row);
		
		
		ssDocumentFileName=testName+"_"+userId;
		
		//Create Page Objects
		
		productServicesPage = new ProductServicesPage(driver);
		phoneListPage = new PhoneListPage(driver,"Apple iPhone 6");
		phoneDetailsPage = new PhoneDetailsPage(driver, "Apple iPhone 6", "Gold", "64GB");
		planListPage = new PlanListPage(driver, "Combo Plans", "Combo 1");
		numberSelectionPage = new NumberSelectionPage(driver, "New");
		mobileAddonsPage = new MobileAddonsPage(driver);
		shoppingCartPage = new ShoppingCartPage(driver);
		
		
		
		
		

		//Launch Singtel Home Page 
		if(!sing_home_page.launchPage(prodURL))
		{
			
			
			sanityResult=sanityResult.concat("Prod URL Launch Not Successful");
			updateFailure(sanityResult, row);
			return;
			
		}
		
		

		if(!utility.waitForPageLoad())
		{
			sanityResult=sanityResult.concat("Prod URL Launch Not Successful");
			updateFailure(sanityResult, row);
			return;
		}
		else
		{
			sanityResult=sanityResult.concat("Prod URL Launch Successfull\n");
		}


		//Set Prod Cookie
		if(!sing_home_page.setShopProdCookie())
		{
			sanityResult=sanityResult.concat("Toogle Button/Setting Prod Cookie is not working");
			updateFailure(sanityResult, row);
			return;
		}
		else
		{
			sanityResult=sanityResult.concat("Cookie Set Successfull\n");
		}


		takeSS();

		//Launch Login Page and do Login
		productServicesPage.launchPage(loginURL);

		if(!utility.waitForPageLoad())
		{
			sanityResult=sanityResult.concat("Product Services Page URL Launch Not Successful");
			updateFailure(sanityResult, row);
			testResults.put("Launch SD Site", "FAIL -- Unable to Launch SD Site");
			return;
		}
		else
		{

			sanityResult=sanityResult.concat("Login Page Launch Successfull\n");
			testResults.put("Launch SD Site", "PASS");

		}
		
		
		FHBTestCases fhbTestCases = new FHBTestCases(driver);
		
		fhbTestCases.FHBNew(row);
}


	public static void setConfiguration()
	{
		folderLocation=System.getProperty("user.home")+"\\Documents\\Singtel\\";
		xls=new Xls_Reader(folderLocation+"SpringDigitalAutomation.xlsx");

	}

	public static void main(String[] args) 
	{


		setConfiguration();

		for(int i=2;i<=xls.getRowCount("TEST");i++)
		{
			try
			{
				String browserType=xls.getCellData("TEST", "BROWSER_TYPE", i);
				SingtelTest eShop=new SingtelTest(browserType);
				eShop.SanityTest(i);
				eShop.stopDriver();

			}
			catch(Exception e)
			{
				continue;
			}	
		}

	}


	public void stopDriver()
	{
		driver.quit();
	}




	public void takeSS()
	{
		screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		try
		{
			FileUtils.copyFile(screenshot, new File(folderLocation+"\\"+(++sscount)+".png"));
		}
		catch(Exception io)
		{
			io.printStackTrace();
			return;
		}
	}


	public  void writePDF()
	{

		int indentation=0;
		Document document = new Document();
		try
		{

			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
			Date runDate = new Date(System.currentTimeMillis());
			System.out.println(sdf.format(runDate));
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(folderLocation+ ssDocumentFileName+".pdf"));
			document.open();
			
			Font titleFont = new Font(FontFamily.TIMES_ROMAN,16,Font.BOLD,BaseColor.RED);
			Font redFont = new Font(FontFamily.TIMES_ROMAN,9,Font.NORMAL,BaseColor.RED);
			Font blackFont = new Font(FontFamily.TIMES_ROMAN,9,Font.NORMAL,BaseColor.BLACK);
			Font greenFont = new Font(FontFamily.TIMES_ROMAN,9,Font.NORMAL,BaseColor.GREEN);
			Font whiteFont = new Font(FontFamily.TIMES_ROMAN,9,Font.BOLD,BaseColor.WHITE);
			
			
			Chunk titleText = new Chunk("Spring Digital eCare Sanity\n\n",titleFont);
            document.add(new Paragraph(titleText));
            
            document.add(new Chunk("Time: "+ runDate+ "\n\n",blackFont));
            
            
            PdfPTable resultTable = new PdfPTable(2);
            
            
            resultTable.setWidths(new int[]{2, 1});
            
            PdfPCell header1 = new PdfPCell(new Phrase("TEST CASE",whiteFont));
            header1.setBackgroundColor(BaseColor.BLUE);
            
            PdfPCell header2 = new PdfPCell(new Phrase("RESULT",whiteFont));
            header2.setBackgroundColor(BaseColor.BLUE);
            
            resultTable.addCell(header1);
            resultTable.addCell(header2);
            
            
            
            for(String key: testResults.keySet())
            {
            	
            	if(testResults.get(key) == "PASS")
            	{
            	resultTable.addCell(new Phrase(key,blackFont));
            	resultTable.addCell(new Phrase(testResults.get(key), greenFont));
            	}
            	else
            	{
            		resultTable.addCell(new Phrase(key,blackFont));
                	resultTable.addCell(new Phrase(testResults.get(key), redFont));
                	
            	}
            }

            
            
            
            document.add(new Paragraph("Sanity Results:: \n\n"));
			//Add Image

            document.add(resultTable);
            
            document.add(new Paragraph("\n\nScreenshots of the Flow\n\n"));
            
			File file=new File(folderLocation);
			if(file.isDirectory())
			{


				//List<String> files = Arrays.asList(file.list());
				
				
				//Collections.sort(files);


				String[] files = file.list();
				
				for (String temp : files) 
				{
					if(temp.contains(".png"))
					{
						System.out.println(temp);
						Image image = Image.getInstance(folderLocation+temp);

						float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
								- document.rightMargin() - indentation) / image.getWidth()) * 100;
						image.scalePercent(scaler);

						document.add(image);
					}

				}

			}
			document.close();
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
	}




	//Delete All Screen shots
	public void deleteAllFile(String  folderLocation)
	{
		File file=new File(folderLocation);
		if(file.isDirectory())
		{

			String files[] = file.list();
			for (String temp : files) 
			{

				if(temp.endsWith(".png"))
				{
					File fileDelete = new File(file, temp);
					fileDelete.delete();
				//	System.out.println("Deleted "+fileDelete);
				}
			}

		}


	}



	public void updateFailure(String sanityResult,int row)
	{
		takeSS();
		writePDF();
		xls.setCellData("TEST", "RESULT", row, sanityResult);
	}

	public void update(String sanityResult,int row)
	{

		writePDF();
		xls.setCellData("TEST", "RESULT", row, sanityResult);
	}

}
