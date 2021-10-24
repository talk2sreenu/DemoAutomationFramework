package com.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.TestNGException;
import org.testng.annotations.Test;

import com.sun.tools.sjavac.Log;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredGet {
	
	public void generateGetMethod() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/todos/1";
		
		RequestSpecification httpResponse = RestAssured.given();
		
		Response response = httpResponse.request(Method.GET, "/posts");
		
		System.out.println(response.getStatusCode());
		
		System.out.println(response.getBody().asString());
		
		System.out.println(response.jsonPath().get("userId[1]"));
	}
	
	public void generateGetWithJSON() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		RequestSpecification httpResponse = RestAssured.given();
		httpResponse.header("Content-Type", "application/json");
		
		JSONObject jso = new JSONObject();
		jso.put("postId", "2");
		//httpResponse.body(jso);
		httpResponse.body(jso.toString());
		
		Response res = httpResponse.get("/comments");
		
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody().asString());
		
		Headers allHeaders = res.getHeaders();
		
		for(Header header:allHeaders) {
			System.out.println(header.getName() + header.getValue());
		}
		
		String responseTime = String.valueOf(res.getTimeIn(TimeUnit.MICROSECONDS));
		
		System.out.println("Response Time is : "+responseTime+" milli seconds");
		
		List<String> emailIds = res.getBody().jsonPath().get("email");
		System.out.println("Total Emails returned : "+emailIds.size());
		System.out.println("Emails returned : "+emailIds.get(499));
		System.out.println("Emails returned : "+emailIds.get(495));
	}
	
	public void testAnotherURI() throws IOException {
		RestAssured.baseURI = "http://demo.guru99.com/V4/sinkministatement.php";
		
		RequestSpecification httpResponse = RestAssured.given().queryParam("CUSTOMER_ID", "68195")
																.queryParam("PASSWORD", "1234!")
																.queryParam("Account_No", "1")
																.header("Content-type", getClass(), "application/json")
																.header("Accept", getClass(), "application/json");
																
		//httpResponse.header("Content-Type", "application/json");
		
		Response res = httpResponse.get();
		
		System.out.println(res.getStatusCode());
		Headers header = res.getHeaders();
		for(Header temp:header)
			System.out.println(temp.getName() +":"+temp.getValue());
		
		Long timetaken = res.getTimeIn(TimeUnit.MILLISECONDS);
		
		System.out.println("Time is : "+timetaken);
	}
	
	
	public static void main(String[] args) throws IOException {
		RestAssuredGet ra = new RestAssuredGet();
		//ra.generateGetMethod();
		//ra.generateGetWithJSON();
		ra.testAnotherURI();
	}
	
	@Test(expectedExceptions = ArithmeticException.class, expectedExceptionsMessageRegExp ="/ by zero")
	public void sampleTest() {
		System.out.println(1/0);
	}
	
	@Test
	public void secondTest() {
		System.out.println("Inside Dependent Method");
	}
	
	public void helloWorld() {
		System.out.println("Hello WOrld");
	}
	@Test(dependsOnMethods={"secondTest"})
	public void aTest() {
		System.out.println("Hello Dependent Methos");
	}
	
}
