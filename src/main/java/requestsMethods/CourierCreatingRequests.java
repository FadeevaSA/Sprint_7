package requestsMethods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.CourierCreatingData;

public class CourierCreatingRequests extends BaseApi {
    public static final String COURIER_CREATING = "/api/v1/courier";
    public static final String DELETE_COURIER = "/api/v1/courier/{courierId}";

    @Step("Send POST request to /api/v1/courier with all parameters")
    public static Response sendPostRequestAllParameters() {
        CourierCreatingData courier = new CourierCreatingData("Statham", "1234", "Джейсон");
        return requestSpecification.and().body(courier).when().post(COURIER_CREATING);
    }

    @Step("Send POST request to /api/v1/courier without login")
    public static Response sendPostRequestWithoutLogin() {
        CourierCreatingData courier = new CourierCreatingData("1234", "Джейсон");
        return requestSpecification.and().body(courier).when().post(COURIER_CREATING);
    }

    @Step("Send POST request to /api/v1/courier without password")
    public static Response sendPostRequestWithoutPassword() {
        CourierCreatingData courier = new CourierCreatingData("Statham");
        return requestSpecification.and().body(courier).when().post(COURIER_CREATING);
    }


    @Step("Send DELETE request to /api/v1/courier/:id")
    public static void deleteCourier(Integer courierId) {
        requestSpecification.delete(DELETE_COURIER, courierId);
    }
}
