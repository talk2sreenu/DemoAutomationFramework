package com.testTurotials;

import org.json.JSONObject;
import org.json.XML;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class RestAssuredTests {
	
	public void sampleTest() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/todos/1";
		
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.get("/posts");
		System.out.println(res.getStatusCode());
	}
	
	public void advTest() {
		RequestSpecBuilder rsb = new RequestSpecBuilder();
		rsb.setBaseUri("https://jsonplaceholder.typicode.com/todos/1");
		rsb.addHeader("Content-type", "application/json");
		rsb.setBasePath("/posts");
		
		RequestSpecification httpRequest = rsb.build();
		Response res = RestAssured.given().spec(httpRequest).get();
		int resVal = res.getStatusCode();
		
		System.out.println(resVal);
		System.out.println(res.getHeader("Content-Type"));
		
		//System.out.println(res.asString());
		System.out.println(res.jsonPath().get("id[53]").toString());
		
		String messageBody = res.getStatusLine();
		System.out.println(messageBody);
	}
	
	public void anotherTestMethod() {
		String idVal = RestAssured.given().baseUri("https://jsonplaceholder.typicode.com/todos/1")
				.when()
				.get("/posts")
				.then().extract().path("id[1]").toString();
		System.out.println(idVal);
		
	}
	
	public void humanReadableTest() {
		given().
			baseUri("https://jsonplaceholder.typicode.com/todos/1").
		when().
			get("/posts").
		then().
			assertThat().statusCode(200).
		and().contentType(ContentType.JSON);		
	}
	
	public void authenticationTest() {
		RequestSpecBuilder rse = new RequestSpecBuilder();
		rse.setBaseUri("https://restapi.demoqa.com/authentication/CheckForAuthentication");
		
		RequestSpecification rsf = rse.build();
		//String authorization = encode("uName", "password");
		//Response res = RestAssured.given().header("Authorization", "Basic "+authorization).
		Response res = RestAssured.given().header("Content-Type", "application/json").
					   baseUri("https://jsonplaceholder.typicode.com/todos/1").
					   get("/posts");
		//System.out.println(res.getBody().asString());
		JSONObject jso1 = new JSONObject(res.getBody().jsonPath().toString());
		String xml = XML.toString(jso1);
		
		System.out.println(xml);
		System.out.println(res.getStatusLine());
		
		JSONObject jso = new JSONObject();
		jso.put("title", "foo");
		jso.put("body", "bar");
		jso.put("userId", 1);
		Response postRes = RestAssured.given().
				   baseUri("https://jsonplaceholder.typicode.com/posts").
				   body(jso.toString()).
				   post();
		System.out.println(postRes.getStatusLine());
		
		Response delRequest = RestAssured.given().
				   baseUri("https://jsonplaceholder.typicode.com/posts").
				   body(jso.toString()).
				   delete("/1");
		
		

		System.out.println(delRequest.getStatusLine());
	}
	
	private String encode(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		RestAssuredTests res = new RestAssuredTests();
		//res.sampleTest();
	//res.advTest();
		//res.anotherTestMethod();
		//res.humanReadableTest();
		//res.authenticationTest();
		res.xmltoJSON();
	}
	
	public void xmltoJSON() {
		String xml_data = "<student><name>Neeraj Mishra</name><age>22</age></student>";
		JSONObject obj = XML.toJSONObject(xml_data);
		System.out.println(obj.toString());
		
		String json_data = "{\"student\":{\"name\":\"Neeraj Mishra\", \"age\":\"22\"}}";
		JSONObject obj2 = new JSONObject(json_data);
		//converting json to xml
		String xml_data2 = XML.toString(obj2);
		System.out.println(xml_data2);
	}
	
	@Test(dataProvider="testData", dataProviderClass = Test1.class)
	public void testAnnotations() {
		
	}
	
	@DataProvider(name="testData")
	public Object[][] DP() {
		return new Object[1][1];
	}
}
