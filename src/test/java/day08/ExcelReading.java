package day08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReading {
	public static FileInputStream oFile;
	public static FileOutputStream oWrite;
	public static String sFile = "./data/Credo.xlsx";

	public static void main(String[] args) throws Exception {
		//getAllCellValues();
		readAllCellValuesAndWrite();
	}

	public static void getCellValueBasedonRowCell(int iRow,int iCell) throws Exception {
		// Build the Connection between Java and Excel
		oFile = new FileInputStream("./data/Credo.xlsx");
		// Select the XSSF only when the Excel is .xlsx
		// Select the HSSF only when the Excel is .xls
		XSSFWorkbook oWorkBook = new XSSFWorkbook(oFile);
		XSSFSheet oSheet = oWorkBook.getSheet("Details");
		Row oRow = oSheet.getRow(iRow);
		Cell oCell = oRow.getCell(iCell);
		double numericCellValue = oCell.getNumericCellValue();
		System.out.println("Value is : " + numericCellValue);
		oWorkBook.close();
		oFile.close();
	}
	
	public static void getAllCellValues() throws Exception {
		oFile = new FileInputStream("./data/Credo.xlsx");
		XSSFWorkbook oWorkBook = new XSSFWorkbook(oFile);
		XSSFSheet oSheet = oWorkBook.getSheetAt(0);
		Row oRow;
		Cell oCell;
		int lastRowNum = oSheet.getLastRowNum();
		System.out.println("Row Count is : "+lastRowNum);
		for(int i=0;i<=lastRowNum;i++) {
			oRow = oSheet.getRow(i);
			short lastCellNum = oRow.getLastCellNum();
			for(int j=0;j<lastCellNum;j++) {
				oCell = oRow.getCell(j);
				CellType cellType = oCell.getCellType();
				switch (cellType) {
				case NUMERIC:
					System.out.print(oCell.getNumericCellValue()+"\t");
					break;
					
				case STRING:
					System.out.print(oCell.getStringCellValue()+"\t");
					break;

				case BOOLEAN:
					System.out.print(oCell.getBooleanCellValue()+"\t");
					break;
				default:
					System.out.print("Error in Reading the Data ");
					break;
				}
			}
			System.out.println();
		}
		oWorkBook.close();
		oFile.close();
	}
	
	public static void readAllCellValuesAndWrite() throws Exception {
		oFile = new FileInputStream(sFile);
		XSSFWorkbook oWorkBook = new XSSFWorkbook(oFile);
		XSSFSheet oSheet = oWorkBook.getSheetAt(0);
		Row oRow;
		Cell oCell;
		int lastRowNum = oSheet.getLastRowNum();
		System.out.println("Row Count is : "+lastRowNum);
		for(int i=1;i<=lastRowNum;i++) {
			oRow = oSheet.getRow(i);
			short lastCellNum = oRow.getLastCellNum();
			for(int j=0;j<lastCellNum;j++) {
				oCell = oRow.getCell(j);
				CellType cellType = oCell.getCellType();
				switch (cellType) {
				case NUMERIC:
					System.out.print(oCell.getNumericCellValue()+"\t");
					break;
					
				case STRING:
					System.out.print(oCell.getStringCellValue()+"\t");
					break;

				case BOOLEAN:
					System.out.print(oCell.getBooleanCellValue()+"\t");
					break;
				default:
					System.out.print("Error in Reading the Data ");
					break;
				}
				
				if(j==2) {
					oSheet.getRow(i).createCell(j+1).setCellValue("Joined");
					oWrite = new FileOutputStream(sFile);
					oWorkBook.write(oWrite);
					oWrite.flush();
				}
			}
			System.out.println();
		}
		oWorkBook.close();
		oWrite.close();
		oFile.close();
	}
	
	
	
	

}
