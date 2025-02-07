import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreating {

    private String login;
    private String password;
    private String firstName;

    public CourierCreating(String password, String firstName) {
        this.password = password;
        this.firstName = firstName;
    }

    public CourierCreating(String login) {
        this.login = login;
    }

    public CourierCreating(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CourierCreating() {
    }

    @Step("Send POST request to /api/v1/courier with all parameters")
    public static Response sendPostRequestAllParameters() {
        CourierCreating courier = new CourierCreating("Statham", "1234", "Джейсон");
        Response response = given().header("Content-type", "application/json").and().body(courier).when().post("/api/v1/courier");
        return response;
    }

    @Step("Send POST request to /api/v1/courier without login")
    public static Response sendPostRequestWithoutLogin() {
        CourierCreating courier = new CourierCreating("1234", "Джейсон");
        Response response = given().header("Content-type", "application/json").and().body(courier).when().post("/api/v1/courier");
        return response;
    }

    @Step("Send POST request to /api/v1/courier without password")
    public static Response sendPostRequestWithoutPassword() {
        CourierCreating courier = new CourierCreating("Statham");
        Response response = given().header("Content-type", "application/json").and().body(courier).when().post("/api/v1/courier");
        return response;
    }

    @Step("Compare response text and response code 201")
    public static void checkSuccessfulResponse(Response response) {
        response.then().assertThat().body("ok", equalTo(true)).and().statusCode(201);
    }

    @Step("Compare response text and response code 409")
    public static void check409ErrorResponse(Response response) {
        response.then().assertThat().body("message", equalTo("Этот логин уже используется")).and().statusCode(409);
    }

    @Step("Compare response text and response code 400")
    public static void check400ErrorResponse(Response response) {
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи")).and().statusCode(400);
    }

    @Step("Send DELETE request to /api/v1/courier/:id")
    public static void deleteCourier(Integer courierId) {
        given().header("Content-type", "application/json").delete("/api/v1/courier/{courierId}", courierId);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
