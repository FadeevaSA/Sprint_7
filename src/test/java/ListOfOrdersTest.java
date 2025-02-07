import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

public class ListOfOrdersTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Get list of 10 orders")
    @Description("Code 200 for /api/v1/orders?limit=10&page=0")
    public void getListOfTenAvailableOrders() {
        Response response = ListOfOrders.sendGetRequestLimitTenOrders();
        ListOfOrders.checkCode200Response(response);
        ListOfOrders.checkListOfOrders(response);
    }
}
