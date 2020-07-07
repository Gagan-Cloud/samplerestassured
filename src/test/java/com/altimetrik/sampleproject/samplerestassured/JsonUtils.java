package com.altimetrik.sampleproject.samplerestassured;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;

public class JsonUtils {
	
	public static String getStringFromJsonPath(String jsonResponse, String jsonPath) {
		return JsonPath.read(jsonResponse, jsonPath, new Predicate[0]) + "";
	}

	public static List<String> getListOfStringFromJsonPath(String jsonResponse, String jsonPath) {
		List<String> output = new ArrayList<String>();
		Collection<? extends String> array = (Collection) JsonPath.read(jsonResponse, jsonPath, new Predicate[0]);
		if (array.size() > 0) {
			Iterator val = array.iterator();
			while (val.hasNext()) {
				Object ob = val.next();
				output.add(ob.toString());
			}
		}
		return output;
	}
	
	public static <T> String getJsonStringFromJava(T javaObject){
		Gson gson = new Gson();
		String jsonString = gson.toJson(javaObject);
		return jsonString;
	}

}
