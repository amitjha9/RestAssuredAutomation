package com.practice.resrassured;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.practice.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostAPICallRegisterSuccess extends TestBase{
	
	
	TestBase testBase;
	String serviceURL;
	String apiURL;
	String URL;
	
	
	@BeforeMethod
	public void setUP()
	{
		try {
			testBase=new TestBase();
			serviceURL=prop.getProperty("POST2_URL");
			apiURL=prop.getProperty("POST2_APIURL");
			URL=serviceURL+apiURL;
			System.out.println("API URL is:"+URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void postCallImplementation()
	{
		try {
			RestAssured.baseURI=URL;
			RequestSpecification httpRequest = RestAssured.given();

			//Create request pay load
			JSONObject requestParam = new JSONObject();
			requestParam.put("email", "eve.holt@reqres.in");
			requestParam.put("password", "pistol");

			//Provide header
			httpRequest.header("Content-Type","application/json");

			//Generate request body
			httpRequest.body(requestParam.toJSONString());

			//Now send the request and get the response object
			Response response = httpRequest.request(Method.POST, URL);

			String responseBody = response.getBody().asString();
			System.out.println("Response Received is:" +responseBody);

			//Validate the status code
			int statusCode = response.getStatusCode();
			System.out.println("Status Code:" +statusCode);
			Assert.assertEquals(statusCode, statusCode_200OK);

			//Validate the status line
			String statusLine = response.getStatusLine();
			System.out.println("Status Line:" +statusLine);
			Assert.assertEquals(statusLine, status_Line_200OK);

			//Validate the header part
			String contentType=response.header("content-type");
			System.out.println("Content Type is:" +contentType);
			Assert.assertEquals(contentType, "application/json; charset=utf-8");

			//Dynamically fetching the value of headers
			int actualContentLength = Integer.parseInt(response.getHeaders().get("content-length").getValue());
			System.out.println("Content Length is:" +actualContentLength);
			int expectedContentLength = Integer.parseInt(response.getHeaders().get("content-length").getValue());
			Assert.assertEquals(actualContentLength, expectedContentLength);

			//Validate cache status header dynamically
			String actualCacheStatus = response.getHeaders().get("cf-cache-status").getValue();
			System.out.println("Cache Status is:" +actualCacheStatus);
			String expectedCacheStatus = actualCacheStatus;
			Assert.assertEquals(actualCacheStatus, expectedCacheStatus);

			//Now Validate the JSON response
			JsonElement jsonElement = new JsonParser().parse(responseBody);
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			Set<Entry<String, JsonElement>> responseTag = jsonObject.entrySet();
			Iterator<Entry<String, JsonElement>> it = responseTag.iterator();
			System.out.println("Response after parse is\n");
			while (it.hasNext()) {
				Map.Entry<String, JsonElement> entry = it.next();
				System.out.println("Key:" +entry.getKey()+ ": Value:" +entry.getValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
