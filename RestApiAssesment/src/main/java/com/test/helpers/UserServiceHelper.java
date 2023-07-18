package com.test.helpers;

import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restTests.POJOs.CreateUSerPOJO;
import com.restTests.POJOs.DeleteUserPOJO;
import com.restTests.POJOs.LoginDataPOJO;
import com.restTests.POJOs.UpdateUserPOJO;
import com.test.constants.Endpoints;
import com.test.utils.Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;


public class UserServiceHelper {
	
	static Response response = null;
	static String token = null;

	
	public static String getBaseUri () throws IOException {
		String baseURI = Utils.getConfigProperty("baseURL");
		return baseURI;
	}

	public static Response LoginToApplication () throws IOException {
		String userName = Utils.getConfigProperty("userName");
		String password = Utils.getConfigProperty("password");
		
		LoginDataPOJO data=new LoginDataPOJO();
		data.setUsername(userName);
		data.setPassword(password);

		response = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(data)
				.when()
				.post(Endpoints.LOGIN);
		return response;
	}
	
	public static String getToken () throws IOException {
		response = LoginToApplication();
		return token = response.jsonPath().get("[0].token");
	}
	
	
	
	
}
