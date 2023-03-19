package Files;

public class Payload {

	public static String addPlace()

	{
		return "{\n" + "  \"location\": {\n" + "    \"lat\": -20.383494,\n" + "    \"lng\": 22.427362\n" + "  },\n"
				+ "  \"accuracy\": 40,\n" + "  \"name\": \"my1 house\",\n"
				+ "  \"phone_number\": \"(+91) 000 893 3937\",\n" + "  \"address\": \"101, Datta Colony, India 09\",\n"
				+ "  \"types\": [\n" + "    \"kk garden\",\n" + "    \"my shop\"\n" + "  ],\n"
				+ "  \"website\": \"http://google.com\",\n" + "  \"language\": \"Marathi-IN\"\n" + "}";
	}

	public static String coursePrice() {
		return "{\r\n" + "  \"dashboard\": {\r\n" + "    \"purchaseAmount\": 1162,\r\n"
				+ "    \"website\": \"rahulshettyacademy.com\"\r\n" + "  },\r\n" + "  \"courses\": [\r\n" + "    {\r\n"
				+ "      \"title\": \"Selenium Python\",\r\n" + "      \"price\": 50,\r\n" + "      \"copies\": 6\r\n"
				+ "    },\r\n" + "    {\r\n" + "      \"title\": \"Cypress\",\r\n" + "      \"price\": 40,\r\n"
				+ "      \"copies\": 4\r\n" + "    },\r\n" + "    {\r\n" + "      \"title\": \"RPA\",\r\n"
				+ "      \"price\": 45,\r\n" + "      \"copies\": 10\r\n" + "    },\r\n" + "     {\r\n"
				+ "      \"title\": \"Appium\",\r\n" + "      \"price\": 36,\r\n" + "      \"copies\": 7\r\n"
				+ "    }\r\n" + "    \r\n" + "    \r\n" + "    \r\n" + "  ]\r\n" + "}\r\n" + "";

	}

	public static String addBook(String isbn, String aisle) {
		String Payload = "{\n"
				+ "\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\n"
				+ "\"isbn\":\""+isbn+"\",\n"
				+ "\"aisle\":\""+aisle+"\",\n"
				+ "\"author\":\"John foe\"\n"
				+ "}";
		return Payload;	
	}

}
