package test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Ebay_Select_ExtentReportAdvance {
	
	public static String sURL = "https://www.ebay.com/";
	public static int iBrowserType = 1; //1-Chrome,2-IE,3-FF
	public static WebDriver driver=null;
	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	public static ExtentTest test,node;
	public static String sReportFile = "./report/EbayReport.html";
	public static String sPath = null;
	public static void main(String[] args) throws Exception {
		setUpReport("WINDOW 10", "CHROME");
		launchBrowser();
		browserSetting();
		navigateUrl();
		getPageInfo();
		searchProduct("iPhone", "Cell Phones & Accessories");
		//getResultText();
		//closeBrowser();
	}
	
	public static void setUpReport(String sOS,String sBrowser) {
		html = new ExtentHtmlReporter(sReportFile);
		extent = new ExtentReports();
		extent.attachReporter(html);
		extent.setSystemInfo("OS",sOS);
		extent.setSystemInfo("BROWSER",sBrowser);
		test = extent.createTest("Ebay Testing", "Testing the Ebay Search");
		test.assignAuthor("Mathan");
		test.assignCategory("Sanity");
	}
	
	public static void launchBrowser() {
		node = test.createNode("Launch Browser");
		switch (iBrowserType) {
		case 1:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the Chrome Browser");
			node.info("User Input is : "+iBrowserType+" ,So invoking the Chrome Browser");
			System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
		case 2:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the IE Browser");
			node.info("User Input is : "+iBrowserType+" ,So invoking the IE Browser");
			System.setProperty("webdriver.ie.driver","./driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
			
		case 3:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the Firefox Browser");
			node.info("User Input is : "+iBrowserType+" ,So invoking the Firefox Browser");
			System.setProperty("webdriver.gecko.driver","./driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
			
		case 4:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the Edge Browser");
			node.info("User Input is : "+iBrowserType+" ,So invoking the Edge Browser");
			System.setProperty("webdriver.edge.driver","./driver/msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("User Input is wrong : "+iBrowserType+" ,So invoking the Default Chrome Browser");
			node.info("User Input is wrong : "+iBrowserType+" ,So invoking the Default Chrome Browser");
			System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
	}
	
	public static void browserSetting() {
		
		node = test.createNode("Browser Setting");
		driver.manage().window().maximize();
		node.info("Browser Maximized");
		driver.manage().deleteAllCookies();
		node.info("Deleted All the Cookies");
	}
	
	public static void navigateUrl() {
		
		node = test.createNode("Navigate URL");
		driver.get(sURL);
		node.info("User Navigateded to : "+sURL);
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	}
	
	public static void getPageInfo() throws Exception {
		node = test.createNode("Validating the Page");
		System.out.println("Get the Current URL : "+driver.getCurrentUrl());
		node.info("User Capturing the Current URL : "+sURL);
		System.out.println("Get the Title : "+driver.getTitle());
		node.info("User Capturing the Title URL : "+sURL);
		String title = driver.getTitle();
		if(title.contains("ebay")) {
			getResult("SUCCESS","User Landed To the Right Page");
		}else {
			//test.fail("User Landed the Wrong Page",test.addScreenCaptureFromPath("D:\\Corporate Training\\Giving Force\\SeleniumTraining\\screenShot", "EbayFailure"));
			sPath = Library.takeScreenShotAsFileDyPath(driver, "Ebay");
			System.out.println(sPath);
			Thread.sleep(5000);
			getResult("FAILURE","User Landed To the Wrong Page");
			
		}
		
	}
	
	public static void searchProduct(String sSearchText,String sCat) {
		// Implicite Wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement oTxt,oDrop,oBtn;
		oTxt = driver.findElement(By.id("gh-ac"));
		oTxt.sendKeys(sSearchText);
		
		oDrop = driver.findElement(By.id("gh-cat"));
		Select oSelect = new Select(oDrop);
		//oSelect.selectByVisibleText(sCat);
		oSelect.selectByIndex(7);
		
		oBtn = driver.findElement(By.id("gh-btn"));
		oBtn.click();
		extent.flush();
	}
	
	
	public static void getResultText() {
		WebElement oResultText;
		oResultText = driver.findElement(By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]"));
		String sText = oResultText.getText();
		sText = sText.replaceAll("[^0-9]","");
		System.out.println("Customized Text is : "+sText);
		int iText = Integer.parseInt(sText);
		if(iText>0) {
			System.out.println("Search Results are Available");
			getResultValues();
		}else {
			System.out.println("Zero Search Results Found");
		}
	}
	
	public static void getResultValues() {
		WebElement oElement,oLink;
		List<WebElement> oList = driver.findElements(By.xpath("//ul[@class='srp-results srp-list clearfix']/li"));
		for(int i=0;i<oList.size();i++) {
			oElement = oList.get(i);
			oLink = oElement.findElement(By.xpath(".//div[@class='s-item__info clearfix']/a"));
			System.out.println(oLink.getText());
			int x = oLink.getLocation().getX();
			int y = oLink.getLocation().getY();		
			ScrollPageto(x, y);
		}
		ScrollPageto(0,0);
	}
	
	public static void closeBrowser() {
		driver.close();
	}
	
	 public static void ScrollPageto(int x,int y)
	 { 
	  JavascriptExecutor oJs; String
	  sCmd; oJs = (JavascriptExecutor) driver; sCmd =
	  String.format("window.scrollTo(%d,%d)",x,y); 
	  oJs.executeScript(sCmd);
	  
	  }
	
	 public static void getResult(String result,String sDesc) throws Exception {
	        if(result.equalsIgnoreCase("FAILURE")) {
	            node.log(Status.FAIL,sDesc,MediaEntityBuilder.createScreenCaptureFromPath(sPath).build());
	           // test.fail(sDesc, MediaEntityBuilder.createScreenCaptureFromPath(sPath).build());
	        }
	        else if(result.equalsIgnoreCase("SUCCESS")) {
	            node.log(Status.PASS,MarkupHelper.createLabel(result+" PASSED ", ExtentColor.GREEN));
	            node.pass(sDesc);
	        }
	        else {
	            node.log(Status.SKIP, MarkupHelper.createLabel(result+" SKIPPED ", ExtentColor.ORANGE));
	            node.skip(sDesc);
	        }
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	

}
