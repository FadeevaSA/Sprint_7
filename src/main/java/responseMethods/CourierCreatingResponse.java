package responseMethods;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class CourierCreatingResponse {
    @Step("Compare response text and response code 201")
    public static void checkSuccessfulResponse(Response response) {
        response.then().assertThat().body("ok", equalTo(true)).and().statusCode(201);
    }

    @Step("Compare response text and response code 409")
    public static void check409ErrorResponse(Response response) {
        response.then().assertThat().body("message", equalTo("Этот логин уже используется")).and().statusCode(409);
    }

    @Step("Compare response text and response code 400")
    public static void check400ErrorResponse(Response response) {
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")).and().statusCode(400);
    }
}
