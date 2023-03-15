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
		return "{\n" + "\n" + "\"dashboard\": {\n" + "\n" + "\"purchaseAmount\": 910,\n" + "\n"
				+ "\"website\": \"rahulshettyacademy.com\"\n" + "\n" + "},\n" + "\n" + "\"courses\": [\n" + "\n" + "{\n"
				+ "\n" + "\"title\": \"Selenium Python\",\n" + "\n" + "\"price\": 50,\n" + "\n" + "\"copies\": 6\n"
				+ "\n" + "},\n" + "\n" + "{\n" + "\n" + "\"title\": \"Cypress\",\n" + "\n" + "\"price\": 40,\n" + "\n"
				+ "\"copies\": 4\n" + "\n" + "},\n" + "\n" + "{\n" + "\n" + "\"title\": \"RPA\",\n" + "\n"
				+ "\"price\": 45,\n" + "\n" + "\"copies\": 10\n" + "\n" + "}\n" + "\n" + "]\n" + "\n" + "}";
	
	
	}

}
