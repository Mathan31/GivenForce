package day07;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataDrivenJSON2 {

	public static void main(String[] args) throws Exception {
		//Building the Connnection B/W Java and File
		FileReader oFile = new FileReader("./data/Complex.json");
		JSONParser oParse = new JSONParser();
		Object oparseObj = oParse.parse(oFile);
		JSONObject jsonObj = (JSONObject)oparseObj; //Type Casting the Object to Json Object
		String sFName = (String)jsonObj.get("firstName");
		String sLName = (String)jsonObj.get("lastName");
		long age = (Long)jsonObj.get("age");
		System.out.println("User First Name is : "+sFName);
		System.out.println("User Last Name is : "+sLName);
		System.out.println("User Age is  : "+age);
		
		Map<String, String>	oMap = (Map)jsonObj.get("address");
		Set<Entry<String, String>> entrySet = oMap.entrySet();
		for(Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
		System.out.println("============Array===========");
		JSONArray oArray = (JSONArray)jsonObj.get("phoneNumbers");
		System.out.println("Array Size is : "+oArray.size());
		System.out.println(oArray);
		System.out.println("==========");
		  for(int i=0;i<oArray.size();i++) {
			  JSONObject menuObj = (JSONObject) oArray.get(i);
			  String sValue = (String)menuObj.get("type");
			  String sClick = (String)menuObj.get("number");
			  System.out.println(sValue+"\t"+sClick);
		  }
		 
		
		oFile.close();
	}

}
