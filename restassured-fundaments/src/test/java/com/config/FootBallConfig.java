package com.config;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class FootBallConfig {
	
	@BeforeClass
	public void setup() {
		RestAssured.requestSpecification= new RequestSpecBuilder()
				.setBaseUri("https://api.football-data.org")
				.setBasePath("/v4")
				.addHeader("X-Auth-Token", "d5b4a2cd6ee74c10bd5b10ff4154e668")
				.addHeader("X-Response-Control", "minified")
				.addFilter(new RequestLoggingFilter())
				.addFilter(new ResponseLoggingFilter())
				.build();
	
		RestAssured.responseSpecification=new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
		
	
	}
}
