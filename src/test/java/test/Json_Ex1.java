package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json_Ex1 {

	public static void main(String[] args) throws IOException, ParseException {
		FileReader oFile = new FileReader("./data/Simple.json");
		JSONParser oParser = new JSONParser();
		Object parseObj = oParser.parse(oFile);
		JSONObject jsonObj = (JSONObject)parseObj;
		long sID = (Long)jsonObj.get("id");
		System.out.println(sID);
		String sName = (String)jsonObj.get("name");
		System.out.println("Name is : "+sName);
		JSONArray oArray = (JSONArray)jsonObj.get("menuitem");
		System.out.println(oArray.size());
		
		  for(int i=0;i<oArray.size();i++) {
			 // System.out.println("Array Output : "+oArray.get(i)); 
			  JSONObject menuObj = (JSONObject)oArray.get(i);
			  String sValue = (String)menuObj.get("value");
			  String oAction = (String)menuObj.get("onclick");
			  System.out.println(sValue +"\t"+oAction);
		  }
		 
		oFile.close();
	}

}
