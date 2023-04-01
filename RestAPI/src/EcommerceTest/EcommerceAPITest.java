package EcommerceTest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class EcommerceAPITest {

	public static void main(String[] args) {

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();

// Login Application

		LoginReq loginreq = new LoginReq();
		loginreq.setUserEmail("sanketchakradeo@gmail.com");
		loginreq.setUserPassword("AAA@cz2BZP7npfJ");

		RequestSpecification reqLogin = given().relaxedHTTPSValidation().log().all().spec(req).body(loginreq);
		LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().extract()
				.as(LoginResponse.class);
		System.out.println(loginResponse.getToken());
		String token = loginResponse.getToken();
		System.out.println(loginResponse.getUserId());
		String userId = loginResponse.getUserId();

// AddProductInApplication

		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).build();
		RequestSpecification reqAddProduct = given().relaxedHTTPSValidation().log().all().spec(addProductBaseReq).param("productName", "wewefdw")
				.param("productAddedBy", userId).param("productCategory", "fashion")
				.param("productSubCategory", "Shirts").param("productPrice", "11111")
				.param("productDescription", "Addias Originals").param("productFor", "woman")
				.multiPart("productImage", new File("/home/sanket-laptop/Desktop/Page-2.png"));
		String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all()
				.extract().response().asString();
		JsonPath js = new JsonPath(addProductResponse);
		String productId = js.get("productId");
		System.out.println(productId);
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("authorization", token).setContentType(ContentType.JSON).build();

		OrderDetails orderDetails = new OrderDetails();

// AddOrderApplication

		orderDetails.setCountry("India");
		orderDetails.setProductOrderId(productId);
		List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		orderDetailsList.add(orderDetails);

		Orders orders = new Orders();
		orders.setOrders(orderDetailsList);

		RequestSpecification createOrderReq = given().relaxedHTTPSValidation().log().all().spec(createOrderBaseReq).body(orders);
		String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all()
				.extract().response().toString();
		System.out.println(responseAddOrder);
		
//DeleteProduct
		 
		RequestSpecification deleteProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).setContentType(ContentType.JSON).build();
			
		RequestSpecification deleteProdReq= given().relaxedHTTPSValidation().log().all().spec(deleteProductBaseReq).pathParam("productId", productId);
				
		String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
		//System.out.println(deleteProductResponse);

		JsonPath js1=new JsonPath(deleteProductResponse);
		Assert.assertEquals("Product Added Successfully", js1.get("message"));
	
	}

}
