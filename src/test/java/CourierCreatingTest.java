import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import requestsMethods.CourierCreatingRequests;
import responseMethods.CourierCreatingResponse;

import static requestsMethods.CourierCreatingRequests.*;
import static requestsMethods.CourierLoginRequests.getCourierId;
import static responseMethods.CourierCreatingResponse.*;

public class CourierCreatingTest {

    @Before
    public void before() {
        CourierCreatingRequests courierRequests = new CourierCreatingRequests();
    }

    @Test
    @DisplayName("Successfully creating a courier with all parameters")
    @Description("201 Created for /api/v1/courier")
    public void checkSuccessfulCreatingCourier() {
        Response response = CourierCreatingRequests.sendPostRequestAllParameters();
        System.out.println(response.asString());
        CourierCreatingResponse.checkSuccessfulResponse(response);
    }

    @Test
    @DisplayName("Request with duplicate login")
    @Description("Error 409 Conflict when creating two identical couriers for /api/v1/courier")
    public void checkError409ForDuplicateLogin() {
        sendPostRequestAllParameters();
        Response secondResponse = sendPostRequestAllParameters();
        check409ErrorResponse(secondResponse);
    }

    @Test
    @DisplayName("Request without login")
    @Description("Error 400 Bad Request for /api/v1/courier without login")
    public void checkError400WithoutLogin() {
        Response response = sendPostRequestWithoutLogin();
        check400ErrorResponse(response);
    }

    @Test
    @DisplayName("Request without password")
    @Description("Error 400 Bad Request for /api/v1/courier without password")
    public void checkError400WithoutPassword() {
        Response response = sendPostRequestWithoutPassword();
        check400ErrorResponse(response);
    }

    @After
    public void after() {
        Integer courierId = getCourierId();
        if (courierId != null) {
            deleteCourier(courierId);
        }
    }

}
