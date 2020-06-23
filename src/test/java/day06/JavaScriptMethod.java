package day06;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptMethod {

	public static void main(String[] args) throws Exception {

		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("http://newtours.demoaut.com/");

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		WebElement oText, oLink, oFirst;

		oText = driver.findElement(By.name("login"));
		
		JavascriptExecutor oExe = (JavascriptExecutor) driver; //Casting Driver to JavaScript
		//oExe.executeScript("arguments[0].style.border='3px solid red';", oText);
		//Thread.sleep(5000);
		//oExe.executeScript("arguments[0].style.border='red';", oText);
		highlightElement(driver, oText);
		oExe.executeScript("arguments[0].click();", oText);

		oLink = driver.findElement(By.xpath("//a[text()='REGISTER']"));

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		highlightElement(driver, oLink);
		oExe.executeScript("arguments[0].click();", oLink);

		oFirst = driver.findElement(By.name("firstName"));
		highlightElement(driver, oFirst);
		oExe.executeScript("arguments[0].value='Mathan';", oFirst);

		// Refresh the Page
		oExe.executeScript("history.go(0)");

		String sText = oExe.executeScript("return document.title;").toString();

		System.out.println("Title is : " + sText);

		String sURL = oExe.executeScript("return document.URL;").toString();

		System.out.println("URL is : " + sURL);

		
		  driver.get("https://www.hdfcbank.com/");
		  
		  driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		  Thread.sleep(5000); 
		  WebElement oPension,oMobile;
		  
		  oPension = driver.findElement(By.xpath("//*[text()='HDFC Pension']"));
		  oMobile = driver.findElement(By.xpath("//a[text()='MobileBanking ']"));
		  
		  int x = oMobile.getLocation().getX();
		  int y = oMobile.getLocation().getY();
		  ScrollPageto(driver, x, y);
		  highlightElement(driver, oMobile);
		  
		  
		  oExe.executeScript("arguments[0].scrollIntoView(true);", oPension);
		  highlightElement(driver, oPension);
		 

	}
	
	 public static void ScrollPageto(WebDriver driver,int x,int y)
	 { 
	  JavascriptExecutor oJs; String
	  sCmd; oJs = (JavascriptExecutor) driver; sCmd =
	  String.format("window.scrollTo(%d,%d)",x,y); 
	  oJs.executeScript(sCmd);
	  
	  }
	
	public static void highlightElement(WebDriver driver,WebElement oElement) throws InterruptedException {
		JavascriptExecutor oExe = (JavascriptExecutor) driver; //Casting Driver to JavaScript
		oExe.executeScript("arguments[0].style.border='3px solid red';", oElement);
		Thread.sleep(2000);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}