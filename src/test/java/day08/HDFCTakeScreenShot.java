package day08;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Library.Library;

public class HDFCTakeScreenShot {
	
	public static String sURL = "https://www.hdfcbank.com/";
	public static int iBrowserType = 1; //1-Chrome,2-IE,3-FF
	public static WebDriver driver=null;
	public static void main(String[] args) throws Exception {
		launchBrowser();
		browserSetting();
		navigateUrl();
		getPageInfo();
		switchWindow();
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
	
	public static void switchWindow() throws Exception {
		WebElement oPenion,oMutual;
		Actions oAction;
		WebDriverWait oWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Library.takeScreenshotAsFile(driver, "HDFCHomePage");
		  oWait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				  
		  "//span[text()='HDFC Pension']"))); 
		  oPenion = driver.findElement(By.xpath("//span[text()='HDFC Pension']"));
		  oAction = new Actions(driver);
		  oAction.moveToElement(oPenion).build().perform();
		  // To Know the Total No Of Window opened using current session
		  System.out.println("Before Clicking the Pension Link, Window Size is : "+driver.getWindowHandles().size());
		  System.out.println("Window Name is : "+driver.getWindowHandle());
		  oPenion.click();
		  driver.findElement(By.xpath("//a[text()='OK']")).click();
		  //Thread.sleep(3000);
		  oWait.until(ExpectedConditions.numberOfWindowsToBe(2));
		  System.out.println("After Clicking the Pension Link, Window Size is : "+driver.getWindowHandles().size());
		  driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		  getPageInfo();
		  Library.takeScreenshotAsBase(driver, "HDFC Pension");
		  oWait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				  "//a[text()='HDFC Mutual Fund']")));
		  oMutual = driver.findElement(By.xpath("//a[text()='HDFC Mutual Fund']"));
		  oAction.moveToElement(oMutual);
		  System.out.println("Before Clicking the Mutual Link, Window Size is : "+driver.getWindowHandles().size());
		  oMutual.click();
		  //Thread.sleep(3000);
		  oWait.until(ExpectedConditions.numberOfWindowsToBe(3));
		  System.out.println("After Clicking the Mutual Link, Window Size is : "+driver.getWindowHandles().size());
		  driver.switchTo().window(driver.getWindowHandles().toArray()[2].toString());  
		  getPageInfo();
		  Library.takeScreenshotAsFileWithTimeStamp(driver, "HDFC Mutual");
		  driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());  
		  getPageInfo();
		  driver.close();
		  System.out.println("After Closing Home Page , Window Size is : "+driver.getWindowHandles().size());
		  driver.quit();
	}
	
	
	public static void closeBrowser() {
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
