import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import requestsMethods.BaseApi;

import static requestsMethods.GetOrdersRequests.sendGetRequestLimitTenOrders;
import static responseMethods.GetOrdersResponse.checkCode200Response;
import static responseMethods.GetOrdersResponse.checkListOfOrders;

public class ListOfOrdersTest {

    @Before
    public void before() {
        new BaseApi();
    }

    @Test
    @DisplayName("Get list of 10 orders")
    @Description("Code 200 for /api/v1/orders?limit=10&page=0")
    public void getListOfTenAvailableOrders() {
        Response response = sendGetRequestLimitTenOrders();
        checkCode200Response(response);
        checkListOfOrders(response);
    }
}
