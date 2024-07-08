package com.testcases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import com.config.VideoGameConfig;
import com.config.VideoGameEndPoints;
import com.objects.VideoGame;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;

public class VideoGameTests extends VideoGameConfig {
	String gameBodyJSON="{\n"
			+ "  \"category\": \"Platform\",\n"
			+ "  \"name\": \"Mario\",\n"
			+ "  \"rating\": \"Mature\",\n"
			+ "  \"releaseDate\": \"2012-05-04\",\n"
			+ "  \"reviewScore\": 85\n"
			+ "}";
	@Test
	public void getALLGames() {
		given()
		.when()
			.get(VideoGameEndPoints.ALL_VIDEO_GAME)
		.then();
	}

	@Test
	public void createNewGameByJSON() {
	given()
		.body(gameBodyJSON)
	.when()
		.post(VideoGameEndPoints.ALL_VIDEO_GAME)
	.then();
	}
	
	@Test
	public void updateGame() {
		given()
				.body(gameBodyJSON)
		.when()
				.put("videogame/3")
		.then();
	}
	
	@Test
	public void deleteGame() {
		given()
				.accept("text/plain")
		.when()
				.delete("videogame/6")
		.then();
	}
	/**
	 * now in this test we are using path parameter 
	 */
	@Test
	public void getSingleGame() {
		given()
				.pathParam("VideoGameId",5)
		.when()
				.get(VideoGameEndPoints.SINGLE_VIDEO_GAME)
		.then();
	}
	
	/**
	 * 25. Object Serialization
	 * for this you need 2 dependincies 1. is jackson-core
	 *  n 2 is jackson-databind
	 */
	@Test
	public void testVideoGameSerializationByJson() {
		VideoGame videoGame = new VideoGame("Shooter","MyAwsomeGame","Mature","2021-09-23",95);
		
		given()
				.body(videoGame)
		.when()
			.post(VideoGameEndPoints.ALL_VIDEO_GAME)
		.then();
	}
	/**
	 *28. Convert JSON Response to POJO
	 */
	@Test
	public void convertJsonToPojo() {
		Response resp= 
						given()
							.pathParam("VideoGameId",5)
						.when()
						.get(VideoGameEndPoints.SINGLE_VIDEO_GAME);
		
		VideoGame videoGame= resp.getBody().as(VideoGame.class);
		System.out.println(videoGame.toString());
				
	}
	/**
	 * 26. Validating Response against a XML Schema 
	 * for that you need to copy response as xml and then convert it into xsd using xsd converter 
	 * and then make file in resources like videogameXSD.xsd
	 * 
	 */
	@Test
	public void testVideoGameSchemaXML() {
		given()
		.pathParam("VideoGameId", 5)
		.accept("application/xml")
		.when()
		.get(VideoGameEndPoints.SINGLE_VIDEO_GAME)
		.then()
		//here we are checking response body .will get xml body back which matches 
		//schema which we difine in VideoGameXSD.xsd file
		//i got classpath error for that i need to put that resources folder in src.main not in src/main/java
		.body(RestAssuredMatchers.matchesXsdInClasspath("VideoGameXSD.xsd"));
	}
	/**
	 *27. Validating Response against a JSON Schema
	 */
	@Test
	public void testVideoGameSchemaJSON() {
		given()
		.pathParam("VideoGameId", 5)
		//.accept("application/json")
		.when()
		.get(VideoGameEndPoints.SINGLE_VIDEO_GAME)
		.then()
		//here we are checking response body .will get xml body back which matches 
		//schema which we difine in VideoGameXSD.xsd file
		//i got classpath error for that i need to put that resources folder in src.main not in src/main/java
		//JsonSchemaValidator for this i need JsonSchemaValidator dependncies
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("VideoGameSchemaJSON.json"));
	}
	
	/**
	 *29. Measuring Response Time in REST Assured
	 */
	@Test
	public void calculateResponseTime() {
		long responseTime = get(VideoGameEndPoints.ALL_VIDEO_GAME).time();
		System.out.println("Response time in ms: "+responseTime);
	}
	
	@Test
	public void assertOnResponseTime() {
		get(VideoGameEndPoints.ALL_VIDEO_GAME)
		.then().time(lessThan(2000L));
	}
	/**
	 * Gpth is looking cetain data in our response 
	 */
}
