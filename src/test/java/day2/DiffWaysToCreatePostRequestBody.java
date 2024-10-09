package day2;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * 1) Hashmap
 * 2) using org.json
 * 3) using POJO (Plain Old Java Object)
 * 4) using external json file
 */
public class DiffWaysToCreatePostRequestBody {

    // 1) Hashmap
    // This approach is applicable to small set of data.
    @Test(priority = 1)
    void testPostUsingHashMap() {
        HashMap data = new HashMap();
        data.put("name", "Scott");
        data.put("location", "France");
        data.put("phone", "123456");

        String[] courseArr = {"C", "C++"};
        data.put("courses", courseArr);

        given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(oneOf(201, 200))
                .body("name", equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-Type", "application/json")
                .log().body()
        ;

    }

    @Test(priority = 2)
    void testDelete() {
        given()
                .when()
                .delete("http://localhost:3000/students/5e70")
                .then()
                .statusCode(oneOf(200, 201, 204, 202));
    }


    // 2) Post request body using org.json library
    @Test
    void testPostUsingJsonLibrary() {
        JSONObject data = new JSONObject();
        data.put("name", "Hasan");
        data.put("location", "Bengalore");
        data.put("phone", "34576");

        String[] coursesArr = {"Java", "Ruby"};
        data.put("courses", coursesArr);

        given()
                .body(ContentType.JSON)
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(oneOf(200, 201))
                .body("name", equalTo("Hasan"))
                .body("location", equalTo("Bengalore"))
                .body("phone", equalTo("34576"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("Ruby"))
                .header("Content-Type", "application/json")
                .log().body()

        ;
    }

    // 3) Post request body using POJO Class
    @Test
    void testPostUsingPOJO() {
        POJO_PostRequest data = new POJO_PostRequest();
        data.setName("Ahmed");
        data.setLocation("Japan");
        data.setPhone("41154313");

        String[] courseArr = {"C#", "Javascript"};
        data.setCourses(courseArr);

        given()
                .body(ContentType.JSON)
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(oneOf(200, 201, 204))
                .log().body()

        ;
    }

    // 4) Post Request body using External JSON File

    @Test
    void testPostUsingExternalJsonFile() throws FileNotFoundException {
        File file = new File("src/test/java/day2/body.json");
        FileReader fileReader = new FileReader(file);

        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject jsonObject = new JSONObject(jsonTokener);

        given()
                .body(ContentType.JSON)
                .body(jsonObject.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(oneOf(200, 201, 204))
                .log().body()
        ;

    }


}
