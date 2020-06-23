package day04;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class Actions_Ex {
	
	public static String sURL = "https://jqueryui.com/droppable/";
	public static int iBrowserType = 1; //1-Chrome,2-IE,3-FF
	public static WebDriver driver=null;
	public static void main(String[] args) {
		launchBrowser();
		browserSetting();
		navigateUrl();
		getPageInfo();
		dragAnddrop();
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
	
	public static void dragAnddrop() {
		Actions oAction;
		driver.switchTo().frame(0);
		WebElement oScr,oDes;
		oScr = driver.findElement(By.id("draggable"));
		oDes = driver.findElement(By.id("droppable"));
		oAction = new Actions(driver);
		//oAction.dragAndDrop(oScr, oDes).perform();
		//oAction.clickAndHold(oScr).moveToElement(oDes).release().build().perform();
		int x = oDes.getLocation().getX();
		int y = oDes.getLocation().getY();
		oAction.clickAndHold(oScr).moveByOffset(x, y).release().build().perform();
		//oAction.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
	}
	
	
	public static void closeBrowser() {
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
