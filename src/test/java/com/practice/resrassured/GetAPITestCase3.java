package com.practice.resrassured;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.practice.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetAPITestCase3 extends TestBase{
	
	TestBase testBase;
	String serviceURL;
	String apiURL;
	String URL;
	
	@BeforeMethod
	public void setUP()
	{
		try {
			testBase=new TestBase();
			serviceURL=prop.getProperty("URL3");
			apiURL=prop.getProperty("apiURL3");
			URL=serviceURL+apiURL;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void callGetAPITest()
	{
		try {
			RestAssured.baseURI=URL;
			RequestSpecification httpRequest=RestAssured.given();
			Response response = httpRequest.request(Method.GET,URL);
			
			//Get the response body
			String responseBody=response.getBody().asString();
			System.out.println("Response Received From Server is:===>" +responseBody);
			
			//Validate the JSON response body one by one using assert command
			Assert.assertEquals(responseBody.contains("1"), true);
			Assert.assertEquals(responseBody.contains("1"), true);
			Assert.assertEquals(responseBody.contains("delectus aut autem"), true);
			Assert.assertEquals(responseBody.contains("false"), true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
