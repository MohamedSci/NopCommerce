package data;

import java.io.*;
import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.*;
import org.apache.poi.ss.formula.functions.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonDataReader 
{

	public Object[][] getData() throws FileNotFoundException, FileNotFoundException {
		String filePath = System.getProperty("user.dir")+"/src/test/java/data/UserData.json";

		JsonElement jsonData =
				new JsonParser().parse(new FileReader(filePath));
		JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
		List<T> testData = new Gson().fromJson(dataSet, new TypeToken<List<T>>() {
		}.getType());
		Object[][] returnValue = new Object[testData.size()][1];
		int index = 0;
		for (Object[] each : returnValue) {
			each[0] = testData.get(index++);
		}
		return returnValue;
	}


//	public String firstname, lastname , email , password  ;
//
//	public void JsonReader() throws IOException, ParseException
//	{
//		String filePath = System.getProperty("user.dir")+"/src/test/java/data/UserData.json";
//
//		File srcFile = new File(filePath);
//
//		JSONParser parser = new JSONParser();
//		JSONArray jarray = (JSONArray)parser.parse(new FileReader(srcFile));
//
//		for(Object jsonObj : jarray)
//		{
//			JSONObject person = (JSONObject) jsonObj ;
//			firstname  = (String) person.get("firstname");
//			System.out.println(firstname);
//
//			lastname = (String) person.get("lastname");
//			System.out.println(lastname);
//
//			email = (String) person.get("email");
//			System.out.println(email);
//
//			password = (String) person.get("password");
//			System.out.println(password);
//
//		}
//
//	}

}
