package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json_Ex2 {

	public static void main(String[] args) throws IOException, ParseException {
		FileReader oFile = new FileReader("./data/Complex.json");
		JSONParser oParser = new JSONParser();
		Object parseObj = oParser.parse(oFile);
		JSONObject jsonObj = (JSONObject)parseObj;
		String sFName = (String)jsonObj.get("firstName");
		System.out.println("First Name is : "+sFName);
		String sLName = (String)jsonObj.get("lastName");
		System.out.println("Last Name is : "+sLName);
		long iAge = (Long)jsonObj.get("age");
		System.out.println("Age is : "+iAge);
		Map<String,String>oMap = (Map)jsonObj.get("address");
		Set<Entry<String, String>> entrySet = oMap.entrySet();
		for(Entry<String, String> entry:entrySet) {
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
		System.out.println("=========Array========");
		JSONArray oArray = (JSONArray)jsonObj.get("phoneNumbers");
		System.out.println(oArray.size());
		
		  for(int i=0;i<oArray.size();i++) {
			 // System.out.println("Array Output : "+oArray.get(i)); 
			  JSONObject menuObj = (JSONObject)oArray.get(i);
			  String sValue = (String)menuObj.get("type");
			  String oAction = (String)menuObj.get("number");
			  System.out.println(sValue +"\t"+oAction);
		  }
		 
		oFile.close();
	}

}
