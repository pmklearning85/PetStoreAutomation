package api.endpoints;
import static io.restassured.RestAssured.*;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

import api.payload.User;

//UserEndPoints.Java
// Created to perform the End points- CRUD Methods implementation - Create, Read, Update and Delete requests to the User API.
// its not tests but defininig these methods for reusability. We will call these methods under Test for actual validations.

public class UserEndPoints {
	
	public static Response createUser(User payload) //Response is a return type
	{
		Response res = given()
			.contentType(ContentType.JSON) //Defined in the Swagger.io
			.accept(ContentType.JSON) //Defined in the Swagger.io
			.body(payload)
		.when()
			.post(Routes.post_url);
		
		return res;
	}

	public static Response getUser(String username)
	{
		Response res = given()
			.pathParam("username", username)
		.when()
			.get(Routes.get_url);
		
		return res;
	}
	
	public static Response updateUser(String username, User payload) //Response is a return type
	{
		Response res = given()
			.contentType(ContentType.JSON) //Defined in the Swagger.io
			.accept(ContentType.JSON) //Defined in the Swagger.io
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(Routes.update_url);
		return res;
	}
	
	public static Response deleteUser(String username)
	{
		Response res = given()
			.pathParam("username", username)
		.when()
			.delete(Routes.del_url);
		
		return res;
	}

}
