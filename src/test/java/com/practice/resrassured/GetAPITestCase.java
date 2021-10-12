package com.practice.resrassured;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.practice.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetAPITestCase extends TestBase{
	
	TestBase testBase;
	String serviceURL;
	String apiURL;
	String URL;
	
	@BeforeMethod
	public void setUP()
	{
		try {
			testBase=new TestBase();
			serviceURL=prop.getProperty("URL2");
			apiURL=prop.getProperty("apiURL2");
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
			System.out.println("Response Received From Server is===>"+responseBody);
			
			//Get the status code
			int statusCode = response.getStatusCode();
			System.out.println("Status Code is:===>"+statusCode);
			Assert.assertEquals(statusCode, statusCode_200OK);
			
			//Get the status line
			String statusLine = response.getStatusLine();
			System.out.println("Status Line is:===>"+statusLine);
			Assert.assertEquals(statusLine, status_Line_200OK);
			
			//We will Validate all the headers as well
			Headers allheaders=response.headers();
			for(Header header: allheaders)
			{
				System.out.println("Key: "+ header.getName() + " :Value: " + header.getValue());
			}
			
			//Validate header
			String contentType = response.header("content-type");
			System.out.println("Content Type is:===>" +contentType);
			Assert.assertEquals(contentType, "application/json; charset=utf-8");
			
			String cacheControl = response.header("cache-control");
			System.out.println("Cache Control is:===>" +cacheControl);
			Assert.assertEquals(cacheControl, "max-age=43200");
			
			String accessControlAllowCredentials = response.header("access-control-allow-credentials");
			System.out.println("Access Control Allow Cred is:===>" +accessControlAllowCredentials);
			Assert.assertEquals(accessControlAllowCredentials, "true");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
