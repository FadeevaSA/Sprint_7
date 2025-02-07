import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListOfOrders {
    private List<Order> orders;

    public ListOfOrders(List<Order> orders) {
        this.orders = orders;
    }

    public ListOfOrders() {
    }

    @Step("Send Get request to /api/v1/orders?limit=10&page=0 with limit 10 orders")
    public static Response sendGetRequestLimitTenOrders() {
        Response response = given().header("Content-type", "application/json").get("/api/v1/orders?limit=10&page=0");
        return response;
    }

    @Step("Compare response code 200")
    public static void checkCode200Response(Response response) {
        response.then().statusCode(200);
    }

    @Step("Check list of orders")
    public static void checkListOfOrders(Response response) {
        ListOfOrders listOfOrders = response.as(ListOfOrders.class);
        for (Order order : listOfOrders.getOrders()) {
            System.out.println("Номер заказа: " + order.getId());
            System.out.println("Клиент: " + order.getFirstName() + " " + order.getLastName());
            System.out.println("Адрес: " + order.getAddress());
            System.out.println("Номер телефона: " + order.getPhone());
            System.out.println("Дата доставки: " + order.getDeliveryDate());
            System.out.println("   ");
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
