import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

public class CourierCreatingTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Successfully creating a courier with all parameters")
    @Description("201 Created for /api/v1/courier")
    public void checkSuccessfulCreatingCourier() {
        Response response = CourierCreating.sendPostRequestAllParameters();
        CourierCreating.checkSuccessfulResponse(response);
    }

    @Test
    @DisplayName("Request with duplicate login")
    @Description("Error 409 Conflict when creating two identical couriers for /api/v1/courier")
    public void checkError409ForDuplicateLogin() {
        Response response = CourierCreating.sendPostRequestAllParameters();
        Response secondResponse = CourierCreating.sendPostRequestAllParameters();
        CourierCreating.check409ErrorResponse(secondResponse);
    }

    @Test
    @DisplayName("Request without login")
    @Description("Error 400 Bad Request for /api/v1/courier without login")
    public void checkError400WithoutLogin() {
        Response response = CourierCreating.sendPostRequestWithoutLogin();
        CourierCreating.check400ErrorResponse(response);
    }

    @Test
    @DisplayName("Request without password")
    @Description("Error 400 Bad Request for /api/v1/courier without password")
    public void checkError400WithoutPassword() {
        Response response = CourierCreating.sendPostRequestWithoutPassword();
        CourierCreating.check400ErrorResponse(response);
    }

    @After
    public void after() {
        Integer courierId = CourierLogin.getCourierId();
        if (courierId != null) {
            CourierCreating.deleteCourier(courierId);
        }
    }

}
