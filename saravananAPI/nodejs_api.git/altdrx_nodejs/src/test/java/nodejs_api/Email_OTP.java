package nodejs_api;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class Email_OTP {

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://dev.altdrx.com:3000";
		RestAssured.config = RestAssured.config()
				.sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
	}

	@Test
	public void emailOTP() {
		String requestBody = "{"
				+ "\"email\": \"sarvnaraj@gmail.com\","
				+ "\"type\": \"login\","
				+ "\"debug\": \"true\""
				+ "}";

		Response emailOTP = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("api/emailotp");

		System.out.println("Response Status Code: " + emailOTP.getStatusCode());
		System.out.println("Response Body: " + emailOTP.getBody().asString());


		Assert.assertEquals(emailOTP.getStatusCode(), 200, "Status code is not 200");

		Boolean OTPsuccess = emailOTP.jsonPath().getBoolean("success");
		Assert.assertEquals((Boolean)OTPsuccess, Boolean.TRUE, "Success message is incorrect");
	}
	@Test
	public void testinvalidemail () {

		String requestBody = 
				"{"
						+ "\"email\": \"sarvnaraj@.com\","
						+ "\"type\": \"login\","
						+ "\"debug\": \"true\""
						+ "}"; 

		Response testinvalidemail = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("api/emailotp");
		System.out.println("Response Status Code: " + testinvalidemail.getStatusCode());
		System.out.println("Response Body: " + testinvalidemail.getBody().asString());

		Assert.assertEquals(testinvalidemail.getStatusCode(), 400, "Status code is not 400");
	}
	@Test
	public void testemptyemail () {
		String requestBody = 
				"{"
						+ "\"email\": \"\","
						+ "\"type\": \"login\","
						+ "\"debug\": \"true\""
						+ "}";

		Response testemptyemail = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("api/emailotp");
		System.out.println("Response Status Code: " + testemptyemail.getStatusCode());
		System.out.println("Response Body: " + testemptyemail.getBody().asString());
		Assert.assertEquals(testemptyemail.getStatusCode(), 400, "Status code is not 400");
	}

	@Test	
	public void testemptytype() {
		String requestBody = 
				"{"
						+ "\"email\": \"saravanan28raj@gmail.com\","
						+ "\"type\": \"\","
						+ "\"debug\": \"true\""
						+ "}";

		Response testemptytype = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("api/emailotp");
		System.out.println("Response Status Code: " + testemptytype.getStatusCode());
		System.out.println("Response Body: " + testemptytype.getBody().asString());
		Assert.assertEquals(testemptytype.getStatusCode(), 400, "Status code is not 400");
	}
	@Test

	public void testemptydebug () {
		String requestBody = 
				"{"
						+ "\"email\": \"saravanan28raj@gmail.com\","
						+ "\"type\": \"login\","
						+ "\"debug\": \"\""
						+ "}";

		Response testemptydebug = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("api/emailotp");
		System.out.println("Response Status Code: " + testemptydebug.getStatusCode());
		System.out.println("Response Body: " + testemptydebug.getBody().asString());
		Assert.assertEquals(testemptydebug.getStatusCode(), 400, "Status code is not 400");
	}

}
