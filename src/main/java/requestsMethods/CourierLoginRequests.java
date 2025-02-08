package requestsMethods;

import pojo.CourierLoginData;
import pojo.CourierCreatingData;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class CourierLoginRequests extends BaseApi {
    @Step("Send POST request to /api/v1/courier/login and get courierId")
    public static Integer getCourierId() {
        CourierLoginData courier = new CourierLoginData("Statham", "1234");
        Response id = requestSpecification.and().body(courier).post("/api/v1/courier/login");
        Integer courierId = id.then().extract().body().path("id");
        return courierId;
    }

    @Step("Send POST request to /api/v1/courier/login with correct password and login")
    public static Response checkCourierAuthorization() {
        CourierLoginData courier = new CourierLoginData("Statham", "1234");
        return requestSpecification.and().body(courier).post("/api/v1/courier/login");
    }

    @Step("Send POST request to /api/v1/courier/login with incorrect password")
    public static Response checkAuthorizationWithIncorrectPassword() {
        CourierLoginData courier = new CourierLoginData("Statham", "1235");
        return requestSpecification.and().body(courier).post("/api/v1/courier/login");
    }

    @Step("Send POST request to /api/v1/courier/login with incorrect login")
    public static Response checkAuthorizationWithIncorrectLogin() {
        CourierLoginData courier = new CourierLoginData("tatham", "1234");
        return requestSpecification.and().body(courier).post("/api/v1/courier/login");
    }

    @Step("Send POST request to /api/v1/courier/login without login")
    public static Response checkPostRequestWithoutLogin() {
        CourierLoginData courier = new CourierLoginData("1234");
        return requestSpecification.and().body(courier).when().post("/api/v1/courier/login");
    }

    @Step("Send POST request to /api/v1/courier/login without password")
    public static Response checkPostRequestWithoutPassword() {
        CourierCreatingData courier = new CourierCreatingData("Statham");
        return requestSpecification.and().body(courier).post("/api/v1/courier/login");
    }
}
