package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class ParsingJSONResponseData {

    @Test(priority = 1)
    void testJsonResponse() {
        // Approach 1

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store")
                .then()
                .statusCode(oneOf(200, 201))
                .header("Content-Type", ContentType.JSON.toString())
                .body("book[3].title", equalTo("The Lord of the Rings"));

        // Approach 2
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store");


        assertEquals(response.getStatusCode(), 200); // validation 1
        assertEquals(response.getHeader("Content-Type"), ContentType.JSON.toString());

        String bookName = response.jsonPath().get("book[3].title").toString();
        assertEquals(bookName, "The Lord of the Rings");
    }

    @Test
    void testJsonResponseBodyData() {

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("http://localhost:3000/store");


        //JSONObject Class
        JSONObject jsonObject = new JSONObject(response.asString()); // converting response to json object type


        boolean status = false;

        for (int i = 0; i < jsonObject.getJSONArray("book").length(); i++) {
            String bookTitle = jsonObject.getJSONArray("book").getJSONObject(i).get("title").toString();
            if (bookTitle.equalsIgnoreCase("The Lord of the Rings")) {
                status = true;
                break;
            }
        }
        assertTrue(status);
        double totalPrice = 0;
        //validate total price of books
        for (int i = 0; i < jsonObject.getJSONArray("book").length(); i++) {
            String price = jsonObject.getJSONArray("book").getJSONObject(i).get("price").toString();
            totalPrice += Double.parseDouble(price);
        }
        System.out.println("totalPrice = " + totalPrice);
        assertEquals(totalPrice,526.0);

    }

}
