package responseMethods;

import pojo.ListOfOrdersData;
import pojo.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class GetOrdersResponse {
    @Step("Compare response code 200")
    public static void checkCode200Response(Response response) {
        response.then().statusCode(200);
    }

    @Step("Check list of orders")
    public static void checkListOfOrders(Response response) {
        ListOfOrdersData listOfOrders = response.as(ListOfOrdersData.class);
        for (Order order : listOfOrders.getOrders()) {
            System.out.println("Номер заказа: " + order.getId());
            System.out.println("Клиент: " + order.getFirstName() + " " + order.getLastName());
            System.out.println("Адрес: " + order.getAddress());
            System.out.println("Номер телефона: " + order.getPhone());
            System.out.println("Дата доставки: " + order.getDeliveryDate());
            System.out.println("   ");
        }
    }
}
