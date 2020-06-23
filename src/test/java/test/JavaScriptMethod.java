package test;

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

		// driver.manage().window().maximize();

		driver.get("http://newtours.demoaut.com/");

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		WebElement oText, oLink, oFirst;

		oText = driver.findElement(By.name("login"));

		JavascriptExecutor oExe = (JavascriptExecutor) driver;
		oExe.executeScript("arguments[0].style.border='3px solid red';", oText);
		//oExe.executeScript("arguments[0].style.border='red';", oText);
		oExe.executeScript("arguments[0].click();", oText);

		oLink = driver.findElement(By.xpath("//a[text()='REGISTER']"));

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		oExe.executeScript("arguments[0].click();", oLink);

		oFirst = driver.findElement(By.name("firstName"));

		oExe.executeScript("arguments[0].value='Mathan';", oFirst);

		// Refresh the Page
		oExe.executeScript("history.go(0)");

		String sText = oExe.executeScript("return document.title;").toString();

		System.out.println("Title is : " + sText);

		String sURL = oExe.executeScript("return document.URL;").toString();

		System.out.println("URL is : " + sURL);

		/*
		 * driver.get("https://www.hdfcbank.com/");
		 * 
		 * driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		 * Thread.sleep(5000); WebElement oPension;
		 * 
		 * oPension = driver.findElement(By.xpath("//a[text()='HDFC Pension']"));
		 * 
		 * oExe.executeScript("arguments[0].scrollIntoView(true);", oPension);
		 * public static void ScrollPageto(int x,int y){
		JavascriptExecutor oJs;
		String sCmd;
		oJs = (JavascriptExecutor) driver;
		sCmd = String.format("window.scrollTo(%d,%d)",x,y);
		oJs.executeScript(sCmd);
			
	}
		 */

	}

}