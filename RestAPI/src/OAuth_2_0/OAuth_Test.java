package OAuth_2_0;

import static io.restassured.RestAssured.given;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import POJO.Api;
import POJO.GetCourse;
import POJO.WebAutomation;
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	//	driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("testselenium103@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
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
		String[] courseTitles1 = {"Selenium Webdriver Java", "Cypress", "Protractor"};	
		GetCourse gc = given().contentType("application/json").queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		System.out.println(gc.getInstructor());
		System.out.println(gc.getLinkedIn());
		//System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		/*
		 * driver.get("www.google.com");
		 * driver.findElement(By.xpath("//*[@id=\"gb\"]/div/div[2]/div[2]/div/a/img")).
		 * click(); driver.findElement(By.xpath(
		 * "//*[@id=\"yDmH0d\"]/c-wiz/div/div/div/div/div[2]/div[2]/span/a"));
		 * driver.quit();
		 */
		List<String> expectedList = Arrays.asList(courseTitles1);
		List <Api> apiCourses =gc.getCourses().getApi();
		for(int i=0;i<apiCourses.size();i++) {
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
		ArrayList <String> actualList = new ArrayList<String>();
		
		List <WebAutomation> courseTitles = gc.getCourses().getWebAutomation();
		for (int i=0;i<courseTitles.size();i++) {
			actualList.add(courseTitles.get(i).getCourseTitle());
		}
		Assert.assertTrue(actualList.equals(expectedList));

		// TODO Auto-generated method stub

	}

}
