package POJO;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class SerializeTest {

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




Response res=given().log().all().queryParam("Key", "qaclick123")
.body(serialize)
.when().log().all().post("maps/api/place/add/json")
.then().assertThat().statusCode(200).extract().response();

String responseString=res.asString();
System.out.println(responseString);
 
	}

}
