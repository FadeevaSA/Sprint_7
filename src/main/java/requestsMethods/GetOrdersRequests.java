package requestsMethods;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class GetOrdersRequests extends BaseApi {
    @Step("Send Get request to /api/v1/orders?limit=10&page=0 with limit 10 orders")
    public static Response sendGetRequestLimitTenOrders() {
        return requestSpecification.get("/api/v1/orders?limit=10&page=0");
    }

}
