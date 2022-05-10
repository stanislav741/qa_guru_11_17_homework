package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresTest {

    @Test
    void apiUserRegister() {

//        Response: {"id":4,"token":"QpwL5tke4Pnpja7X4"}

        String bodyUserRegister = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        given().
                body(bodyUserRegister).
                contentType(JSON).
        when().
                post("https://reqres.in/api/register").
        then().
                statusCode(200).
                body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void apiUserLogin() {

        String bodyUserLogin = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given().
                body(bodyUserLogin).
                contentType(JSON).
        when().
                post("https://reqres.in/api/login").
        then().
                statusCode(200).
                body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void apiResourceList() {

        String responseResourceListExpected = "{\"page\":1,\"per_page\":6,\"total\":12,\"total_pages\":2,\"data\":" +
            "[{\"id\":1,\"name\":\"cerulean\",\"year\":2000,\"color\":\"#98B2D1\",\"pantone_value\":\"15-4020\"}," +
            "{\"id\":2,\"name\":\"fuchsia rose\",\"year\":2001,\"color\":\"#C74375\",\"pantone_value\":\"17-2031\"}," +
            "{\"id\":3,\"name\":\"true red\",\"year\":2002,\"color\":\"#BF1932\",\"pantone_value\":\"19-1664\"}" +
            ",{\"id\":4,\"name\":\"aqua sky\",\"year\":2003,\"color\":\"#7BC4C4\",\"pantone_value\":\"14-4811\"}," +
            "{\"id\":5,\"name\":\"tigerlily\",\"year\":2004,\"color\":\"#E2583E\",\"pantone_value\":\"17-1456\"}," +
            "{\"id\":6,\"name\":\"blue turquoise\",\"year\":2005,\"color\":\"#53B0AE\",\"pantone_value\":" +
            "\"15-5217\"}],\"support\":{\"url\":\"https://reqres.in/#support-heading\"," +
            "\"text\":\"To keep ReqRes free, contributions towards server costs are appreciated!\"}}";

        String responseResourceList =
        given().
                contentType(JSON).
        when().
                get("https://reqres.in/api/unknown").
        then().
                statusCode(200).
                extract().response().asString();

        assertEquals(responseResourceListExpected, responseResourceList);
    }

    @Test
    void apiUserSingle() {

        String responseUserSingleExpected = "{\"data\":{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":" +
                "\"Janet\",\"last_name\":\"Weaver\",\"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"}," +
                "\"support\":{\"url\":\"https://reqres.in/#support-heading\"," +
                "\"text\":\"To keep ReqRes free, contributions towards server costs are appreciated!\"}}";

        String responseUserSingle =
        given().
                contentType(JSON).
        when().
                get("https://reqres.in/api/users/2").
        then().
                statusCode(200).
                extract().response().asString();

        assertEquals(responseUserSingleExpected, responseUserSingle);
    }

    @Test
    void apiEntryCreate() {

        String bodyEntryCreate = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given().
                body(bodyEntryCreate).
                contentType(JSON).
        when().
                post("https://reqres.in/api/users").
        then().
                statusCode(201).
                body("name", is("morpheus")).
                body("job", is("leader"));
    }

}
