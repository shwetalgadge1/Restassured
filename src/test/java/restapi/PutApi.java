package restapi;



import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutApi {

    @Test
    public void putApiRequest() {
        // Create the request body as a JSON string
        String requestBody = "{\n" +
                "    \"firstname\" : \"Jane\",\n" +
                "    \"lastname\" : \"Doe\",\n" +
                "    \"totalprice\" : 150,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-02-01\",\n" +
                "        \"checkout\" : \"2023-02-10\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

        // Assuming you already have a booking ID to update
        int bookingId = 1;

        Response response = 
            RestAssured
            .given()
            .contentType(ContentType.JSON)
            .baseUri("https://restful-booker.herokuapp.com/booking/" + bookingId)
            .body(requestBody)
            .when()
            .put()
            .then()
            .assertThat()
            .statusCode(200) // PUT usually returns 200 OK if the update is successful
            .extract()
            .response();

        // Validate the response
        Assert.assertTrue(response.getBody().asString().contains("Jane"));
    }
}

