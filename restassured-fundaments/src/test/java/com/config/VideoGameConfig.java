package com.config;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class VideoGameConfig {

	@BeforeClass
	public static void setup() {

		RestAssured.requestSpecification = new RequestSpecBuilder()
				.setBaseUri("https://videogamedb.uk/")
				.setBasePath("api/v2/")
				.setContentType("application/json")
				.addHeader("Accept", "application/json")
				.addFilter(new RequestLoggingFilter())//  it do what:- log() all () method do
				.addFilter(new ResponseLoggingFilter())
				.build();

		RestAssured.responseSpecification = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();

	}

}
