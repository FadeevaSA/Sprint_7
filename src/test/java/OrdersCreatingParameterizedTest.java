import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

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
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Successful order of a scooter with different data")
    @Description("201 Created for /api/v1/orders with different data")
    public void successfulOrderCreation() {
        Response response = sendPostRequestForCreatingOrders();
        check201CreatedResponse(response);
    }

    @After
    public void after() {
        Integer orderId = getOrderNumber();
        if (orderId != null) {
            orderCancellation(orderId);
        }
    }

    @Step("Send POST request to /api/v1/orders for creating orders")
    public Response sendPostRequestForCreatingOrders() {
        OrdersCreating order = new OrdersCreating(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = given().header("Content-type", "application/json").and().body(order).post("/api/v1/orders");
        return response;
    }

    @Step("Compare response text and response code 201")
    public void check201CreatedResponse(Response response) {
        response.then().assertThat().body("track", notNullValue()).statusCode(201);
    }

    @Step("Send POST request to /api/v1/orders and get order number")
    public Integer getOrderNumber() {
        OrdersCreating order = new OrdersCreating(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = given().header("Content-type", "application/json").and().body(order).post("/api/v1/orders");
        Integer orderId = response.then().extract().body().path("track");
        return orderId;
    }

    @Step("Send PUT request to /api/v1/orders/cancel for order cancellation")
    public static void orderCancellation(Integer orderId) {
        given().header("Content-type", "application/json").put("/api/v1/orders/cancel{orderId}", orderId);
    }
}
