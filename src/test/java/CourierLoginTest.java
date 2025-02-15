import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requestsMethods.BaseApi;

import static requestsMethods.CourierCreatingRequests.deleteCourier;
import static requestsMethods.CourierCreatingRequests.sendPostRequestAllParameters;
import static requestsMethods.CourierLoginRequests.*;
import static responseMethods.CourierLoginResponse.*;

public class CourierLoginTest {

    @Before
    public void before() {
        new BaseApi();
        sendPostRequestAllParameters();
    }

    @Test
    @DisplayName("Successful courier authorization")
    @Description("Code 200  for /api/v1/courier/login with all parameters")
    public void checkSuccessfulCourierAuthorization() {
        Response response = checkCourierAuthorization();
        check200SuccessResponse(response);
    }

    @Test
    @DisplayName("Unsuccessful courier authorization without login")
    @Description("400 Bad Request for /api/v1/courier/login without login")
    public void checkUnsuccessfulAuthorizationWithoutLogin() {
        Response response = checkPostRequestWithoutLogin();
        check400ErrorResponse(response);
    }

    @Test
    @DisplayName("Unsuccessful courier authorization without password")
    @Description("400 Bad Request for /api/v1/courier/login without password")
    public void checkUnsuccessfulAuthorizationWithoutPassword() {
        Response response = checkPostRequestWithoutPassword();
        check400ErrorResponse(response);
    }

    @Test
    @DisplayName("Unsuccessful courier authorization with incorrect password")
    @Description("404 Bad Request for /api/v1/courier/login with incorrect password")
    public void checkUnsuccessfulAuthorizationWithIncorrectPassword() {
        Response response = checkAuthorizationWithIncorrectPassword();
        check404ErrorResponse(response);
    }

    @Test
    @DisplayName("Unsuccessful courier authorization with incorrect login")
    @Description("404 Bad Request for /api/v1/courier/login with incorrect login")
    public void checkUnsuccessfulAuthorizationWithIncorrectLogin() {
        Response response = checkAuthorizationWithIncorrectLogin();
        check404ErrorResponse(response);
    }

    @After
    public void after() {
        Integer courierId = getCourierId();
        if (courierId != null) {
            deleteCourier(courierId);
        }
    }
}
