package day3;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.internal.http.ContentEncoding;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HeadersDemo {

    @Test(priority = 1)
    void testHeaders() {
        given()
                .when()
                .get("https://www.google.com")
                .then()
                .header("Content-Type", ContentType.HTML.withCharset(StandardCharsets.ISO_8859_1))
                .and()
                .header("Content-Encoding", ContentEncoding.Type.GZIP.toString())
                .and()
                .header("Server", "gws")


        ;
    }

    @Test(priority = 2)
    void getHeaders() {
        Response response =
                given()
                        .when()
                        .get("https://www.google.com");

        //get single header info
        //String headerValue = response.getHeader("Content-Type");
        //System.out.println("headerValue = " + headerValue);

        //get all headres info
        Headers headers = response.getHeaders();

        for (Header header : headers) {
            System.out.print("header.getName() = " + header.getName() + "\t");
            System.out.println("header.getValue() = " + header.getValue());
        }


    }

}
