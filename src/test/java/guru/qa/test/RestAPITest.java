package guru.qa.test;

import guru.qa.config.Config;
import guru.qa.models.newuser.NewUserBody;
import guru.qa.models.newuser.NewUserResponse;
import guru.qa.models.register.RegisterBody;
import guru.qa.models.register.RegisterResponse;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.specs.RequestSpecs.allureAndUriWithJson;
import static guru.qa.specs.ResponseSpecs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@Tag("All")
@DisplayName("API тесты для Reqres")
public class RestAPITest {

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

    @Tag("Delete")
    @DisplayName("Удаление пользователя")
    @Test
    void deleteUserTest() {
        step("Удаление пользователя", () ->
                given()
                        .spec(allureAndUriWithJson)
                        .when()
                        .delete("register")
                        .then()
                        .spec(getLogs204));
    }

    @Tag("Registration")
    @DisplayName("Тест на регистрацию")
    @Test
    void registryTest() {
        RegisterBody register = new RegisterBody();
        register.setEmail(config.getRegEmail());
        register.setPassword(config.getRegPassword());

        RegisterResponse response = step("Регестрация пользователя", () ->
                given()
                        .spec(allureAndUriWithJson)
                        .body(register)
                        .when()
                        .post("register")
                        .then()
                        .spec(getLogs200)
                        .extract()
                        .as(RegisterResponse.class));

        step("Проверка корректного вывода", () ->
                assertThat(response.getToken()).isEqualTo(config.getRegToken()));
    }

    @Tag("Pantone")
    @DisplayName("Проверка существования пантон-цвета")
    @Test
    void pantoneTest() {
        String groovyRequest = "data.findAll{it.pantone_value =~/^%s/}.pantone_value.flatten()";

        step("Проверка существования пантон-цвета", () ->
                given()
                        .spec(allureAndUriWithJson)
                        .when()
                        .get("/unknown")
                        .then()
                        .spec(getLogs200)
                        .body(format(groovyRequest, config.getPantone().substring(0, 3)), hasItem(config.getPantone())));
    }
}
