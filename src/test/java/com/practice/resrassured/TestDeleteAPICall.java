package com.practice.resrassured;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.practice.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestDeleteAPICall extends TestBase{

	TestBase testBase;
	String serviceURL;
	String apiURL;
	String URL;

	@BeforeMethod
	public void setUP()
	{
		try {
			testBase=new TestBase();
			serviceURL=prop.getProperty("DELETE_URL");
			apiURL=prop.getProperty("DELETE_APIURL");
			URL=serviceURL+apiURL;
			System.out.println("Service URL is:" +URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteCall()
	{
		try {
			RestAssured.baseURI=URL;
			RequestSpecification httpRequest=RestAssured.given();
			Response response = httpRequest.request(Method.DELETE,URL);

			//Get the response body -> For delete response body will be null
			
			//Get the status code
			int statusCode = response.getStatusCode();
			System.out.println("Status Code is:===>"+statusCode);
			Assert.assertEquals(statusCode, statusCode_204OK);
			
			//Get the status line
			String statusLine = response.getStatusLine();
			System.out.println("Status Line is:===>"+statusLine);
			Assert.assertEquals(statusLine, status_Line_204OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
