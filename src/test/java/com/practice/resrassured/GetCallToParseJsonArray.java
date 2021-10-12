package com.practice.resrassured;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.practice.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetCallToParseJsonArray extends TestBase{
	
	TestBase testBase;
	String serviceURL;
	String apiURL;
	String URL;
	
	@BeforeMethod
	public void setUP()
	{
		testBase=new TestBase();
		serviceURL=prop.getProperty("URL5");
		apiURL=prop.getProperty("apiURL5");
		URL=serviceURL+apiURL;
	}
	
	@Test
	public void getCallToParseJsonArray()
	{
		try {
			RestAssured.baseURI=URL;
			//Generate https request
			RequestSpecification httpRequest = RestAssured.given();
			Response response=httpRequest.request(Method.GET, URL);
			
			//Get the response body printed on console
			String responseBody = response.getBody().asString();
			System.out.println("Response received from server:===>\n" +responseBody);
			
			//Get the status code
			int statusCode = response.getStatusCode();
			System.out.println("Status Code is:===>"+statusCode);
			Assert.assertEquals(statusCode, statusCode_200OK);
			
			//Get the status line
			String statusLine = response.getStatusLine();
			System.out.println("Status Line is:===>"+statusLine);
			Assert.assertEquals(statusLine, status_Line_200OK);
			
			@SuppressWarnings("deprecation")
			JsonElement jsonElement = new JsonParser().parse(responseBody);
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			JsonArray jsonArray = jsonObject.getAsJsonArray("data");
			int size = jsonArray.size();
			System.out.println("\n");
			for(int i=0; i<size; i++)
			{
				jsonObject=jsonArray.get(i).getAsJsonObject();
				Set<Entry<String, JsonElement>> responseBodyTag=jsonObject.entrySet();
				System.out.println("+++++++++++++Json object Value+++++++++");
				Iterator<Entry<String, JsonElement>> it = responseBodyTag.iterator();
				while (it.hasNext()) {
					Map.Entry<String, JsonElement> entry = it.next();
					System.out.println("Key:" +entry.getKey()+ ": Value: "+entry.getValue());
					System.out.println("+++++++++++++++++++++++++++++++++++++++");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
