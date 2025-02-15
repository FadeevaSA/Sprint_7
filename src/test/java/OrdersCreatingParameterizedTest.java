import pojo.OrdersCreatingData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requestsMethods.OrdersCreatingRequests;

import java.util.Arrays;
import java.util.List;

import static requestsMethods.OrdersCreatingRequests.orderCancellation;
import static responseMethods.OrdersCreatingResponse.check201CreatedResponse;

@RunWith(Parameterized.class)
public class OrdersCreatingParameterizedTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> color;

    private OrdersCreatingRequests ordersCreatingRequests;

    public OrdersCreatingParameterizedTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters(name = "Тест {index}")
    public static Object[][] getOrderDetails() {
        return new Object[][]{
                {"Петя", "Петров", "Молодежная 4", 5, "+7 800 355 35 35", 2, "2025-06-06", "хочу кататься", Arrays.asList("BLACK")},
                {"Маша", "Иванова", "Луговая 11", 2, "+7 800 355 66 99", 6, "2025-05-26", "вжух", Arrays.asList("GREY")},
                {"Антон", "Гусак", "Левкова 222", 8, "+7 800 355 96 89", 1, "2025-03-20", "Только красивый", Arrays.asList("GREY", "BLACK")},
                {"Настя", "Ященко", "Московская 4-3", 20, "+7 800 355 66 00", 3, "2025-11-13", "Любой цвет", null}
        };
    }

    @Before
    public void before() {
        OrdersCreatingData orderData = new OrdersCreatingData(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ordersCreatingRequests = new OrdersCreatingRequests(orderData);
    }

    @Test
    @DisplayName("Successful order of a scooter with different data")
    @Description("201 Created for /api/v1/orders with different data")
    public void successfulOrderCreation() {
        Response response = ordersCreatingRequests.sendPostRequestForCreatingOrders();
        check201CreatedResponse(response);
    }

    @After
    public void after() {
        Integer orderId = ordersCreatingRequests.getOrderNumber();
        if (orderId != null) {
            orderCancellation(orderId);
        }
    }
}
