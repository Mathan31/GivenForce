package day07;

import java.io.FileInputStream;
import java.util.Properties;

public class DataDrivenPropertiesFile {

	public static void main(String[] args) throws Exception {
		//Storing the File Location
		String sFile = "./data/environment.properties";
		
		//Creating the Object for Getting Connection with Property File
		FileInputStream oFile = new FileInputStream(sFile);
		
		//Create the Object for Properties Class
		Properties oPro = new Properties();
		oPro.load(oFile);
		String sUser = oPro.getProperty("userName");
		System.out.println("UserName is : "+sUser);
		String sPass = oPro.getProperty("passWord");
		System.out.println("Password is : "+sPass);
		oFile.close();
	}

}
