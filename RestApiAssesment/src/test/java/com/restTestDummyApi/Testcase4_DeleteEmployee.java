package com.restTestDummyApi;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restTestDummyApi.POJO.CreateEmployeePOJO;
import com.restTestDummyApi.POJO.EmployeeResponsePOJO;
import com.test.constants.Endpoints;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Testcase4_DeleteEmployee {

	String responseName =  null;
	int responseSalary = 0;
	int responseAge = 0;
	int responseId = 0;
	
	@BeforeClass
	public void init() {
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
	}

	@Test
	public void deleteEmployee() {
		
	System.out.println("Inside delete employee responseId = " +responseId);
		Response res = RestAssured.given()
				.contentType("application/json")
				.when()
				.delete("/delete/0");
		res.then().statusCode(400);
		res.prettyPrint();
		
	}
}
