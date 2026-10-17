package api.endpoints;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import api.payload.User;




public class UserEndPointsRoutesProperties {
	
	
	//load properties file
	
	static ResourceBundle getURL()
	{
		//by default this method takes the file from src/test/resources
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	
	public static Response createUser(User payload) //Response is a return type
	{
		String post_url2 = getURL().getString("post_url");
		
		Response res = given()
			.contentType(ContentType.JSON) //Defined in the Swagger.io
			.accept(ContentType.JSON) //Defined in the Swagger.io
			.body(payload)
		.when()
			.post(post_url2);
		
		return res;
	}

	public static Response getUser(String username)
	{
		String get_url2 = getURL().getString("get_url");
		Response res = given()
			.pathParam("username", username)
		.when()
			.get(get_url2);
		
		return res;
	}
	
	public static Response updateUser(String username, User payload) //Response is a return type
	{
		String update_url2 = getURL().getString("update_url");
		Response res = given()
			.contentType(ContentType.JSON) //Defined in the Swagger.io
			.accept(ContentType.JSON) //Defined in the Swagger.io
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(update_url2);
		return res;
	}
	
	public static Response deleteUser(String username)
	{
		String delete_url2 = getURL().getString("del_url");
		Response res = given()
			.pathParam("username", username)
		.when()

			.delete(delete_url2);
		
		return res;
	}

}
