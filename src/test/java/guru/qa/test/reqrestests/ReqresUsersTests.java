package guru.qa.test.reqrestests;

import guru.qa.config.Config;
import guru.qa.models.newuser.NewUserBody;
import guru.qa.models.newuser.NewUserResponse;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.specs.RequestSpecs.allureAndUriWithJson;
import static guru.qa.specs.ResponseSpecs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@Tag("All") @Tag("Users")
@DisplayName("API тесты для Reqres")
public class ReqresUsersTests {

    Config config = ConfigFactory.create(Config.class, System.getProperties());

    @Tag("Id")
    @DisplayName("Проверка фамилии пользователя по id")
    @Test
    void successGetUser() {
        step("Проверка фамилии пользователя по id", () ->
                given()
                        .spec(allureAndUriWithJson)
                        .when()
                        .get("users/" + config.getId())
                        .then()
                        .spec(getLogs200)
                        .body("data.last_name", is(config.getIdName())));
    }

    @Tag("Error")
    @DisplayName("Проверка о 404 ошибке")
    @Test
    void checkSingleUser404Error() {
        step("Выводит 404 ошибку", () ->
                given()
                        .spec(allureAndUriWithJson)
                        .when()
                        .get("users/404")
                        .then()
                        .spec(getLogs404));
    }

    @Tag("NewUser")
    @DisplayName("Создание нового пользователя и проверка его имени")
    @Test
    void createUserTest() {

        NewUserBody newUser = new NewUserBody();
        newUser.setName(config.getNewUserName());
        newUser.setJob(config.getNewUserJob());

        NewUserResponse response = step("Создание нового пользователя", () ->
                given()
                        .spec(allureAndUriWithJson)
                        .body(newUser)
                        .when()
                        .post("users")
                        .then()
                        .spec(getLogs201)
                        .extract()
                        .as(NewUserResponse.class));

        step("Проверка корректности созданного пользователя", () ->
                assertThat(response.getName()).isEqualTo(config.getNewUserName()));
    }
}
