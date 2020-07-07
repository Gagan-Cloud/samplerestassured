package com.altimetrik.sampleproject.samplerestassured;

import java.util.Map;

import org.apache.http.client.ResponseHandler;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtil {

	public static Response  postRequest(Map<String,String> headersMap,  Map<String,String> cookiesMap ,  Map<String,String> queryParams , String jsonBody){
		RestAssured.baseURI = EndPoints.postRequesEndPoint;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification reqSpecification = RestAssured.given();
		if(headersMap != null){
			reqSpecification.headers(headersMap);
		}
		if(cookiesMap != null){
			reqSpecification.cookies(cookiesMap);
		}
		if(queryParams != null){
			reqSpecification.queryParams(queryParams);
		}
		if(jsonBody != null){
			reqSpecification.body(jsonBody);
		}
		Response response = reqSpecification.request(Method.POST);
		
		return response;
	}
	
	public static Response  putRequest(String Id , Map<String,String> headersMap,  Map<String,String> cookiesMap ,  Map<String,String> queryParams , String jsonBody){
		RestAssured.baseURI = EndPoints.putRequesEndPoint.replaceAll("ID", Id);
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification reqSpecification = RestAssured.given();
		if(headersMap != null){
			reqSpecification.headers(headersMap);
		}
		if(cookiesMap != null){
			reqSpecification.cookies(cookiesMap);
		}
		if(queryParams != null){
			reqSpecification.queryParams(queryParams);
		}
		if(jsonBody != null){
			reqSpecification.body(jsonBody);
		}
		Response response = reqSpecification.request(Method.PUT);
		return response;
	}
	
	public static Response  getRequest(Map<String,String> headersMap,  Map<String,String> cookiesMap ,  Map<String,String> queryParams ){
		RestAssured.baseURI ="http://dummy.restapiexample.com";
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification reqSpecification = RestAssured.given();
		if(headersMap != null){
			reqSpecification.headers(headersMap);
		}
		if(cookiesMap != null){
			reqSpecification.cookies(cookiesMap);
		}
		if(queryParams != null){
			reqSpecification.queryParams(queryParams);
		}
		Response response = reqSpecification.request(Method.GET,"/api/v1/employee/1");
		return response;
	}

}
