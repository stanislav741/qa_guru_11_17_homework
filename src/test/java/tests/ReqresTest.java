package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresTest {

    @Test
    void apiUserRegister() {

//        Response: {"id":4,"token":"QpwL5tke4Pnpja7X4"}

        String registerData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        given().
                body(registerData).
                contentType(JSON).
        when().
                post("https://reqres.in/api/register").
        then().
                statusCode(200).
                body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void apiUserLogin() {

        String loginData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given().
                body(loginData).
                contentType(JSON).
        when().
                post("https://reqres.in/api/login").
        then().
                statusCode(200).
                body("token", is("QpwL5tke4Pnpja7X4"));
    }
}
