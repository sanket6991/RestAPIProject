package OAuth_2_0;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class OAuth_Test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		//WebDriverManager.edgedriver().setup();
		
		  org.openqa.selenium.chrome.ChromeOptions ops = new
		  org.openqa.selenium.chrome.ChromeOptions();
		  ops.addArguments("--remote-allow-origins=*"); 
		  ops.addArguments("--disable");
		  org.openqa.selenium.remote.DesiredCapabilities cp = new
		  org.openqa.selenium.remote.DesiredCapabilities();
		  cp.setCapability(org.openqa.selenium.chrome.ChromeOptions.CAPABILITY, ops);
		  ops.addArguments("--disable-web-security","--user-data-dir=true","--allow-running-insecure-content" );
		  ops.merge(cp);
		  
		 
		driver = new ChromeDriver(ops);
	//	driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("testselenium103@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("QAZcdetgb23@");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		String url = driver.getCurrentUrl();
		System.out.println(url);
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&scope")[0];
		System.out.println(code);



		//driver.findElement().click();
		//driver.findElement(null)
		
		String accessTokenResponse = given().urlEncodingEnabled(false).queryParam("code", code).queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		System.out.println(accessToken);


		
		//Response
		String response = given().contentType("application/json").queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);

		// TODO Auto-generated method stub

	}

}
