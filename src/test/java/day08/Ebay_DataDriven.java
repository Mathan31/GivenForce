package day08;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Ebay_DataDriven {

	public static WebDriver driver;

	public static int iRow, iTotalRow, iCell, iTotalCell;
	public static String sExcelFile = "./data/Ebay.xlsx";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String sSheet, sSearchTxt;
		sSheet = "Search";
		Page_Info();
		try {
			InputStream oFile = new FileInputStream(sExcelFile);
			XSSFWorkbook oExcel = new XSSFWorkbook(oFile);
			XSSFSheet oSheet = oExcel.getSheet(sSheet);
			Row oRow;
			Cell oCell;
			iTotalRow = oSheet.getLastRowNum();
			for (iRow = 1; iRow <= iTotalRow; iRow++) {
				oRow = oSheet.getRow(iRow);
				iTotalCell = oRow.getLastCellNum();
				for (iCell = 0; iCell < iTotalCell; iCell++) {
					oCell = oRow.getCell(iCell);
					sSearchTxt = oCell.getStringCellValue();

					Search_Product(sSearchTxt, "Cell Phones & Accessories");
					Get_Match(sSearchTxt);
				}

			}
			oExcel.close();
			oFile.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void Page_Info() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.ebay.com/");
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);

	}

	public static void Search_Product(String sTxt, String sCat) {
		WebElement oText, oBtn, oDropDown;

		oText = driver.findElement(By.xpath("//*[@id='gh-ac']"));
		boolean oResult = oText.isDisplayed();
		oText.clear();
		oText.sendKeys(sTxt);
		oDropDown = driver.findElement(By.xpath("//*[@id='gh-cat']"));
		Select oSelect = new Select(oDropDown);
		oSelect.selectByVisibleText(sCat);

		oBtn = driver.findElement(By.xpath("//*[@id='gh-btn']"));
		oBtn.click();

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

	}

	public static void Get_Match(String sTxt) throws Exception {
		WebElement oText, oProduct;
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		oText = driver.findElement(By.xpath("//*[@class='listingscnt']|//*[@class='srp-controls__count-heading']"));
		String sText = oText.getText();
		System.out.println("Search Result is : " + sText);
		sText = sText.replaceAll("[^0-9]", "").trim();
		int iText = Integer.parseInt(sText);
		if (iText > 0) {
			System.out.println("Search Result is Listed");
		} else {
			System.out.println("No Search Result");
		}

		List<WebElement> oList = driver
				.findElements(By.xpath("//ul[@class='srp-results srp-list clearfix']/li|//*[@id='ListViewInner']/li"));
		System.out.println("Total Value is : " + oList.size());
		for (int i = 0; i < oList.size(); i++) {
			oProduct = oList.get(i);
			// sVal1 will be having Phone Name
			// sVal2 will be having Phone Price
			String sVal1 = oProduct.findElement(By.xpath(".//a[@class='s-item__link']/h3|.//a[@class='vip']"))
					.getText();
			String sVal2 = oProduct.findElement(By.xpath(".//span[@class='s-item__price']")).getText();
			System.out.println(sVal1);
			System.out.println(sVal2);
			int x = oProduct.getLocation().x;
			int y = oProduct.getLocation().y;
			ScrollPageto(x, y);
			// 1St Param is File Location
			// 2nd Param is Sheet Name Ex : iPhone,Samsung or Nokia
			// 3rd Param is Row Number
			// 4th Param is Cell Number
			// 5th Param sVal1 orsVal2
			Write_Cell_Value_To_Excel(sExcelFile, sTxt, i, 0, sVal1);
			Write_Cell_Value_To_Excel(sExcelFile, sTxt, i, 1, sVal2);
		}
		ScrollPageto(0, 0);

	}

	public static void ScrollPageto(int x, int y) {
		JavascriptExecutor oJs;
		String sCmd;
		oJs = (JavascriptExecutor) driver;
		sCmd = String.format("window.scrollTo(%d,%d)", x, y);
		oJs.executeScript(sCmd);

	}

	public static void Write_Cell_Value_To_Excel(String sFile, String sSheet, int iRow, int iCell, String sValue) {
		// 1St Param is File Location
		// 2nd Param is Sheet Name Ex : iPhone,Samsung or Nokia
		// 3rd Param is Row Number
		// 4th Param is Cell Number
		// 5th Param sVal1 orsVal2

		InputStream oFile;
		XSSFWorkbook oExcel;
		XSSFSheet oSheet;
		Row oRow;
		Cell oCell;
		try {

			oFile = new FileInputStream(sFile);
			oExcel = new XSSFWorkbook(oFile);
			oSheet = oExcel.getSheet(sSheet);
			if (oSheet == null) {
				oExcel.createSheet(sSheet);
				oSheet = oExcel.getSheet(sSheet);
			}

			oRow = oSheet.getRow(iRow);
			if (oRow == null) {
				oSheet.createRow(iRow);
				oRow = oSheet.getRow(iRow);
			}

			oCell = oRow.getCell(iCell);

			if (oCell == null) {
				oRow.createCell(iCell);
				oCell = oRow.getCell(iCell);
			}

			oCell.setCellValue(sValue);

			OutputStream oFileWrite = new FileOutputStream(sFile);
			oExcel.write(oFileWrite);
			oFileWrite.close();

			oExcel.close();
			oFile.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
