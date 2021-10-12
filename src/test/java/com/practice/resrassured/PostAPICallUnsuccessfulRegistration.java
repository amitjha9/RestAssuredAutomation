package com.practice.resrassured;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.practice.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostAPICallUnsuccessfulRegistration extends TestBase{
	
	TestBase testBase;
	String serviceURL;
	String apiURL;
	String URL;
	
	@BeforeMethod
	public void setUP()
	{
		try {
			testBase=new TestBase();
			serviceURL=prop.getProperty("POST3_URL");
			apiURL=prop.getProperty("POST3_APIURL");
			URL=serviceURL+apiURL;
			System.out.println("Api URL is:" +URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPostAPICallRegistrationFailure()
	{
		try {
			RestAssured.baseURI=URL;
			RequestSpecification httpResquest = RestAssured.given();
			
			//Create request pay load using JSONObject 
			JSONObject requestPayload = new JSONObject();
			requestPayload.put("email", "sydney@fife");
			//Create a header as content type
			httpResquest.header("Content-Type","application/json");
			//Create body
			httpResquest.body(requestPayload.toJSONString());
			Response response = httpResquest.request(Method.POST,URL);
			
			String responseBody = response.getBody().asString();
			System.out.println("Response is:\n" +responseBody);
			
			//Validate the status code
			int statusCode = response.getStatusCode();
			System.out.println("Status Code is:" +statusCode);
			Assert.assertEquals(statusCode, statusCode_400_Bad_Request);
			
			//Validate the status line
			String statusLine = response.getStatusLine();
			System.out.println("Status Line is:" +statusLine);
			Assert.assertEquals(statusLine, status_Line_400_Bad_Request);
			
			//Print all header one by one
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
			List<Header> getAllHeader = response.headers().asList();
			//Iterate the list and validate the headers.
			Iterator<Header> it = getAllHeader.iterator();
			while (it.hasNext()) {
				Header header = (Header) it.next();
				String headerName = header.getName();
				String headerValue = header.getValue();
				System.out.println("HEADER NAME:" +headerName+ ": HEADER VALUE:" +headerValue);
			}
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			//Validate header one by one
			int actualContentLength=Integer.parseInt(response.getHeaders().get("content-length").getValue());
			System.out.println("Content Length is:" +actualContentLength);
			int expectedContentLength = Integer.parseInt(response.getHeaders().get("content-length").getValue());
			Assert.assertEquals(actualContentLength, expectedContentLength);

			//Validate another headers
			String actualCacheStatus = response.getHeaders().get("cf-cache-status").getValue();
			System.out.println("Cache Status is:" +actualCacheStatus);
			String expectedCacheStatus = actualCacheStatus;
			Assert.assertEquals(actualCacheStatus, expectedCacheStatus);
			
			//Validate the JSON response
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
			JsonElement jsonElement = new JsonParser().parse(responseBody);
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			Set<Entry<String, JsonElement>> responseTag = jsonObject.entrySet();
			Iterator<Entry<String, JsonElement>> it1 = responseTag.iterator();
			while (it1.hasNext()) {
				Map.Entry<String, JsonElement> entry = it1.next();
				System.out.println("Key:" +entry.getKey()+ " Value:" +entry.getValue());
			}
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
