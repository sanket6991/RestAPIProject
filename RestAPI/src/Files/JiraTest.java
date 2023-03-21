package Files;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class JiraTest {

	public static void main(String[] args) {

		RestAssured.baseURI = "http://localhost:8082";
		SessionFilter session = new SessionFilter();
//Login Scenario
		String response = given().header("Content-Type", "application/json")
				.body("{\n" + "\n" + "    \"username\": \"sanketchakradeo\",\n"
						+ "    \"password\": \"7tUxrLrGKFe8p5Z\"\n" + "}")
				.log().all().filter(session).when().post("rest/auth/1/session").then().log().all().extract().response()
				.toString();
//Add comment
		given().pathParam("id", "10100").log().all().header("Content-Type", "application/json")
				.body("{\n" + "    \"body\": \"This is my first comment\",\n" + "    \"visibility\": {\n"
						+ "        \"type\":\"role\",\n" + "        \"value\":\"Administrators\"\n" + "    }\n" + "}")
				.filter(session).when().post("/rest/api/2/issue/{id}/comment").then().log().all().assertThat()
				.statusCode(201);
// Add Attachment
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("id", "10100").header("Content-Type","multipart/form-data").multiPart("file", new File("info.txt")).when().post("rest/api/2/issue/{id}/attachments")
.then().log().all().assertThat().statusCode(200);






		// TODO Auto-generated method stub

	}

}
