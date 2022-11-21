package guru.qa.test;

import guru.qa.data.Data;
import guru.qa.modals.newUser.NewUserBody;
import guru.qa.modals.newUser.NewUserResponse;
import guru.qa.modals.register.RegisterBody;
import guru.qa.modals.register.RegisterResponse;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static guru.qa.specs.RequestSpecs.allureAndUri;
import static guru.qa.specs.RequestSpecs.allureAndUriWithJson;
import static guru.qa.specs.ResponseSpecs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@Tag("All")
public class RestAPITest {

    Data data = ConfigFactory.create(Data.class, System.getProperties());

    @Tag("Id")
    @DisplayName("Проверка фамилии пользователя по id")
    @Test
    void successGetUser() {
        step("Проверка фамилии пользователя по id", () ->
                given()
                        .spec(allureAndUri)
                        .when()
                        .get("users/" + data.getId())
                        .then()
                        .spec(getLogs200)
                        .body("data.last_name", is(data.getIdName())));
    }

    @Tag("Error")
    @DisplayName("Проверка о 404 ошибке")
    @Test
    void checkSingleUser404Error() {
        step("Выводит 404 ошибку", () ->
                given()
                        .spec(allureAndUri)
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
        newUser.setName(data.getNewUserName());
        newUser.setJob(data.getNewUserJob());

        AtomicReference<NewUserResponse> response = new AtomicReference<>();

        step("Создание нового пользователя", () ->
                response.set(given()
                        .spec(allureAndUriWithJson)
                        .body(newUser)
                        .when()
                        .post("users")
                        .then()
                        .spec(getLogs201)
                        .extract()
                        .as(NewUserResponse.class)));

        step("Проверка корректности созданного пользователя", () ->
                assertThat(response.get().getName()).isEqualTo(data.getNewUserName()));
    }

    @Tag("Delete")
    @DisplayName("Удаление пользователя")
    @Test
    void deleteUserTest() {
        step("Удаление пользователя", () ->
                given()
                        .spec(allureAndUri)
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
        register.setEmail(data.getRegEmail());
        register.setPassword(data.getRegPassword());

        AtomicReference<RegisterResponse> response = new AtomicReference<>();

        step("Регестрация пользователя", () ->
                response.set(given()
                        .spec(allureAndUriWithJson)
                        .body(register)
                        .when()
                        .post("register")
                        .then()
                        .spec(getLogs200)
                        .extract()
                        .as(RegisterResponse.class)));

        step("Проверка корректного вывода", () ->
                assertThat(response.get().getToken()).isEqualTo(data.getRegToken()));
    }

    @Tag("Pantone")
    @DisplayName("Проверка существования пантон-цвета")
    @Test
    void pantoneTest() {
        String groovyRequest = "data.findAll{it.pantone_value =~/^%s/}.pantone_value.flatten()";

        step("Проверка существования пантон-цвета", () ->
                given()
                        .spec(allureAndUri)
                        .when()
                        .get("/unknown")
                        .then()
                        .spec(getLogs200)
                        .body(format(groovyRequest, data.getPantone().substring(0, 3)), hasItem(data.getPantone())));
    }
}
