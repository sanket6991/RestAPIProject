import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	int sum = 0;
	@Test
	public void  sumOfCourses() {
		JsonPath js = new JsonPath(Payload.coursePrice());
		int count = js.getInt("courses.size()");	
		/*for(int i=0;i<count;i++) {
			int copies = js.get("courses["+i+"].copies");
			int price = js.get("courses["+i+"].price");
			a= copies*price+a;
	*/
		for(int i=0;i<count;i++) {
			int copies = js.get("courses["+i+"].copies");
			int price = js.get("courses["+i+"].price");
			int amount = copies*price;
			System.out.println(amount);
			sum = sum+amount;
		}
		System.out.println(sum);
int purchaseAmount = js.get("dashboard.purchaseAmout");
//System.
Assert.assertEquals(sum, purchaseAmount);
	}	
		

}
