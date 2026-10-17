package api.test;
import java.lang.System.Logger;


import org.checkerframework.common.returnsreceiver.qual.This;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import api.payload.User;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;

public class UserModuleTests {
	
	Faker faker; //Variable creation
	User userPayload; //Variable creation
	public org.apache.logging.log4j.Logger loggerv;
	
	@BeforeClass //it will execute first before the test methods
	public void setup()
	{
		faker = new Faker(); //Object creation
		userPayload = new User(); //Object creation
		
		userPayload.setId(faker.idNumber().hashCode()); //randomly
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		loggerv = LogManager.getLogger(getClass());
		
		
	}

	@Test(priority=1)
	public void testPostUser()
	{
		loggerv.info("**********creating user*************");
		Response res = api.endpoints.UserEndPoints.createUser(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("================================End of testPostUser===============================");
		loggerv.info("**********user is created*************");
	}
	

	@Test(priority=2)
	public void testGetUserByName()
	{
		Response res = api.endpoints.UserEndPoints.getUser(this.userPayload.getUsername()); //Extract username from payload
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("================================End of testGetUserByName===============================");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		//update data using payload

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response res= api.endpoints.UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//Check firstname, lastname, email after updation
		
		Response resafterupdate = api.endpoints.UserEndPoints.getUser(this.userPayload.getUsername()); //Extract username from payload
		resafterupdate.then().log().all();
		Assert.assertEquals(resafterupdate.getStatusCode(), 200);
		
		System.out.println("================================End of testUpdateUserByName===============================");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		Response res = api.endpoints.UserEndPoints.deleteUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		System.out.println("================================End of testDeleteUserByName===============================");
	}
	
}
