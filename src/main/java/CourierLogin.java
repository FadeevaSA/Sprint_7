import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class CourierLogin {
    private String login;
    private String password;


    public CourierLogin() {
    }

    public CourierLogin(String password) {
        this.password = password;
    }

    public CourierLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Step("Send POST request to /api/v1/courier/login and get courierId")
    public static Integer getCourierId() {
        CourierLogin courier = new CourierLogin("Statham", "1234");
        Response id = given().header("Content-type", "application/json").and().body(courier).post("/api/v1/courier/login");
        Integer courierId = id.then().extract().body().path("id");
        return courierId;
    }

    @Step("Send POST request to /api/v1/courier/login with correct password and login")
    public static Response checkCourierAuthorization() {
        CourierLogin courier = new CourierLogin("Statham", "1234");
        Response response = given().header("Content-type", "application/json").and().body(courier).post("/api/v1/courier/login");
        return response;
    }

    @Step("Send POST request to /api/v1/courier/login with incorrect password")
    public static Response checkAuthorizationWithIncorrectPassword() {
        CourierLogin courier = new CourierLogin("Statham", "1235");
        Response response = given().header("Content-type", "application/json").and().body(courier).post("/api/v1/courier/login");
        return response;
    }

    @Step("Send POST request to /api/v1/courier/login with incorrect login")
    public static Response checkAuthorizationWithIncorrectLogin() {
        CourierLogin courier = new CourierLogin("tatham", "1234");
        Response response = given().header("Content-type", "application/json").and().body(courier).post("/api/v1/courier/login");
        return response;
    }

    @Step("Send POST request to /api/v1/courier/login without login")
    public static Response sendPostRequestWithoutLogin() {
        CourierLogin courier = new CourierLogin("1234");
        Response response = given().header("Content-type", "application/json").and().body(courier).when().post("/api/v1/courier/login");
        return response;
    }

    @Step("Send POST request to /api/v1/courier/login without password")
    public static Response sendPostRequestWithoutPassword() {
        CourierCreating courier = new CourierCreating("Statham");
        Response response = given().header("Content-type", "application/json").and().body(courier).post("/api/v1/courier/login");
        return response;
    }

    @Step("Compare response text and response code 400")
    public static void check400ErrorResponse(Response response) {
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа")).and().statusCode(400);
    }

    @Step("Compare response text and response code 404 Not Found")
    public static void check404ErrorResponse(Response response) {
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена")).and().statusCode(404);
    }

    @Step("Compare response text and response code 200")
    public static void check200SuccessResponse(Response response) {
        response.then().assertThat().body("id", greaterThan(0)).and().statusCode(200);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
