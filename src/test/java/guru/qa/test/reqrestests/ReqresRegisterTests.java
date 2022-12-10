package guru.qa.test.reqrestests;

import guru.qa.config.Config;
import guru.qa.models.register.RegisterBody;
import guru.qa.models.register.RegisterResponse;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.specs.RequestSpecs.allureAndUriWithJson;
import static guru.qa.specs.ResponseSpecs.getLogs200;
import static guru.qa.specs.ResponseSpecs.getLogs204;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("All")
@Tag("Register")
@DisplayName("API тесты для Reqres")
public class ReqresRegisterTests {

    Config config = ConfigFactory.create(Config.class, System.getProperties());

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
}
