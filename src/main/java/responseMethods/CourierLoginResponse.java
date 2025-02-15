package responseMethods;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class CourierLoginResponse {
    @Step("Compare response text and response code 400")
    public static void check400ErrorResponse(Response response) {
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа")).and().statusCode(400);
    }

    @Step("Compare response text and response code 404 Not Found")
    public static void check404ErrorResponse(Response response) {
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
    }

    @Step("Compare response text and response code 200")
    public static void check200SuccessResponse(Response response) {
        response.then().assertThat().body("id", greaterThan(0)).and().statusCode(200);
    }
}
