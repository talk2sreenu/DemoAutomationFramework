package com.API;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredAPITest {
	
	
	public void getEmployeeData() {
		//RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/employee/2";
		RequestSpecBuilder rsb = new RequestSpecBuilder();
		rsb.setBaseUri("https://restful-booker.herokuapp.com");
		rsb.setBasePath("/booking");
		rsb.addHeader("ContentType", "application/json");
		RequestSpecification httpRequest = rsb.build();
		//RequestSpecification httpRequest = RestAssured.given();
		//httpRequest.header("ContentType", "application/json");
		
		
		//Response res = httpRequest.get();
		Response res = RestAssured.given().spec(httpRequest).get();
		
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
		WebDriver driver = null;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement txtVal = null;
		WebElement finVal = wait.until(ExpectedConditions.elementToBeClickable(txtVal));
	}
}
