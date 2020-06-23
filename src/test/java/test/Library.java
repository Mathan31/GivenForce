package test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Library {

	public static void takeScreenShotAsFile(WebDriver driver, String sFileName) throws Exception {

		TakesScreenshot oShot = (TakesScreenshot) driver;
		File scr = oShot.getScreenshotAs(OutputType.FILE);
		File des = new File("./screenShot/" + sFileName + ".png");
		FileUtils.copyFile(scr, des);
		
		//File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(screenshotFile, new File("path of the file you want to save the screenshot to"));
	}
	
	public static String takeScreenShotAsFileDyPath(WebDriver driver,String sFileName) throws Exception {

		TakesScreenshot oShot = (TakesScreenshot) driver;
		File scr = oShot.getScreenshotAs(OutputType.FILE);
		String sPath = System.getProperty("user.dir")+"/screenshot/"+sFileName+System.currentTimeMillis()+".png";
		File des = new File(sPath);
		FileUtils.copyFile(scr, des);
		return sPath;
	}

	public static void takeScreenShotAsBase(WebDriver driver, String sFileName) throws Exception {

		TakesScreenshot oShot = (TakesScreenshot) driver;
		String screenshotAs = oShot.getScreenshotAs(OutputType.BASE64);
		File scr = OutputType.FILE.convertFromBase64Png(screenshotAs);
		File des = new File("./screenShot/" + sFileName + ".png");
		FileUtils.copyFile(scr, des);
	}

	public static void takeScreenShotAsByte(WebDriver driver, String sFileName) throws Exception {

		TakesScreenshot oShot = (TakesScreenshot) driver;
		byte[] screenshotAs = oShot.getScreenshotAs(OutputType.BYTES);
		File scr = OutputType.FILE.convertFromPngBytes(screenshotAs);
		File des = new File("./screenShot/" + sFileName + ".png");
		FileUtils.copyFile(scr, des);
	}

	public static void takeSpecificScreenShotAsFile(WebDriver driver,WebElement oElement, String sFileName) throws Exception {

		TakesScreenshot oShot = (TakesScreenshot) driver;
		File scr = oShot.getScreenshotAs(OutputType.FILE);
		
		int x = oElement.getLocation().getX();
		int y = oElement.getLocation().getY();
		int height = oElement.getSize().getHeight();
		int width = oElement.getSize().getWidth();
		BufferedImage img = ImageIO.read(scr);
		BufferedImage destination = img.getSubimage(x, y, width, height);
		ImageIO.write(destination, "png", scr);
		File des = new File("./screenShot/" + sFileName + ".png");
		FileUtils.copyFile(scr, des);
	}
	public static void takeFullScreenShotAsFile(WebDriver driver, String sFileName) throws Exception {

		Screenshot oShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		File des = new File("./screenShot/" + sFileName + ".png");
		ImageIO.write(oShot.getImage(), "png", des);
	}

}
