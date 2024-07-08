package com.config;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

public class TestConfig {
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI="https://videogamedb.uk/";
		RestAssured.basePath="api/v2/";
		//RestAssured.port=443;
		
		RestAssured.requestSpecification = new RequestSpecBuilder()
				.setContentType("application/json")
				.addHeader("Accept","application/json" )
				.build();
		
		RestAssured.responseSpecification = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
		
	}

}
