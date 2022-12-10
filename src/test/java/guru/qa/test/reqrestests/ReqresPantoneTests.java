package guru.qa.test.reqrestests;

import guru.qa.config.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.specs.RequestSpecs.allureAndUriWithJson;
import static guru.qa.specs.ResponseSpecs.getLogs200;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.Matchers.hasItem;

@Tag("All") @Tag("Pantone")
@DisplayName("API тесты для Reqres")
public class ReqresPantoneTests {

    Config config = ConfigFactory.create(Config.class, System.getProperties());

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
