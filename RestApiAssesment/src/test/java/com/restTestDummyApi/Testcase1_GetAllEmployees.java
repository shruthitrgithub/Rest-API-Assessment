package com.restTestDummyApi;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restTestDummyApi.POJO.EmployeeResponsePOJO;
import com.restTestDummyApi.POJO.EmployeesPOJO;
import com.test.constants.Endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Testcase1_GetAllEmployees {

	@BeforeClass
	public void init() {
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
	}

	@Test
	public void getEmployees() {

		Response res=RestAssured.given()
				.log().all()
				.contentType(ContentType.JSON)
				.when()
				.get(Endpoints.EMPLOYEES);

		
		res
		.then()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.time(lessThan(5000L));

		res.prettyPrint();
		System.out.println("The Status code = " + res.getStatusCode());
		System.out.println("The Status Line = " + res.getStatusLine());	
		System.out.println("Total number of records = "+res.body().jsonPath().get("size()"));

//		EmployeeResponsePOJO ob =new EmployeeResponsePOJO();
//		EmployeeResponsePOJO[] list=res.as(EmployeeResponsePOJO[].class);// deserialization
//		System.out.println("Id is ="+list[0].id);
		
	}
}
