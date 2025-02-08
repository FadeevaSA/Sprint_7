package requestsMethods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.CourierCreatingData;

public class CourierCreatingRequests extends BaseApi {
    @Step("Send POST request to /api/v1/courier with all parameters")
    public static Response sendPostRequestAllParameters() {
        CourierCreatingData courier = new CourierCreatingData("Statham", "1234", "Джейсон");
        return requestSpecification.and().body(courier).when().post("/api/v1/courier");
    }

    @Step("Send POST request to /api/v1/courier without login")
    public static Response sendPostRequestWithoutLogin() {
        CourierCreatingData courier = new CourierCreatingData("1234", "Джейсон");
        return requestSpecification.and().body(courier).when().post("/api/v1/courier");
    }

    @Step("Send POST request to /api/v1/courier without password")
    public static Response sendPostRequestWithoutPassword() {
        CourierCreatingData courier = new CourierCreatingData("Statham");
        return requestSpecification.and().body(courier).when().post("/api/v1/courier");
    }


    @Step("Send DELETE request to /api/v1/courier/:id")
    public static void deleteCourier(Integer courierId) {
        requestSpecification.delete("/api/v1/courier/{courierId}", courierId);
    }
}
