package day09;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.Library.Library;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Ebay_ExtentReport {
	
	public static String sURL = "https://www.ebay.com/";
	public static int iBrowserType = 1; //1-Chrome,2-IE,3-FF
	public static WebDriver driver=null;
	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static void main(String[] args) throws Exception {
		setReport();
		launchBrowser();
		browserSetting();
		navigateUrl();
		getPageInfo();
		searchProduct("iPhone", "Cell Phones & Accessories");
		getResultText();
		searchProduct("Samsung", "Cell Phones & Accessories");
		getResultText();
		//closeBrowser();
	}
	
	public static void setReport() {
		
		//Create a Connection for Your Html File
		html = new ExtentHtmlReporter("./report/Ebay.html");
		//Append the Output
		html.setAppendExisting(false);
		extent = new ExtentReports();
		//Attaching the Html Reporter
		extent.attachReporter(html);
		
		
	}
	
	public static void launchBrowser() {
		test = extent.createTest("Launch Browser", "User Can Launch there preferred Browser");
		test.assignAuthor("Mathan");
		test.assignCategory("Smoke");
		switch (iBrowserType) {
		case 1:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the Chrome Browser");
			test.info("User Input is : "+iBrowserType+" ,So invoking the Chrome Browser");
			System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
		case 2:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the IE Browser");
			test.info("User Input is : "+iBrowserType+" ,So invoking the IE Browser");
			System.setProperty("webdriver.ie.driver","./driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
			
		case 3:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the Firefox Browser");
			test.info("User Input is : "+iBrowserType+" ,So invoking the FireFox Browser");
			System.setProperty("webdriver.gecko.driver","./driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
			
		case 4:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the Edge Browser");
			test.info("User Input is : "+iBrowserType+" ,So invoking the Edge Browser");
			System.setProperty("webdriver.edge.driver","./driver/msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("User Input is wrong : "+iBrowserType+" ,So invoking the Default Chrome Browser");
			test.info("User Input is wrong : "+iBrowserType+" ,So invoking the Default Chrome Browser");
			System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
	}
	
	public static void browserSetting() {
		test = extent.createTest("Browser Setting", "User Can set the Browser based on there preference");
		test.assignAuthor("Mathan");
		test.assignCategory("Smoke");
		driver.manage().window().maximize();
		test.info("Window Maximized");
		driver.manage().deleteAllCookies();
		test.info("Deleted all the Cookies");
	}
	
	public static void navigateUrl() {
		test = extent.createTest("Navigate URL", "User can Navigate to there Application");
		test.assignAuthor("Joythi");
		test.assignCategory("Smoke");
		driver.get(sURL);
		test.info("User Navigated to : "+sURL);
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	}
	
	public static void getPageInfo() throws Exception, Exception {
		test = extent.createTest("Get Page Info", "User Can get the Information about the URL");
		test.assignAuthor("Rupesh");
		test.assignCategory("Smoke");
		System.out.println("Get the Current URL : "+driver.getCurrentUrl());
		String currentUrl = driver.getCurrentUrl();
		test.info("Current URL is : "+currentUrl);
		System.out.println("Get the Title : "+driver.getTitle());
		String title = driver.getTitle();
		test.info("Current Title is : "+title);
		if(title.contains("ebay")) {
			test.pass("User Landed to the Right Page");
		}else {
			test.fail("User Landed to the Wrong Page", MediaEntityBuilder
					.createScreenCaptureFromPath(Library.takeScreenshotAsFileDynamicPath(driver,"EbayTile")).build());
		}
		
	}
	
	public static void searchProduct(String sSearchText,String sCat) {
		// Implicite Wait
		test = extent.createTest("Search Product", "User Can search there preferred Product");
		test.assignAuthor("Shradha");
		test.assignCategory("Smoke");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement oTxt,oDrop,oBtn;
		oTxt = driver.findElement(By.id("gh-ac"));
		oTxt.clear();
		oTxt.sendKeys(sSearchText);
		test.info("Entered the Search text");
		oDrop = driver.findElement(By.id("gh-cat"));
		Select oSelect = new Select(oDrop);
		//oSelect.selectByVisibleText(sCat);
		oSelect.selectByIndex(7);
		test.info("Selected the Catagory based on index 7");
		oBtn = driver.findElement(By.id("gh-btn"));
		oBtn.click();
		test.info("Clicked on the Search Button");
	}
	
	
	public static void getResultText() {
		test = extent.createTest("Get Result Test", "User Can get the total result value");
		test.assignAuthor("Dhruva");
		test.assignCategory("Smoke");
		WebElement oResultText;
		oResultText = driver.findElement(By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]"));
		String sText = oResultText.getText();
		sText = sText.replaceAll("[^0-9]","");
		System.out.println("Customized Text is : "+sText);
		int iText = Integer.parseInt(sText);
		test.info("Total Searched produces are : "+iText);
		if(iText>0) {
			test.pass("Searched Products are listed");
			System.out.println("Search Results are Available");
		}else {
			test.fail("Searched Products are Not listed");
			System.out.println("Zero Search Results Found");
		}
		extent.flush();
		
	}
	public static void closeBrowser() {
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
