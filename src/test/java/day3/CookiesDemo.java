package day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CookiesDemo {

    @Test(priority = 1)
    void testCookies() {

        given()
                .when()
                .get("https://www.google.com")
                .then()
                //cookie her serferince dynamic generate olduğu için bu fail olcak.
                .cookie("AEC", "AVYB7crX2dhr5yY9YalwWH-1i6TJSJEQ6DoMPwAxQ7b_y2c_N0zPWFFwGg;Path=/;Domain=.google.com;Secure;HttpOnly;Expires=4/7/25, 9:00 AM;SameSite=lax")
                .log().cookies();
    }


    @Test(priority = 2)
    void getCookiesInfo() {

        Response response = given()
                .when()
                .get("https://www.google.com");

        // get single cookie info
        //String cookie_value = response.getCookie("AEC");
        //System.out.println("cookie_value = " + cookie_value);

        //Get all cookies info
        Map<String, String> cookies_values = response.getCookies();

        //System.out.println(cookies_values.keySet());

        for (String k : cookies_values.keySet()) {
            String cookie_value = response.getCookie(k);
            System.out.println("cookie_value = " + cookie_value);
        }




    }


}
