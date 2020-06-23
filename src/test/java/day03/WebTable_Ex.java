package day03;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebTable_Ex {
	
	public static String sURL = "http://newtours.demoaut.com/";
	public static int iBrowserType = 1; //1-Chrome,2-IE,3-FF
	public static WebDriver driver=null;
	public static void main(String[] args) {
		launchBrowser();
		browserSetting();
		navigateUrl();
		getPageInfo();
		getValues();
		getValuesBasedonXpath();
		//closeBrowser();
	}
	
	public static void launchBrowser() {
		switch (iBrowserType) {
		case 1:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the Chrome Browser");
			System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
		case 2:
			System.out.println("User Input is : "+iBrowserType+" ,So invoking the IE Browser");
			System.setProperty("webdriver.ie.driver","./driver/IEDriverServer.exe");
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
	
	
	public static void getValues() {
		WebElement oTable,oRow,oCol;
		oTable = driver.findElement(By.xpath("//img[@alt='Specials']/../../..//table/tbody"));
		List<WebElement> oRowList = oTable.findElements(By.tagName("tr"));
		for(int i=0;i<oRowList.size();i++) {
			oRow = oRowList.get(i);
			List<WebElement> oColList = oRow.findElements(By.tagName("td"));
			for(int j=0;j<oColList.size();j++) {
				oCol = oColList.get(j);
				System.out.print(oCol.getText()+" ");
			}
			System.out.println();
		}
		
	}
	
	public static void getValuesBasedonXpath() {
		WebElement oTable,oRow,oCol;
		oTable = driver.findElement(By.xpath("//img[@alt='Specials']/../../..//table/tbody"));
		List<WebElement> oRowList = oTable.findElements(By.xpath("./tr"));
		for(int i=0;i<oRowList.size();i++) {
			oRow = oRowList.get(i);
			List<WebElement> oColList = oRow.findElements(By.xpath("./td"));
			for(int j=0;j<oColList.size();j++) {
				oCol = oColList.get(j);
				System.out.print(oCol.getText()+" ");
			}
			System.out.println();
		}
		
	}
	
	/*
	 * List<WebElement> oList = driver.findElements(By.
	 * xpath("//ul[@class='srp-results srp-list clearfix']/li")); for(int
	 * i=0;i<oList.size();i++) { oElement = oList.get(i); oLink =
	 * oElement.findElement(By.xpath(".//div[@class='s-item__info clearfix']/a"));
	 * System.out.println(oLink.getText()); }
	 */
	public static void closeBrowser() {
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
