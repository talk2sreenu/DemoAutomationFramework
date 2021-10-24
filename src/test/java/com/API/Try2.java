package com.API;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Try2 {
	
	public void AnotherTry() {
		RestAssured.baseURI = "https://reqres.in/api";
		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		
		Response res = httpRequest.get("/users/2");
		System.out.println(res.body().jsonPath().prettyPrint());
		
		System.out.println(res.header("Content-Type").toString());
		System.out.println(res.body().jsonPath().get("data.id"));
	}
	
	public void postRequestCreation() {
		RestAssured.baseURI = "https://reqres.in/api/users";
		
		RequestSpecification req = RestAssured.given();
		
		JSONObject jso = new JSONObject();
		jso.put("name", "morpheus");
		jso.put("job", "leader");
		
		req.header("Content-Type", "application/json");
		req.body(jso);
		
		Response res = req.post();
		System.out.println(res.getStatusCode());
		System.out.println(res.getStatusLine());
		System.out.println(res.body().jsonPath().prettify());
		
	}
	
	public void satchPostRequest() {
		RestAssured.baseURI = "https://gorest.co.in/public/v1/users";
		
		RequestSpecification req = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		//json.put("id", "1301");
		requestParams.put("gender", "male");
		requestParams.put("name", "Ssat1052");
		requestParams.put("email", "ssat1052@gmail.com");
		requestParams.put("status", "active");
		
		req.header("Accept", "application/json");
		req.header("Authorization", "Bearer 64d9be8c15b8d41ccf42a3f72f093718f50c7ab54d7f292904ef221700326201");
		req.body(requestParams);
		
		Response res = req.post();
		System.out.println(res.getStatusCode());
		System.out.println(res.getStatusLine());
		System.out.println(res.body().jsonPath().prettify());
		System.out.print(res.body().jsonPath().get("data[0]"));
		
	}
	
	public void satchGettRequest() {
		RestAssured.baseURI = "https://gorest.co.in/public/v1/users";
		
		RequestSpecification req = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		
		req.header("Accept", "application/json");
		req.header("Authorization", "Bearer 64d9be8c15b8d41ccf42a3f72f093718f50c7ab54d7f292904ef221700326201");
		req.body(requestParams);
		
		Response res = req.get();
		System.out.println(res.getStatusCode());
		System.out.println(res.getStatusLine());
		System.out.println(res.body().jsonPath().prettify());
		System.out.print(res.body().jsonPath().get("data[0]"));
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		Try2 tr = new Try2();
		//tr.AnotherTry();
		//tr.postRequestCreation();
		//tr.satchPostRequest();
		tr.satchGettRequest();
		String password = "Master@1";
		String url = Base64.getEncoder().encodeToString(password.getBytes());
		
		System.out.println("URL"+ url);
	}
}
