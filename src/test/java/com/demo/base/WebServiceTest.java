package com.demo.base;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WebServiceTest {
	
	public void validateGetMethod() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/todos/1";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.request(Method.GET, "/posts");
		
		int responseCode = response.getStatusCode();
		System.out.println("Response Code is : "+ responseCode);
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : "+ responseBody);
		
		Headers allHeaders = response.getHeaders();
		for(Header header:allHeaders)
			System.out.println("Key: " + header.getName() + "\t Value: " + header.getValue());
		
	}
	public static void main(String[] args){
		WebServiceTest wt = new WebServiceTest();
		wt.validateGetMethod();
	}
}
