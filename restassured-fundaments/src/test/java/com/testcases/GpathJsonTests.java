package com.testcases;

import org.testng.annotations.Test;

import com.config.FootBallConfig;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GpathJsonTests extends FootBallConfig {

		@Test
		public void extractMapOfElementsWithFind() {
			Response resp= 
						get("competitions/2021/teams");
			Map<String, ?> allTeamDataForSingleTeam = resp.path("teams.find {it.name == 'Liverpool FC'}");
			System.out.println("Map of team data "+allTeamDataForSingleTeam);
		}
		
		/**
		 *31. GPath JSON Part 2 - Using findAll to extract multiple data
		 */
		@Test
		public void extractSingleValueFromFind() {
			Response resp = get("teams/57");
			String certainPlayer = resp.path("squad.find{it.id == 5530}.name");
			System.out.println("Name of Player: "+certainPlayer);
		}
		
		@Test
		public void extractListOfValuesWithFindAll() {
			Response resp = get("teams/57");
			List<String> playerNames = resp.path("squad.findAll{it.id >7784}.name");
			System.out.println("List of players name: "+playerNames);
		}
		/**
		 *32. GPath JSON Part 3 - Using Min, Max, Collect and Sum
		 */
		@Test
		public void extractSingleValueWithhighestNumber() {
			Response resp = get("teams/57");
			String playerName = resp.path("squad.max { it.id }.name");
			System.out.println("Player with highest id: "+playerName);
		}
		@Test
		public void extractSingleValueWithLowestNumber() {
			Response resp = get("teams/57");
			String playerName = resp.path("squad.min { it.id }.name");
			System.out.println("Player with lowest id: "+playerName);
		}
		
		@Test
		public void extractMultipleValuesAndSumThem() {
			Response resp = get("teams/57");
		int sumOfIds = resp.path("squad.collect { it.id }.sum()");
		System.out.println("Sum of ids: "+sumOfIds);
		}
		
		/**
		 *33. GPath JSON Part 4 - Combining finds and using parameters
		 */
		@Test
		public void extractMapWithFindAndFindAllParameters() {
			String posisition ="Goalkeeper";
			String nationality = "England";
			
			Response resp = get("teams/57");
			
			Map<String,?> playerOfCertainPosition = 
					resp.path("squad.findAll { it.position== '%s' }.find { it.nationality }",posisition,nationality);
		
			System.out.println("Details of Player "+playerOfCertainPosition);	
		}
		@Test
		public void extractMultiplePlayersap() {
			String posisition ="Goalkeeper";
			String nationality = "England";
			
			Response resp = get("teams/57");
			
			ArrayList<Map<String,?>> allPlayersOfCertainPosition = 
					resp.path("squad.findAll { it.position== '%s' }.findAll { it.nationality }",posisition,nationality);
		
			System.out.println("Details of Player "+allPlayersOfCertainPosition);	
		}	
		
}
