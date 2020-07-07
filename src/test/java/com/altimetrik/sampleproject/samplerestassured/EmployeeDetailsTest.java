package com.altimetrik.sampleproject.samplerestassured;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import junit.framework.Assert;
public class EmployeeDetailsTest {
	
	
	@Test
	public void getAPITest(){
		Map<String,String> headersMap = new HashMap<String, String>();
		headersMap.put("Accept", "application/json");
		headersMap.put("Content-Type", "application/json");
		Map<String,String> cookiesMap = null;
		Map<String,String> queryParams = null;
		Response getResponse = RestAssuredUtil.getRequest(headersMap, cookiesMap, queryParams);
		//Fetch the details of employee Id : 1 from DB
		System.out.println("response GET : "+getResponse.getStatusCode());
		Assert.assertEquals(getResponse.getStatusCode(), "200");
	}
	
	@Test
	public void postEmployeeDetailsUsingPropertyFile() throws IOException{
		Map<String,String> headersMap = new HashMap<String, String>();
		headersMap.put("Accept", "application/json");
		Map<String,String> cookiesMap = null;
		Map<String,String> queryParams = null;
		System.out.println(System.getProperty("user.dir"));
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//test//resource//employee.properties");
		Properties prop = new Properties();
		prop.load(file);
		JsonObject employeeObj = new JsonObject();
		employeeObj.addProperty("id", prop.getProperty("id"));
		employeeObj.addProperty("name", prop.getProperty("name"));
		employeeObj.addProperty("salary", prop.getProperty("salary"));
		employeeObj.addProperty("age", prop.getProperty("age"));
		Response postResponse = RestAssuredUtil.postRequest(headersMap, cookiesMap, queryParams,employeeObj.toString());
		System.out.println("response : "+postResponse.getStatusCode());
		Assert.assertEquals(postResponse.getStatusCode(), "200");
	}
	
	
	@Test
	public void updateEmployeeDetailsTest() throws IOException{
		Map<String,String> headersMap = new HashMap<String, String>();
		headersMap.put("Accept", "application/json");
		Map<String,String> cookiesMap = null;
		Map<String,String> queryParams = null;
		System.out.println(System.getProperty("user.dir"));
		Employee updatedDetails = new Employee();
		updatedDetails.setId("4");
		updatedDetails.setAge("34");
		updatedDetails.setName("Altimetrik");
		updatedDetails.setSalary("1000");
		Response postResponse = RestAssuredUtil.putRequest(updatedDetails.getId(),headersMap, cookiesMap, queryParams, new Gson().toJson(updatedDetails));
		System.out.println("response : "+postResponse.getStatusCode());
		Assert.assertEquals(200,postResponse.getStatusCode());
	}
	
	
	
	@Test
	public void updateEmployeeDetailsUsingJsonTemplateTest() throws IOException{
		Map<String,String> headersMap = new HashMap<String, String>();
		headersMap.put("Accept", "application/json");
		Map<String,String> cookiesMap = null;
		Map<String,String> queryParams = null;
		System.out.println(System.getProperty("user.dir"));
		
		Gson gson = new Gson();
		Employee updatedDetails = gson.fromJson(new FileReader(System.getProperty("user.dir")+"//src//test//resource//employee.json"), Employee.class);
		updatedDetails.setId("8");
		updatedDetails.setAge("34");
		updatedDetails.setName("Altimetrik");
		updatedDetails.setSalary("1000");
		
		String afterModification = new Gson().toJson(updatedDetails);
		System.out.println(afterModification);
		
		Response postResponse = RestAssuredUtil.putRequest(updatedDetails.getId(),headersMap, cookiesMap, queryParams, afterModification);
		System.out.println("response : "+postResponse.getStatusCode());
		Assert.assertEquals(200,postResponse.getStatusCode());
	}
	
	
	
	@Test(dataProvider= "POSTData" , dataProviderClass =  APIDataProvider.class)
	public void updateEmployeeDetailsUsingDataProviderTest(String id, String name ,String age,String salary) throws IOException{
		
		Map<String,String> headersMap = new HashMap<String, String>();
		headersMap.put("Accept", "application/json");
		Map<String,String> cookiesMap = null;
		Map<String,String> queryParams = null;
		System.out.println(System.getProperty("user.dir"));
		
		Employee updatedDetails = new Employee();
		updatedDetails.setId(id);
		updatedDetails.setAge(age);
		updatedDetails.setName("Altimetrik");
		updatedDetails.setSalary("1000");
		
		String afterModification = new Gson().toJson(updatedDetails);
		System.out.println(afterModification);
		
		Response postResponse = RestAssuredUtil.putRequest(updatedDetails.getId(),headersMap, cookiesMap, queryParams, afterModification);
		System.out.println("response : "+postResponse.getStatusCode());
		Assert.assertEquals(200,postResponse.getStatusCode());
	}
	
	
	
}
