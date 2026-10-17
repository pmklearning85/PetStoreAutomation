package api.endpoints;

/* 
https://petstore.swagger.io

Create User (Post): https://petstore.swagger.io/v2/user
Get User (Get): https://petstore.swagger.io/v2/user/{username}
Update User (Update): https://petstore.swagger.io/v2/user/{username}
Delete User (Delete): https://petstore.swagger.io/v2/user/{username}
Username can't be hardcoded and it is a path parameter since it needs to be created first using create User.
Username is an input to the Get, Update and Delete requests


Base URL = https://petstore.swagger.io/v2
End point1 = user
End point2 = user/{username}
*/

//Route - URLs and end points
public class Routes {
	
	
	//Static - can access base_url variable without creating any object but by class name
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User module
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String del_url=base_url+"/user/{username}";
	
	//Store module
	
	//Pet module
	
		

}
