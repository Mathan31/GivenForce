package day02;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class InteractingWebElement {
	
	public static String sURL = "https://www.selenium.dev/downloads/";
	public static int iBrowserType = 1; //1-Chrome,2-IE,3-FF
	public static WebDriver driver=null;
	public static void main(String[] args) {
		launchBrowser();
		browserSetting();
		navigateUrl();
		getPageInfo();
		clickDocument();
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
	
	
	public static void clickDocument() {
		WebElement oDoc,oSearch;
		//driver.findElement(By.xpath("//nav[@id='navbar']/a[text()='Documentation']")).click();
		oDoc = driver.findElement(By.xpath("//nav[@id='navbar']/a[text()='Documentation']"));
		oSearch = driver.findElement(By.xpath("//input[@name='search']"));
		String sFontSize = oDoc.getCssValue("font-size");
		System.out.println("Font Size is : "+sFontSize);
		
		String sFontcolor = oDoc.getCssValue("color");
		System.out.println("Font Color is : "+sFontcolor);
		int x = oDoc.getLocation().getX();
		int y = oDoc.getLocation().getY();
		System.out.println("X-Coordinate is : "+x+"  Y-Coordinate is : "+y);
		int height = oSearch.getSize().getHeight();
		int width = oSearch.getSize().getWidth();
		System.out.println("Height is : "+height+" Width is : "+width);
		String linkText = oDoc.getText();
		System.out.println("Link Text is : "+linkText);
		System.out.println("Document Link is Displayed : "+oDoc.isDisplayed());
		System.out.println("Document Link is Enabled : "+oDoc.isEnabled());
		oDoc.click();
		
	}
	public static void closeBrowser() {
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
