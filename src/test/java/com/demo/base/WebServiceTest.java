package com.demo.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WebServiceTest {

	public void validateGetMethod() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/todos/1";

		RequestSpecification httpRequest = RestAssured.given();
		//Response response = httpRequest.request(Method.GET, "/comments?postId=1");
		Response response = httpRequest.request(Method.GET, "/posts");

		int responseCode = response.getStatusCode();
		System.out.println("Response Code is : "+ responseCode);

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : "+ responseBody);
		System.out.println(response.jsonPath().get("title[1]"));
		Headers allHeaders = response.getHeaders(); 
		for(Header header:allHeaders)
			System.out.println("Key: " + header.getName() + "\t Value: " + header.getValue());

	}
	
	public void validateGetMethodWithJSON() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type","application/json");
		
		JSONObject jo = new JSONObject();
		jo.put("postId", "2");
		httpRequest.body(jo.toString());
		Response response = httpRequest.get("/comments");

		int responseCode = response.getStatusCode();
		System.out.println("Response Code is : "+ responseCode);

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : "+ responseBody); //too large to print
		System.out.println(response.jsonPath().get("email[1]"));

	}
	public void validatePostMethod() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject reqParams = new JSONObject();

		reqParams.put("title", "test");
		reqParams.put("body", "bar");
		reqParams.put("userId", "32");

		httpRequest.header("Content-Type","application/json");
		httpRequest.body(reqParams.toString());

		Response response = httpRequest.request(Method.POST, "/posts");
		
		int resCode = response.getStatusCode();
		System.out.println("Response Code is : "+resCode);

		String responseVal = response.getBody().asString();
		System.out.println(responseVal);

		int idVal = response.jsonPath().get("id");
		System.out.println(idVal);
	}

	public void validatePostFromFile() throws IOException {
		String fileName = "G:\\Inputs\\input.json";
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		RequestSpecification httpRequest = RestAssured.given();

		httpRequest.header("Content-Type","application/json");
		httpRequest.body(Files.readAllBytes(Paths.get(fileName)));

		//Response response = httpRequest.request(Method.POST, "/posts");
		Response response = httpRequest.post("/posts");
		int resCode = response.getStatusCode();
		System.out.println("Response Code is : "+resCode);

		String responseVal = response.getBody().asString();
		System.out.println(responseVal);

		int idVal = response.jsonPath().get("id");
		System.out.println(idVal);

		JsonPath jp = response.jsonPath();

		if(jp.get("id").equals(101))
			System.out.println("Expected item available");
		try {
			if(jp.get("ids"))
				System.out.println("Incorrect item available!!!, Failed");
		}catch(Exception e) {
			System.out.println("Expected item not available. Please check again");
		}
	}

	public static void main(String[] args) throws IOException{
		WebServiceTest wt = new WebServiceTest();
		wt.validateGetMethod();
		wt.validatePostMethod();
		wt.validatePostFromFile();
		wt.validateGetMethodWithJSON();
	}
}
