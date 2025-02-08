package responseMethods;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.notNullValue;

public class OrdersCreatingResponse {
    @Step("Compare response text and response code 201")
    public static void check201CreatedResponse(Response response) {
        response.then().assertThat().body("track", notNullValue()).statusCode(201);
    }

}
