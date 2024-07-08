package com.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;

public class AuthenticationTest {
	
	@BeforeClass
	public void setup() {
		RestAssured.proxy("localhost", 8888);
	}
	
	@Test
	public void basicPreemptiveAuthTest() {
		given()
				.auth().preemptive().basic("username","password")
		.when()
				.get("http://localhost:8080/someEndpoint");
	}
	@Test
	public void basicChallengedAuthTest() {
		given()
				.auth().basic("username", "password")
		.when()
				.get("http://localhost:8080/someEndpoint");
	}

}
