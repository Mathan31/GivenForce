package day06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileSystemObject {

	public static void main(String[] args) throws Exception  {
		
			//fileEx();
		 	//fileBasicReader();
			fileBasicInputStream();
			//fileWritter();
			//fileWritterCopy();
			//fileStreamCopy();
			//bufferedWritterReader();
			//printWritter();
			//scanner_Ex();
		
	}
	
	public static void fileEx() throws Exception{
		File oDir = new File("D:\\File");
		if(oDir.exists()){
			System.out.println("Folder Already Created");	
		}else{
			oDir.mkdirs();
			System.out.println("Folder Created");
		}
		
		File oFile = new File(oDir, "file.txt");
		if(oFile.exists()){
			System.out.println("Text File Already Exist");
		}else{
			System.out.println("File is not Existing, So Creating the File");
			oFile.createNewFile();
			
		}
		
	}
	
	public static void fileBasicReader() throws IOException{
		File oDir = new File("D:\\File");
		if(oDir.exists()){
			System.out.println("Folder Already Created");	
		}else{
			oDir.mkdirs();
			System.out.println("Folder Created");
		}
		
		File oFile = new File(oDir, "fileWritter.txt");
		if(oFile.exists()){
			System.out.println("Text File Already Exist");
		}else{
			System.out.println("File is not Existing, So Creating the File");
			oFile.createNewFile();
		}
		
		FileReader oRead = new FileReader(oFile);
		int iRead1 = oRead.read();
		int iRead2 = oRead.read();
		int iRead3 = oRead.read();
		System.out.println(iRead1+"\t"+iRead2+"\t"+iRead3);
		oRead.close();
	}
	
	public static void fileBasicInputStream() throws IOException{
		File oDir = new File("D:\\File");
		if(oDir.exists()){
			System.out.println("Folder Already Created");	
		}else{
			oDir.mkdirs();
			System.out.println("Folder Created");
		}
		
		File oFile = new File(oDir, "fileWritter.txt");
		if(oFile.exists()){
			System.out.println("Text File Already Exist");
		}else{
			System.out.println("File is not Existing, So Creating the File");
			oFile.createNewFile();
		}
		
		FileInputStream oRead = new FileInputStream(oFile);
		int iRead1 = oRead.read();
		int iRead2 = oRead.read();
		int iRead3 = oRead.read();
		System.out.println(iRead1+"\t"+iRead2+"\t"+iRead3);
		oRead.close();
	}
	
	
	public static void fileWritter() throws IOException{
		File oDir = new File("D:\\File");
		if(oDir.exists()){
			System.out.println("Folder Already Created");	
		}else{
			oDir.mkdirs();
			System.out.println("Folder Created");
		}
		
		File oFile = new File(oDir, "fileWritter.txt");
		if(oFile.exists()){
			System.out.println("Text File Already Exist");
		}else{
			System.out.println("File is not Existing, So Creating the File");
			oFile.createNewFile();
		}
		
		FileWriter oWrite = new FileWriter(oFile, false);
		oWrite.write("This is a Java Session");
		oWrite.write(65);
		oWrite.write("Followed By Selenium Session");
		oWrite.flush();
		oWrite.close();
		
		/*
		 * System.out.println("========File Input Stream======"); FileInputStream
		 * oStream = new FileInputStream(oFile); int read = oStream.read();
		 * while(read!=-1){ System.out.print(read); read = oStream.read(); }
		 * oStream.close();
		 */
		System.out.println();
		System.out.println("=====File Reader=======");
		FileReader oRead = new FileReader(oFile);
		int iRead = oRead.read();
		while(iRead!=-1){
			System.out.print(iRead);
			iRead = oRead.read();
		}
		oRead.close();
	}
	
	
	public static void fileWritterCopy() throws IOException{
		File oDir = new File("D:\\File");
		if(oDir.exists()){
			System.out.println("Folder Already Created");	
		}else{
			oDir.mkdirs();
			System.out.println("Folder Created");
		}
		
		File oFileCopy = new File(oDir, "fileReaderCopy.txt");
		File oFileCopywrite = new File(oDir, "fileWritterCopy.txt");
		FileReader oRead = new FileReader(oFileCopy);
		FileWriter oWrite = new FileWriter(oFileCopywrite);
		int iRead = oRead.read();
		while(iRead!=-1){
			System.out.print((char)iRead);
			oWrite.write(iRead);
			iRead = oRead.read();
		}
		oWrite.flush();
		oRead.close();
		oWrite.close();
		/*
		 * System.out.println("========File Input Stream======"); FileInputStream
		 * oStream = new FileInputStream(oFile); int read = oStream.read();
		 * while(read!=-1){ System.out.print(read); read = oStream.read(); }
		 * oStream.close();
		 */
		
	}
	
	public static void fileStreamCopy() throws IOException{
		File oDir = new File("D:\\File");
		if(oDir.exists()){
			System.out.println("Folder Already Created");	
		}else{
			oDir.mkdirs();
			System.out.println("Folder Created");
		}
		
		File oFileCopy = new File(oDir, "fileReaderCopy.txt");
		File oFileCopywrite = new File(oDir, "fileStreamCopy.txt");
		FileInputStream oRead = new FileInputStream(oFileCopy);
		FileOutputStream oWrite = new FileOutputStream(oFileCopywrite);
		int iRead = oRead.read();
		while(iRead!=-1){
			System.out.print((char)iRead);
			oWrite.write(iRead);
			iRead = oRead.read();
		}
		oWrite.flush();
		oRead.close();
		oWrite.close();
		/*
		 * System.out.println("========File Input Stream======"); FileInputStream
		 * oStream = new FileInputStream(oFile); int read = oStream.read();
		 * while(read!=-1){ System.out.print(read); read = oStream.read(); }
		 * oStream.close();
		 */
		
	}
	public static void bufferedWritterReader() throws Exception{
		File oDir = new File("D:\\File");
		if(oDir.exists()){
			System.out.println("Folder Already Created");	
		}else{
			oDir.mkdirs();
			System.out.println("Folder Created");
		}
		
		File oFile = new File(oDir, "bufferedWritter.txt");
		if(oFile.exists()){
			System.out.println("Text File Already Exist");
		}else{
			System.out.println("File is not Existing, So Creating the File");
			oFile.createNewFile();
		}
		
		FileWriter oWrite = new FileWriter(oFile, false);
		BufferedWriter oBWrite = new BufferedWriter(oWrite);
		oBWrite.write("This is Java Session");
		oBWrite.newLine();
		oBWrite.write(65);
		oBWrite.newLine();
		oBWrite.write("Followed By Selenium Session");
		oBWrite.flush();
		oBWrite.close();
		FileReader oRead = new FileReader(oFile);
		BufferedReader oBRead = new BufferedReader(oRead);
		String sRead = oBRead.readLine();
		while(sRead!=null){
			System.out.println(sRead);
			sRead = oBRead.readLine();
		}
		oBRead.close();
	}
	
	public static void printWritter() throws Exception{
		File oDir = new File("D:\\File");
		if(oDir.exists()){
			System.out.println("Folder Already Created");	
		}else{
			oDir.mkdirs();
			System.out.println("Folder Created");
		}
		
		File oFile = new File(oDir, "printWritter.txt");
		if(oFile.exists()){
			System.out.println("Text File Already Exist");
		}else{
			System.out.println("File is not Existing, So Creating the File");
			oFile.createNewFile();
		}
		
		FileWriter oWrite = new FileWriter(oFile, false);
		PrintWriter oPrint = new PrintWriter(oWrite);
		oPrint.println("This is Java Session");
		oPrint.println(65);
		oPrint.println("Followed By Selenium Session");
		oPrint.println(10.5);
		oPrint.println(true);
		oPrint.flush();
		oPrint.close();
		FileReader oRead = new FileReader(oFile);
		BufferedReader oBRead = new BufferedReader(oRead);
		String sRead = oBRead.readLine();
		while(sRead!=null){
			System.out.println(sRead);
			sRead = oBRead.readLine();
		}
		oBRead.close();
	}
	
	public static void scanner_Ex(){
		Scanner oScan = new Scanner(System.in);
		System.out.println("Enter the User Details :");
		String sName = oScan.nextLine();
		System.out.println("Name is : "+sName);
		System.out.println("Enter your Age : ");
		int iAge = oScan.nextInt();
		System.out.println("Age is : "+iAge);
		oScan.close();
	}
	
	
		
	

}
