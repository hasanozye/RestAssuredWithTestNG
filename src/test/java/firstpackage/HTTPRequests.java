package firstpackage;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HTTPRequests {

    int id;

    @Test
    void getUser() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200);
    }

    @Test(priority = 1)
    void getUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all()

        ;
    }

    @Test(priority = 2)
    void createUser() {

        HashMap data = new HashMap();
        data.put("name", "hasan");
        data.put("job", "Test Automation Engineer");


        id = given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");/*.then()
                .statusCode(oneOf(201, 200))
                .log().body()
*/
    }

    @Test(dependsOnMethods = {"createUser"}, priority = 3)
    void UpdateUser() {
        HashMap requestBody = new HashMap();
        requestBody.put("name", "Özyer");
        requestBody.put("job", "SDET");

        given()
                .contentType(ContentType.JSON)
                .when()
                .put("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(oneOf(200, 201))
                .log().body();

    }

    @Test(dependsOnMethods = "createUser", priority = 4)
    void deleteUser() {
        given()
                .when()
                .delete("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(204)
                .log().body()

        ;
    }


}
