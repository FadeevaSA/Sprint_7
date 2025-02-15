package requestsMethods;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class GetOrdersRequests extends BaseApi {
    public static final String GET_ORDER = "/api/v1/orders?limit=10&page=0";

    @Step("Send Get request to /api/v1/orders?limit=10&page=0 with limit 10 orders")
    public static Response sendGetRequestLimitTenOrders() {
        return requestSpecification.get(GET_ORDER);
    }

}
