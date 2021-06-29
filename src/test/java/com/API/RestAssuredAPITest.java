package com.API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredAPITest {
	
	
	public void getEmployeeData() {
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/employee/2";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("ContentType", "application/json");
		Response res = httpRequest.get();
		
		System.out.println(res.getStatusCode());
		System.out.println(res.getStatusLine());
		System.out.println(res.body().jsonPath().get("message").toString());
		System.out.println(res.body().asString());		
	}
	
	public void deleteEmployeeData() {
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("ContentType", "application/json");
		Response res = httpRequest.delete("/delete/2");
		
		System.out.println(res.getStatusCode());
		System.out.println(res.body().asString());		
	}
	
	public static void main(String[] args) {
		RestAssuredAPITest api = new RestAssuredAPITest();
		api.getEmployeeData();
		api.deleteEmployeeData();
	}
}
