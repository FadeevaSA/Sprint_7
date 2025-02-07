import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourierLoginTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        Response response = CourierCreating.sendPostRequestAllParameters();
    }

    @Test
    @DisplayName("Successful courier authorization")
    @Description("Code 200  for /api/v1/courier/login with all parameters")
    public void checkSuccessfulCourierAuthorization() {
        Response response = CourierLogin.checkCourierAuthorization();
        CourierLogin.check200SuccessResponse(response);
    }

    @Test
    @DisplayName("Unsuccessful courier authorization without login")
    @Description("400 Bad Request for /api/v1/courier/login without login")
    public void checkUnsuccessfulAuthorizationWithoutLogin() {
        Response response = CourierLogin.sendPostRequestWithoutLogin();
        CourierLogin.check400ErrorResponse(response);
    }

    @Test
    @DisplayName("Unsuccessful courier authorization without password")
    @Description("400 Bad Request for /api/v1/courier/login without password")
    public void checkUnsuccessfulAuthorizationWithoutPassword() {
        Response response = CourierLogin.sendPostRequestWithoutPassword();
        CourierLogin.check400ErrorResponse(response);
    }

    @Test
    @DisplayName("Unsuccessful courier authorization with incorrect password")
    @Description("404 Bad Request for /api/v1/courier/login with incorrect password")
    public void checkUnsuccessfulAuthorizationWithIncorrectPassword() {
        Response response = CourierLogin.checkAuthorizationWithIncorrectPassword();
        CourierLogin.check404ErrorResponse(response);
    }

    @Test
    @DisplayName("Unsuccessful courier authorization with incorrect login")
    @Description("404 Bad Request for /api/v1/courier/login with incorrect login")
    public void checkUnsuccessfulAuthorizationWithIncorrectLogin() {
        Response response = CourierLogin.checkAuthorizationWithIncorrectLogin();
        CourierLogin.check404ErrorResponse(response);
    }

    @After
    public void after() {
        Integer courierId = CourierLogin.getCourierId();
        if (courierId != null) {
            CourierCreating.deleteCourier(courierId);
        }
    }
}
