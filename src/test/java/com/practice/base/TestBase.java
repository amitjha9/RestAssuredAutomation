package com.practice.base;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {

	public int statusCode_200OK = 200;
	public int statusCode_201OK = 201;
	public int statusCode_204OK = 204;
	public int statusCode_400_Bad_Request = 400;
	public int statusCode_401 = 401;
	public int statusCode_500 = 500;
	public String status_Line_200OK = "HTTP/1.1 200 OK";
	public String status_Line_201OK = "HTTP/1.1 201 Created";
	public String status_Line_204OK = "HTTP/1.1 204 No Content";
	public String status_Line_400_Bad_Request = "HTTP/1.1 400 Bad Request";
	
	public Properties prop;
	//Load the properties file
	public TestBase()
	{
		try {
			prop=new Properties();
			FileInputStream fip= new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/practice/config/config.properties");
			prop.load(fip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
