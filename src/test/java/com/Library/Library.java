package com.Library;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Library {
	
	public static void takeScreenshotAsFile(WebDriver driver,String sFileName) throws Exception {
		TakesScreenshot oShot = (TakesScreenshot)driver;
		File scr = oShot.getScreenshotAs(OutputType.FILE);
		File des = new File("./screenshot/"+sFileName+".png");
		FileUtils.copyFile(scr, des);
		
	}
	
	public static String takeScreenshotAsFileDynamicPath(WebDriver driver,String sFileName) throws Exception {
		TakesScreenshot oShot = (TakesScreenshot)driver;
		File scr = oShot.getScreenshotAs(OutputType.FILE);
		String sPath = System.getProperty("user.dir")+"/screenshot/"+sFileName+System.currentTimeMillis()+".png";
		File des = new File(sPath);
		FileUtils.copyFile(scr, des);
		return sPath;
	}
	
	public static void takeScreenshotAsFileWithTimeStamp(WebDriver driver,String sFileName) throws Exception {
		TakesScreenshot oShot = (TakesScreenshot)driver;
		File scr = oShot.getScreenshotAs(OutputType.FILE);
		File des = new File("./screenshot/"+sFileName+System.currentTimeMillis()+".png");
		FileUtils.copyFile(scr, des);
		
	}
	
	public static void takeScreenshotAsBase(WebDriver driver,String sFileName) throws Exception {
		TakesScreenshot oShot = (TakesScreenshot)driver;
		String sBase = oShot.getScreenshotAs(OutputType.BASE64);
		File scr = OutputType.FILE.convertFromBase64Png(sBase);
		File des = new File("./screenshot/"+sFileName+".png");
		FileUtils.copyFile(scr, des);
		
	}
	
	public static void takeFullScreenShotAsFile(WebDriver driver, String sFileName) throws Exception {

		Screenshot oShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		File des = new File("./screenShot/" + sFileName + ".png");
		ImageIO.write(oShot.getImage(), "png", des);
	}

}
