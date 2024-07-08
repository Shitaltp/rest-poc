package com.testcases;

import org.testng.annotations.Test;

import com.config.VideoGameConfig;
import com.config.VideoGameEndPoints;

import static io.restassured.RestAssured.*;

public class MyFirstTest extends VideoGameConfig {
	
	@Test
	public void myFirstTest() {
		
		given()
			.log().all()
		.when()
			.get("/videogame")
		.then()
			.log().all();
	}
	
	@Test
	public void myFirstTestWithEndPoints() {
		get(VideoGameEndPoints.ALL_VIDEO_GAME)
		.then().log().all();
	} 

}
