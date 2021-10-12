package com.practice.resrassured;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.practice.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAPITest extends TestBase{
	
	//TestBase testBase;
	String serviceURL;
	String apiURL;
	String URL;
	
	@BeforeClass
	public void setUP()
	{
		try {
			//testBase=new TestBase();
			serviceURL = prop.getProperty("URL");
			apiURL = prop.getProperty("apiURL");
			URL= serviceURL+apiURL;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void callAPITest()
	{
		try {
			//Specify the base URL to the RESTful web service
			RestAssured.baseURI=URL;
			
			//Specify the request that you want to send to the server
			RequestSpecification httpRequest = RestAssured.given();
			
			//Make a request to the server with method type GET and URL.
			Response response = httpRequest.request(Method.GET, URL);
			
			//Print the response received from the server
			String responseBody=response.getBody().asString();
			System.out.println("Response received from server is: "+responseBody);
			
			//Validate the status code
			int statusCode = response.getStatusCode();
			System.out.println("Status Code is: "+statusCode);
			Assert.assertEquals(statusCode, statusCode_200OK);
			
			//Validate the status line
			String statusLine = response.getStatusLine();
			System.out.println("Status Line is:" +statusLine);
			Assert.assertEquals(statusLine, status_Line_200OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
