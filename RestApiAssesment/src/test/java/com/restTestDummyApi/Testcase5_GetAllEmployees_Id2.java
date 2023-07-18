package com.restTestDummyApi;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restTestDummyApi.POJO.EmployeesPOJO;
import com.test.constants.Endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Testcase5_GetAllEmployees_Id2 {

	@BeforeClass
	public void init() {
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
	}

	@Test
	public void getEmployees2() {

		Response res=RestAssured.given()
				.log().all()
				.contentType(ContentType.JSON)
				.when()
				.get("/employee/2");

		
		res
		.then()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.time(lessThan(5000L));

		res.prettyPrint();
		System.out.println("The Status code = " + res.getStatusCode());
		System.out.println("The Status Line = " + res.getStatusLine());
		System.out.println("Total number of records = "+res.body().jsonPath().get("size()"));
		
		String responseName =  res.body().jsonPath().getString("data.employee_name");
		int responseSalary = res.body().jsonPath().getInt("data.employee_salary");
		int responseAge = res.body().jsonPath().getInt("data.employee_age");
		
		System.out.println("Name in Response=" +responseName);
		System.out.println("Salary in Response=" +responseSalary);
		System.out.println("Age in Response=" +responseAge);
		
		Assert.assertEquals("Garrett Winters", responseName); 
		Assert.assertEquals(170750, responseSalary);
		Assert.assertEquals(63, responseAge);


	}
}
