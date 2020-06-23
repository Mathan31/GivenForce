package test;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwitchToWindow {
	
	public static WebDriver driver;
	public static int iBrType = 1; //1 - Chrome,2 - FF,3 - IE
	public static String sURL = "https://www.hdfcbank.com/";

	public static void main(String[] args) throws Exception {
		browserInvoke();
		settingBrowser();
		navigatePage();
		getPageInfo();
		switchWindow();
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
	
	public static void navigatePage() throws Exception{
		driver.get(sURL);
		Thread.sleep(10000);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public static void getPageInfo(){
		System.out.println("Page Title is : "+driver.getTitle());
		System.out.println("Page Current URL is : "+driver.getCurrentUrl());
	}
	
	public static void switchWindow() throws Exception{
		
		WebElement oPension,oMutual,oLogin;
		WebDriverWait oWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		oLogin = driver.findElement(By.xpath("(//span[@class='hdfc-logo-icon'])[3]"));
		Library.takeSpecificScreenShotAsFile(driver, oLogin, "HdfcHomePension");
		//oClose = driver.findElement(By.xpath("//img[@class='popupCloseButton']"));
		//oWait.until(ExpectedConditions.elementToBeClickable(oClose));
		//oClose.click();
		Library.takeScreenShotAsFile(driver, "HdfcHomePage");
		Library.takeFullScreenShotAsFile(driver, "FullHdfcHomePage");
		oPension = driver.findElement(By.xpath("//*[text()='HDFC Pension']"));
		Actions oAction = new Actions(driver);
		oAction.moveToElement(oPension).build().perform();
		oWait.until(ExpectedConditions.elementToBeClickable(oPension));
		System.out.println("Window Count Before Clicking Pension Link :"+driver.getWindowHandles().size());
		oPension.click();
		driver.findElement(By.xpath("//a[text()='OK']")).click();
		oWait.until(ExpectedConditions.numberOfWindowsToBe(2));
		//Thread.sleep(3000);
		System.out.println("Window Count After Clicking Pension Link :"+driver.getWindowHandles().size());
		//driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		switchToWindow(driver);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		getPageInfo();
		Library.takeScreenShotAsBase(driver, "HdfcPensionPage");
		oMutual = driver.findElement(By.xpath("//*[text()='HDFC Mutual Fund']"));
		oAction.moveToElement(oMutual).build().perform();
		oWait.until(ExpectedConditions.elementToBeClickable(oMutual));
		System.out.println("Window Count Before Clicking Mutual Link :"+driver.getWindowHandles().size());
		oMutual.click();
		//Thread.sleep(3000);
		oWait.until(ExpectedConditions.numberOfWindowsToBe(3));
		System.out.println("Window Count After Clicking Mutual Link :"+driver.getWindowHandles().size());
		//driver.switchTo().window(driver.getWindowHandles().toArray()[2].toString());
		switchToWindow(driver);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		getPageInfo();
		Library.takeScreenShotAsByte(driver, "HdfcMutualPage");
		driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		getPageInfo();
		driver.close();
		Thread.sleep(3000);
		System.out.println("Window Count After Closing Home Page :"+driver.getWindowHandles().size());
	}
	
	public static void switchToWindow(WebDriver driver) throws InterruptedException {
		String currentWindow = driver.getWindowHandle();
		String newWindow = null;
		Set<String> allWindow = driver.getWindowHandles();
		//Thread.sleep(3000);
		if(allWindow.size()> 1) {
			for(String allHandler:allWindow) {
				if(!allHandler.equals(currentWindow)) {
					newWindow = allHandler;
				}
			}
			driver.switchTo().window(newWindow);
		}
	}
	
	public static void closeBrowser(){
		driver.close();
	}
	

	

}
