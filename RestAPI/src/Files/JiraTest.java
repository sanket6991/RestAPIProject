package Files;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraTest {

	public static void main(String[] args) {

		RestAssured.baseURI = "http://localhost:8082";
		SessionFilter session = new SessionFilter();
		
//Login Scenario
		given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.body("{\n" + "\n" + "    \"username\": \"sanketchakradeo\",\n"
						+ "    \"password\": \"7tUxrLrGKFe8p5Z\"\n" + "}")
				.log().all().filter(session).when().post("rest/auth/1/session").then().log().all().extract().response()
				.toString();
		
//Add comment
		String expectedMessage="Hi, How are you?";
	String addCommentResponse =	given().relaxedHTTPSValidation().pathParam("id", "10100").log().all().header("Content-Type", "application/json")
				.body("{\n" + "    \"body\": \""+expectedMessage+"\",\n" + "    \"visibility\": {\n"
						+ "        \"type\":\"role\",\n" + "        \"value\":\"Administrators\"\n" + "    }\n" + "}")
				.filter(session).when().post("/rest/api/2/issue/{id}/comment").then().log().all().assertThat()
				.statusCode(201).extract().response().asString();
	JsonPath js1 = new JsonPath(addCommentResponse);
	String commentId = js1.getString("id");
	
// Add Attachment
		given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("id", "10100")
				.header("Content-Type", "multipart/form-data").multiPart("file", new File("info.txt")).when()
				.post("rest/api/2/issue/{id}/attachments").then().log().all().assertThat().statusCode(200);
		
//Get Issue
		String issueDetails = given().relaxedHTTPSValidation().filter(session).pathParam("id", "10100").queryParam("fields", "comment").get("/rest/api/2/issue/{id}").then().log().all().extract().response().toString();
        System.out.println(issueDetails);
		// TODO Auto-generated method stub
        
        JsonPath js2 = new JsonPath(issueDetails);
        int commentsCount = js2.getInt("fields.comment.comments.size()");
        for(int i=0;i<commentsCount;i++)
        {
        	String commentIdIssue= js2.get("fields.comment.comments["+i+"].id").toString();	
        	if(commentIdIssue.equalsIgnoreCase(commentId)) {
        		String message = js2.get("fields.comment.comments["+i+"].body").toString();
        		System.out.println(message);
        		Assert.assertEquals(message, expectedMessage);
        		
        	}
        	
        }

	}

}
