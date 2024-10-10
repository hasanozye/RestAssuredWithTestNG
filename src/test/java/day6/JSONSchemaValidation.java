package day6;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;


public class JSONSchemaValidation {

    //herbir datayı tek tek type'larını çekip int, string, onject,array mi vs. diye kontrol etmek yerine,
    //jsonschemavalidatior yaparak bütün resposne body'nin bütün type'larını kontrol etmiş oluyoruz.
    @Test
    void jsonSchemaValidation() {
        given()
                .when()
                .get("http://localhost:3000/store")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"))

        ;
    }


}
