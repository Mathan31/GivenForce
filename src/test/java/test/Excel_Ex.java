package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Ex {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		readExcel();
	}
	
	public static void readExcel() throws Exception {
		FileInputStream oExcel = new FileInputStream("./data/Credo.xlsx");
		
		XSSFWorkbook oWB = new XSSFWorkbook(oExcel);
		XSSFSheet oSheet = oWB.getSheet("Details");
		XSSFRow oRow;
		XSSFCell oCell;
		int lastRowNum = oSheet.getLastRowNum();
		System.out.println("Last Row Count is : "+lastRowNum);
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
					System.out.println("Error in Reading Data");
					break;
				}
				
				  if(j==2) { 
					  oSheet.getRow(i).createCell(j+1).setCellValue("Joined"); 
				  //oCell
				  FileOutputStream oWrite = new
				  FileOutputStream("./data/Credo.xlsx"); 
				  oWB.write(oWrite);
				  //oWrite.flush();
				  }
				 
			}
			System.out.println();
		}
		
		oWB.close();
	}

}
