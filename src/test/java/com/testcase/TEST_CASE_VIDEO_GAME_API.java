package com.testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class TEST_CASE_VIDEO_GAME_API {
	
	@Test(priority=1)
	public void test_GetAllVideoGames() {
		given().
			when()
			  .get("http://localhost:8080/app/videogames").
			  then().
			  statusCode(200).log().all();
		
		
	}
	@Test(priority=2)
	public void test_addNewVideoGame() {
		HashMap map=new HashMap();
		map.put("id","11");
	    map.put("name","string");
	    map.put( "releaseDate", "2022-12-16T17:51:52.945Z");
		map.put("reviewScore",0);
		map.put ("category","string");
		map.put( "rating", "string");
		Response res=
				given().
				contentType("application/json").
				body(map).
				when().
				post("http://localhost:8080/app/videogames").
				then().
				statusCode(500).log().body().extract().response();
		String jsonString=res.asString();
		Assert.assertEquals(jsonString.contains("Record Added Successfully"),false);
		
		}
	@Test(priority=3)
	public void test_getVideoGame() {
		
				given().
				
				when().
				get("http://localhost:8080/app/videogames/3").
				then().
				statusCode(200).
				body("videoGame.id", equalTo("3")).
				body("videoGame.name",equalTo("Tetris"));
				
		
		}
	@Test(priority=4)
	public void test_UpdateVideoGame()
	{
			HashMap data=new HashMap();
			data.put("id", "3");
			data.put("name", "Tetris");  // update video game name
			data.put("releaseDate", "2019-09-20T08:55:58.510Z");
			data.put("reviewScore", "4");  // update
			data.put("category", "Adventure");
			data.put("rating", "Universal");
			
			given()
				.contentType("application/json")
				.body(data)
			.when()
				.put("http://localhost:8080/app/videogames/3") //http://localhost:8081/app/videogames/106
			.then()
				.statusCode(200)
				.log().body()
				.body("videoGame.id",equalTo("3"))
				.body("videoGame.name",equalTo("Tetris"));
		
	}
	@Test(priority=5)
	public void test_DeleteVideoGame() throws InterruptedException
	{
		
			Response res=
			given().
				when().
				delete("http://localhost:8080/app/videogames/1").
				then().
				 statusCode(200).
				 log().body().extract().response();
			Thread.sleep(5000);
			String jsonString=res.asString();
			Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);
				
				
		
	}
	}

