package requestsMethods;

import pojo.OrdersCreatingData;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class OrdersCreatingRequests extends BaseApi {
    private final OrdersCreatingData orderData;

    public OrdersCreatingRequests(OrdersCreatingData orderData) {
        this.orderData = orderData;
    }

    @Step("Send POST request to /api/v1/orders for creating orders")
    public Response sendPostRequestForCreatingOrders() {
        return requestSpecification.and().body(orderData).post("/api/v1/orders");
    }

    @Step("Send POST request to /api/v1/orders and get order number")
    public Integer getOrderNumber() {
        Response response = requestSpecification.and().body(orderData).post("/api/v1/orders");
        Integer orderId = response.then().extract().body().path("track");
        return orderId;
    }

    @Step("Send PUT request to /api/v1/orders/cancel for order cancellation")
    public static void orderCancellation(Integer orderId) {
        requestSpecification.put("/api/v1/orders/cancel{orderId}", orderId);
    }
}
