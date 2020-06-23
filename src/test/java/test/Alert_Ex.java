package test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Alert_Ex {
	
	public static WebDriver driver;
	public static int iBrType = 1; //1 - Chrome,2 - FF,3 - IE
	public static String sURL = "https://www.w3schools.com/js/tryit.asp?filename=tryjs_alert";

	public static void main(String[] args) {
		browserInvoke();
		settingBrowser();
		navigatePage();
		getPageInfo();
		alertHandling();
		//closeBrowser();
	}
	
	public static void browserInvoke(){
		switch(iBrType){
		case 1:
			System.out.println("User Selection is : "+iBrType+",So invoking Chrome Browser");
			System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
		case 2:
			System.out.println("User Selection is : "+iBrType+",So invoking FF Browser");
			System.setProperty("webdriver.gecko.driver","./Driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case 3:
			System.out.println("User Selection is : "+iBrType+",So invoking IE Browser");
			System.setProperty("webdriver.ie.driver","./Driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
			
		default:
			System.out.println("User Selection is wrong : "+iBrType+",So invoking Default Chrome Browser");
			System.setProperty("webdriver.chrome.driver","D:\\Software\\Selenium\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}
	}
	
	public static void settingBrowser(){
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public static void navigatePage(){
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public static void getPageInfo(){
		System.out.println("Page Title is : "+driver.getTitle());
		System.out.println("Page Current URL is : "+driver.getCurrentUrl());
	}
	
	public static void alertHandling(){
		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("//button[text()='Try it']")).click();
		WebDriverWait oWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		oWait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}
	
	public static void closeBrowser(){
		driver.close();
	}
	

	

}
