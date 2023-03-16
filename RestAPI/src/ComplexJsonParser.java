import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParser {

	public static void main(String[] args) {
		

		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(Payload.coursePrice());

		int count = js.getInt("courses.size()");
		System.out.println(count);

		int totalAmout = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmout);
		// print title of first course

		String titleFirstCourse = js.get("courses[0].title");
		System.out.println(titleFirstCourse);

		// Print all course tiltles and there respective prices
		for (int i = 0; i < count; i++) {
			String courseTitles = js.get("courses[" + i + "].title");
			System.out.println(js.get("courses[" + i + "].price").toString());
			System.out.println(courseTitles);
		}
		System.out.println("Print total number of copies sold by RPA course");
		for (int i = 0; i < count; i++) {
			String courseTitles = js.get("courses[" + i + "].title");
			if (courseTitles.equalsIgnoreCase("RPA")) {
				int copies = js.get("courses[" + i + "].copies");
				System.out.println(copies);
				break;

				// copies sold
			}

		}

	}

}
