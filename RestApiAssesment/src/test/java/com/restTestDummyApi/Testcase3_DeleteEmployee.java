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

public class Testcase3_DeleteEmployee {

	String responseName =  null;
	int responseSalary = 0;
	int responseAge = 0;
	int responseId = 0;
	
	@BeforeClass
	public void init() {
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
	}

	@Test
	public void createEmployee() {
		
		CreateEmployeePOJO dataIn = new CreateEmployeePOJO();

		dataIn.setName("test2");
		dataIn.setSalary(1234);
		dataIn.setAge(35);

		Response res=RestAssured.given()				
				.contentType("application/json")
				.body(dataIn)
				.when()
				.post(Endpoints.CREATE);
		
		res
		.then()
		.statusCode(200);
		res.prettyPrint();

		responseName =  res.body().jsonPath().getString("data.name");
		responseSalary = res.body().jsonPath().getInt("data.salary");
		responseAge = res.body().jsonPath().getInt("data.age");
		responseId = res.body().jsonPath().getInt("data.id");
		
		System.out.println("Name in Response=" +responseName);
		System.out.println("Salary in Response=" +responseSalary);
		System.out.println("Age in Response=" +responseAge);
		System.out.println("Id in Response=" +responseId);

	}

	@Test(dependsOnMethods = "createEmployee")
	public void deleteEmployee() {
		
	System.out.println("Inside delete employee responseId = " +responseId);
		Response res = RestAssured.given()
				.contentType("application/json")
				.when()
				.delete("/delete/"+responseId);
		res.then().statusCode(200);
		res.prettyPrint();
		
	}
}
