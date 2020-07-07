package com.altimetrik.sampleproject.samplerestassured;



import org.testng.annotations.DataProvider;

public class APIDataProvider {

	@DataProvider(name="POSTData")
	public static Object[][] getData(){
		Object[][] data = testData();
		return data;
	}
	
	public static Object[][] testData(){
		 return new Object[][] {{"1", "gagan", "34", "1000"}} ;
	}
	
}
