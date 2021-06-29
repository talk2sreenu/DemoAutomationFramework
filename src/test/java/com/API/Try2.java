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
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		Try2 tr = new Try2();
		tr.AnotherTry();
		tr.postRequestCreation();
		String password = "Master@1";
		String url = Base64.getEncoder().encodeToString(password.getBytes());
		
		System.out.println("URL"+ url);
	}
}
