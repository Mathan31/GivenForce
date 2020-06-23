package day06;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class Ebay_Select_BrowserSetting {
	
	public static String sURL = "https://www.ebay.com/";
	public static int iBrowserType = 1; //1-Chrome,2-IE,3-FF
	public static WebDriver driver=null;
	public static ChromeOptions option;
	public static InternetExplorerOptions oIE;
	public static Proxy oproxy;
	public static void main(String[] args) {
		launchBrowser();
		browserSetting();
		navigateUrl();
		getPageInfo();
		searchProduct("iPhone", "Cell Phones & Accessories");
		getResultText();
		
		
		//closeBrowser();
	}
	
	public static void launchBrowser() {
		switch (iBrowserType) {
		case 1:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the Chrome Browser");
			System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
			option = new ChromeOptions();
			option.addArguments("start-maximized");
			//option.addArguments("ignore-certificate-errors");
			option.setAcceptInsecureCerts(true);
			option.setHeadless(true);
			oproxy = new Proxy();
			//System.out.println("FTP Proxy : "+oproxy.getFtpProxy());
			//System.out.println("HTTP Proxy : "+oproxy.getHttpProxy());
			//oproxy.setFtpProxy("10.12.255.5");
			DesiredCapabilities oCap = new DesiredCapabilities();
			oCap.setCapability("proxy", oproxy);
			option.merge(oCap);
			driver = new ChromeDriver(option);
			break;
			
		case 2:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the IE Browser");
			System.setProperty("webdriver.ie.driver","./driver/IEDriverServer.exe");
			oIE = new InternetExplorerOptions();
			driver = new InternetExplorerDriver();
			break;
			
		case 3:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the Firefox Browser");
			System.setProperty("webdriver.gecko.driver","./driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
			
		case 4:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the Edge Browser");
			System.setProperty("webdriver.edge.driver","./driver/msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("User Input is wrong : "+iBrowserType+" ,So invoking the Default Chrome Browser");
			System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
	}
	
	public static void browserSetting() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public static void navigateUrl() {
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	}
	
	public static void getPageInfo() {
		System.out.println("Get the Current URL : "+driver.getCurrentUrl());
		System.out.println("Get the Title : "+driver.getTitle());
		
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
		}else {
			System.out.println("Zero Search Results Found");
		}
		
	}
	public static void closeBrowser() {
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
