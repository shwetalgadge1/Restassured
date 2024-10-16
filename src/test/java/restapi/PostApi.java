package restapi;


	

	import org.testng.Assert;
	import org.testng.annotations.Test;
	import io.restassured.RestAssured;
	import io.restassured.http.ContentType;
	import io.restassured.response.Response;

	public class PostApi {

	    @Test
	    public void postApiRequest() {
	        // Create the request body as a JSON string
	        String requestBody = "{\n" +
	                "    \"firstname\" : \"John\",\n" +
	                "    \"lastname\" : \"Doe\",\n" +
	                "    \"totalprice\" : 123,\n" +
	                "    \"depositpaid\" : true,\n" +
	                "    \"bookingdates\" : {\n" +
	                "        \"checkin\" : \"2023-01-01\",\n" +
	                "        \"checkout\" : \"2023-01-05\"\n" +
	                "    },\n" +
	                "    \"additionalneeds\" : \"Breakfast\"\n" +
	                "}";

	        Response response = 
	            RestAssured
	            .given()
	            .contentType(ContentType.JSON)
	            .baseUri("https://restful-booker.herokuapp.com/booking")
	            .body(requestBody)
	            .when()
	            .post()
	            .then()
	            .assertThat()
	            .statusCode(200) // or 201, depending on the API's response code for successful creation
	            .extract()
	            .response();

	        // Validate the response
	        Assert.assertTrue(response.getBody().asString().contains("bookingid"));
	    }
	}



