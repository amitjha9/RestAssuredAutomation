package com.practice.resrassured;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.practice.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAPITestCase4 extends TestBase{

	JsonPath jsonPath;
	JsonObject  jobject;
	JsonObject  jobject1;
	TestBase testBase;
	String serviceURL;
	String apiURL;
	String URL;
	
	@BeforeMethod
	public void setUP()
	{
		try {
			testBase=new TestBase();
			serviceURL=prop.getProperty("URL4");
			apiURL=prop.getProperty("apiURL4");
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

			//Get the response body printed on console
			String responseBody = response.getBody().asString();
			System.out.println("Response received from server:===>\n" +responseBody);

			//Validate the JSON response for every node line by line using Json Parser.
			jsonPath = new JsonPath(responseBody);
			@SuppressWarnings("deprecation")
			JsonElement jelement = new com.google.gson.JsonParser().parse(responseBody);
			jobject = jelement.getAsJsonObject();
			jobject = jobject.getAsJsonObject("data");
			Set<Entry<String, JsonElement>> responseBodyTag=jobject.entrySet();
			//Map<String> responseBodyTag=jobject.keySet();
			System.out.println("JSON after parsing");
			Iterator<Entry<String, JsonElement>> it = responseBodyTag.iterator();
			while (it.hasNext()) {
				Map.Entry<String, JsonElement> entry = it.next();
				System.out.println("Key:" +entry.getKey()+ " :Value:" +entry.getValue());
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
