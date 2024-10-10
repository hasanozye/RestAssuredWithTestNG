package day5;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class ParsingXMLResponse {

    @Test
    void testXMLResponse() {
        // Approach 1
        given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1")
                .then()
                .statusCode(oneOf(201, 200))
                .header("Content-Type", ContentType.XML.toString())
                .log().body()

        ;


    }


}
