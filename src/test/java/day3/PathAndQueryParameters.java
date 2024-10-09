package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {


    // Query is not a variable. Query parametreleri önceden istekle birlikte gidiyor onlara özel isim girmiyoruz.
    // Path parametrelerine is like a variable. onlara isim veriyoruz

    // Path parameters is basically root in the request. Where it should go?
    // Query parameters is basically filtering the data. Once it reaches the server,
    // it will filter the data in query request.
    @Test
    void testQueryAndPathParams() {

        given()
                .pathParams("mypath", "users") // path params
                .queryParam("page", 2) // query parameter
                .queryParam("id", 5) // query parameter
                .when()
                .get("https://reqres.in/api/{mypath}")
                .then()
                .statusCode(oneOf(200, 201, 204))
                .log().body()
        ;
    }


}
