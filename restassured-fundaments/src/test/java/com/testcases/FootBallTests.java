package com.testcases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import com.config.FootBallConfig;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.*;

import java.util.List;

public class FootBallTests extends FootBallConfig {
	
	@Test
	public void getDetailsOfOneArea() {
		
		given()
		.queryParam("areas", 2067)
		.when()
				.get("/areas");
		
		
	}
	
	
	@Test
	public void getDetailsOfMultipleAres() {
		String areaIds= "2067,2076,2088";
		given()
		/**
		 * we can put commas in our query param using urlEncodingEnabled
		 */
		.urlEncodingEnabled(false)
				.queryParam("areas",areaIds)
		.when()
			.get("/areas");	
	}
	/**
	 * In this we are checking response is same as we check founded =1886
	 */
	@Test
	public void getDateFounded() {
		
		given()
		.when()
				.get("/teams/57")
		.then()
				.body("founded", equalTo(1886));
	}
	
	/**
	 * in this we are checking what team we get in response 
	 * so first team name we are valdating
	 */
	@Test 
public void getFirstTeamName() {
		
		given()
		.when()
				.get("/competitions/2021/teams")
		.then()
				.body("teams.name[0]",equalTo("Arsenal FC"));
	}
	/**
	 * here we extracting all response body
	 */
	@Test
	public void getAllTeamData() {
		String response= get("teams/57").asString();
		System.out.println(response);
	}
	
	@Test
	public void getAllDeamData_DoCheckFirst() {
		Response resp=
				given()
				.when()
						.get("/teams/57")
				.then()
						.contentType(ContentType.JSON)
						.extract().response();
		String jsonResponseAsString =resp.asString();
		System.out.println(jsonResponseAsString);
	}
	/**
	 * Extract headers of a http response
	 */
	@Test
	public void getHeaders() {
		Response resp = 
					 get("teams/57")
					 .then()
					 .extract().response();
		
		String contentTypeHeader = resp.getContentType();
		System.out.println(contentTypeHeader);
		
		String apiVersionHeader = resp.getHeader("X-API-Version");
		System.out.println(apiVersionHeader);
	}
	/**
	 * 24. Extract explicit data from the body with JSON path
	 */
	@Test
	public void extractFirstTeamName() {
		String getFirstTeamName = get("competitions/2021/teams").jsonPath().getString("teams.name[0]");
		System.out.println(getFirstTeamName);
	}
	/**
	 * first we save all response then grab all teams name
	 */
	@Test
	public void getAllTeamNames() {
		Response resp = 
				get("competitions/2021/teams")
				.then()
				.extract().response();
		
		List<String> teamNames = resp.path("teams.name");
			
				for(String teamName : teamNames) {
					System.out.println(teamName);
				}
	}
	/**
	 *25. Object Serialization
	 *first convert json into job object
	 */
}
