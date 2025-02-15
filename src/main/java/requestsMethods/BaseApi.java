package requestsMethods;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApi {
    protected static RequestSpecification requestSpecification;

    public BaseApi() {
        setUp();
    }

    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        requestSpecification = RestAssured.given().header("Content-Type", "application/json");

    }
}