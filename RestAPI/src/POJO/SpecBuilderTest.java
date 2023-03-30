package POJO;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
RestAssured.baseURI="https://rahulshettyacademy.com";
	
BodyForSerialization serialize = new BodyForSerialization();
serialize.setAccuracy(50);
serialize.setAddress("Near Post Office");
serialize.setLanguage("Marathi");
serialize.setPhone_number("02465525666");
serialize.setWebsite("https://rahulshettyacademy.com");
serialize.setName("MyOwnHouse");
serialize.setTypes(null);
List <String> myList = new ArrayList<String>();	
myList.add("Shoe Park0");
myList.add("Shop");
serialize.setTypes(myList);
Location l = new Location();
serialize.setLocation(l);
l.setLat(-57.87885);
l.setLng(45.582555);

ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
.setContentType(ContentType.JSON).build();

RequestSpecification res=given().spec(req).body(serialize);

Response response = res.when().log().all().post("maps/api/place/add/json")
.then().spec(resspec).extract().response();

String responseString = response.asString();
System.out.println(responseString);
 
	}

}
