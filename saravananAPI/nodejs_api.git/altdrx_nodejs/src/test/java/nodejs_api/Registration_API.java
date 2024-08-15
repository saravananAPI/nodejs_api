package nodejs_api;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class Registration_API {
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://dev.altdrx.com:3000";
		RestAssured.config = RestAssured.config()
				.sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
	}

	@Test
	public void successfulregistration() {
		String requestBody = "{ "
				+ "\"name\": \"Rahul LS\", "
				+ "\"phone\": \"8618104645\", "
				+ "\"email\": \"sksaranv6j@gmail.com\", "
				+ "\"residentialStatus\": 1"
				+ "}";

		Response successresponse = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("/api/register");

		System.out.println("Response Status Code: " + successresponse.getStatusCode());
		System.out.println("Response Body: " + successresponse.getBody().asString());

		Assert.assertEquals(successresponse.getStatusCode(), 200, "Status code is not 200");

		Boolean successMessage = successresponse.jsonPath().getBoolean("success");
		Assert.assertEquals((Boolean) successMessage, Boolean.TRUE, "Success message is incorrect");
	}
	@Test

	public void Existingemail () {

		String requestBody = "{ \"name\": \"Rahul LS\", \"phone\": \"8628104645\", \"email\": \"sksaranv3j@gmail.com\", \"residentialStatus\": 1}";

		Response Exisitngemailresponse = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("/api/register");

		System.out.println("Response Body: " + Exisitngemailresponse.getBody().asString());

		Boolean emailresponse= Exisitngemailresponse.jsonPath().getBoolean("success");
		Assert.assertEquals((Boolean)emailresponse, Boolean.FALSE, "Success Message is incorrect");
	}

	@Test
	public void Existingphone () {

		String requestBody = "{ \"name\": \"Rahul LS\", \"phone\": \"8870841853\", \"email\": \"sksaranvj99@gmail.com\", \"residentialStatus\": 1}";

		Response Exisitngphoneresponse = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("/api/register");

		System.out.println("Response Body: " + Exisitngphoneresponse.getBody().asString());

		Boolean phoneresponse= Exisitngphoneresponse.jsonPath().getBoolean("success");
		Assert.assertEquals((Boolean)phoneresponse, Boolean.FALSE, "Success Message is incorrect");
	}

	@Test

	public void invalidphone () {
		String requestBody = "{ \"name\": \"Rahul LS\", \"phone\": \"862810464\", \"email\": \"sksaranvj99@gmail.com\", \"residentialStatus\": 1}";

		Response invalidphoneresponse = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("/api/register");
		System.out.println("Response Status Code: " + invalidphoneresponse.getStatusCode());

		System.out.println("Response Body: " + invalidphoneresponse.getBody().asString());

		Assert.assertEquals(invalidphoneresponse.getStatusCode(), 400, "Status code is not 400");

	}

	@Test

	public void invalidemail () {
		String requestBody = "{ \"name\": \"Rahul LS\", \"phone\": \"8628104645\", \"email\": \"sksaranvj.com\", \"residentialStatus\": 1}";

		Response invalidemailresponse = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("/api/register");
		System.out.println("Response Status Code: " + invalidemailresponse.getStatusCode());
		System.out.println("Response Body: " + invalidemailresponse.getBody().asString());

		Assert.assertEquals(invalidemailresponse.getStatusCode(), 400, "Status code is not 400");
	}
	@Test

	public void emptynameparameter () {

		String requestBody = "{ \"name\": \"\", \"phone\": \"8628104645\", \"email\": \"sksaranvj.com\", \"residentialStatus\": 1}";

		Response emptynameresponse = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("/api/register");
		System.out.println("Response Status Code: " + emptynameresponse.getStatusCode());
		System.out.println("Response Body: " + emptynameresponse.getBody().asString());

		Assert.assertEquals(emptynameresponse.getStatusCode(), 400, "Status code is not 400");
	}
	@Test

	public void emptyphoneparameter () {

		String requestBody = "{ \"name\": \"Rahul\", \"phone\": \"\", \"email\": \"sksaranvj.com\", \"residentialStatus\": 1}";

		Response emptyphoneresponse = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("/api/register");
		System.out.println("Response Status Code: " + emptyphoneresponse.getStatusCode());
		System.out.println("Response Body: " + emptyphoneresponse.getBody().asString());

		Assert.assertEquals(emptyphoneresponse.getStatusCode(), 400, "Status code is not 400");
	}
	@Test
	public void emptyemailparameter () {

		String requestBody = "{ \"name\": \"Rahul\", \"phone\": \"8628104645\", \"email\": \"\", \"residentialStatus\": 1}";

		Response emptyemailresponse = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("/api/register");
		System.out.println("Response Status Code: " + emptyemailresponse.getStatusCode());
		System.out.println("Response Body: " + emptyemailresponse.getBody().asString());

		Assert.assertEquals(emptyemailresponse.getStatusCode(), 400, "Status code is not 400");
	}


}


