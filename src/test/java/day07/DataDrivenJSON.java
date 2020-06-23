package day07;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataDrivenJSON {

	public static void main(String[] args) throws Exception {
		//Building the Connnection B/W Java and File
		FileReader oFile = new FileReader("./data/Simple.json");
		JSONParser oParse = new JSONParser();
		Object oparseObj = oParse.parse(oFile);
		JSONObject jsonObj = (JSONObject)oparseObj; //Type Casting the Object to Json Object
		String sName = (String)jsonObj.get("name");
		long id = (Long)jsonObj.get("id");
		System.out.println("User Name is : "+sName);
		System.out.println("ID is : "+id);
		JSONArray oArray = (JSONArray)jsonObj.get("menuitem");
		System.out.println("Array Size is : "+oArray.size());
		System.out.println(oArray);
		System.out.println("==========");
		  for(int i=0;i<oArray.size();i++) {
			  JSONObject menuObj = (JSONObject) oArray.get(i);
			  String sValue = (String)menuObj.get("value");
			  String sClick = (String)menuObj.get("onclick");
			  System.out.println(sValue+"\t"+sClick);
		  }
		 
		
		oFile.close();
	}

}
