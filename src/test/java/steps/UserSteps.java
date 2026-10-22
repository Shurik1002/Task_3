package steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import pojo.User;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserSteps {


    @Step("Сравниваю текст и код ответа после удаления пользователя")
    public void checkDeleteResponse(Response response) {

        response.then()
                .statusCode(202)
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"));

    }

    @Step("Создаю уникальный логин")
    public String createEmail() {
        return UUID.randomUUID().toString().substring(0, 8) + "@yandex.ru";
    }

    @Step("Создаю уникальный пароль")
    public String createPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    @Step("Создаю пользователя")
    public Response userRegister(User user) {
        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .header("Content-type", "application/json")
                        .body(user)
                        .when()
                        .post("/api/auth/register");
        return response;

    }

    @Step("Авторизуюсь")
    public Response userLogin(String email, String password) {
        Response response =
                given()
                        .filter(new AllureRestAssured())
                        .header("Content-type", "application/json")
                        .body(new User(email, password))
                        .when()
                        .post("/api/auth/login");
        return response;
    }

    @Step("Получаю токен")
    public String getToken(Response response) {
        String token = response.jsonPath().getString("accessToken");
        return token;
    }

    @Step("Удаляю пользователя")
    public Response deleteUser(String accessToken) {
        return given()
                .filter(new AllureRestAssured())
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }

    @Step("Удаляю созданного пользователя")
    public void deleteTestUser(String email, String password) {

        Response loginResponse = userLogin(email, password);
        String accessToken = getToken(loginResponse);
        Response deleteResponse = deleteUser(accessToken);
        checkDeleteResponse(deleteResponse);


    }

}
