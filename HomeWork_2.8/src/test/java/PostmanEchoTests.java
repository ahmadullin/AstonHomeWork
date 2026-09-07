import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTests {

    private static final String BASE_URL = "https://postman-echo.com";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    /**
     * Тест 1. GET запрос
     * Проверяет GET запрос с параметрами foo1=bar1 и foo2=bar2
     */
    @Test
    public void testGetRequest() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("url", containsString("postman-echo.com/get"))
                .body("headers.host", equalTo("postman-echo.com"));
    }

    /**
     * Тест 2. POST запрос с обычным текстом
     * Проверяет отправку текстового тела в POST запросе
     */
    @Test
    public void testPostRawText() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("json", nullValue());
    }

    /**
     * Тест 3. POST запрос с Form Data
     * Проверяет отправку данных в формате application/x-www-form-urlencoded
     */
    @Test
    public void testPostFormData() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .body("foo1=bar1&foo2=bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }

    /**
     * Тест 4. PUT запрос
     * Проверяет отправку данных методом PUT
     */
    @Test
    public void testPutRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

    /**
     * Тест 5. PATCH запрос
     * Проверяет отправку данных методом PATCH
     */
    @Test
    public void testPatchRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

    /**
     * Тест 6. DELETE запрос
     * Проверяет отправку данных методом DELETE
     */
    @Test
    public void testDeleteRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

    /**
     * Тест 7. Заголовки запроса
     * Проверяет, что отправленный заголовок возвращается в ответе
     */
    @Test
    public void testRequestHeaders() {
        given()
                .header("my-sample-header", "Lorem ipsum dolor sit amet")
                .when()
                .get("/headers")
                .then()
                .statusCode(200)
                .body("headers.my-sample-header", equalTo("Lorem ipsum dolor sit amet"));
    }

    /**
     * Тест 8. Заголовки ответа
     * Проверяет, что сервер возвращает заголовки, переданные в параметрах
     */
    @Test
    public void testResponseHeaders() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/response-headers")
                .then()
                .statusCode(200)
                .header("foo1", equalTo("bar1"))
                .header("foo2", equalTo("bar2"))
                .body("foo1", equalTo("bar1"))
                .body("foo2", equalTo("bar2"));
    }

    /**
     * Тест 9. Код состояния ответа
     * Проверяет, что сервер возвращает код 200
     */
    @Test
    public void testStatus200() {
        given()
                .when()
                .get("/status/200")
                .then()
                .statusCode(200)
                .body("status", equalTo(200));
    }

    /**
     * Тест 10. Задержка ответа
     * Проверяет, что сервер задерживает ответ на указанное время
     */
    @Test
    public void testDelayResponse() {
        given()
                .when()
                .get("/delay/2")
                .then()
                .statusCode(200)
                .body("delay", equalTo("2"));
    }

    /**
     * Тест 11. IP адрес
     * Проверяет, что сервер возвращает IP адрес клиента
     */
    @Test
    public void testGetIP() {
        given()
                .when()
                .get("/ip")
                .then()
                .statusCode(200)
                .body("ip", notNullValue());
    }

    /**
     * Тест 12. Текущее время
     * Проверяет, что сервер возвращает текущее время
     */
    @Test
    public void testCurrentTime() {
        given()
                .when()
                .get("/time/now")
                .then()
                .statusCode(200)
                .body(not(emptyString()));
    }

    /**
     * Тест 13. GZip сжатие
     * Проверяет, что сервер возвращает ответ в сжатом формате GZip
     */
    @Test
    public void testGzipResponse() {
        given()
                .header("Accept-Encoding", "gzip, deflate")
                .when()
                .get("/gzip")
                .then()
                .statusCode(200)
                .header("content-encoding", containsString("gzip"))
                .body("gzipped", equalTo(true));
    }

    /**
     * Тест 14. Deflate сжатие
     * Проверяет, что сервер возвращает ответ в сжатом формате Deflate
     */
    @Test
    public void testDeflateResponse() {
        given()
                .header("Accept-Encoding", "gzip, deflate")
                .when()
                .get("/deflate")
                .then()
                .statusCode(200)
                .header("content-encoding", anyOf(containsString("gzip"), containsString("deflate")))
                .body("deflated", equalTo(true));
    }

    /**
     * Тест 15. UTF8 кодировка
     * Проверяет, что сервер возвращает ответ в кодировке UTF-8
     */
    @Test
    public void testUtf8Response() {
        given()
                .when()
                .get("/encoding/utf8")
                .then()
                .statusCode(200)
                .header("content-type", containsString("charset=utf-8"));
    }

    /**
     * Тест 16. Потоковый ответ
     * Проверяет, что сервер возвращает ответ с chunked transfer encoding
     */
    @Test
    public void testStreamedResponse() {
        given()
                .when()
                .get("/stream/5")
                .then()
                .statusCode(200)
                .header("transfer-encoding", containsString("chunked"));
    }

    /**
     * Тест 17. Basic Auth успешная аутентификация
     * Проверяет успешную аутентификацию с правильными логином и паролем
     */
    @Test
    public void testBasicAuthSuccess() {
        given()
                .auth().basic("postman", "password")
                .when()
                .get("/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true));
    }

    /**
     * Тест 18. Basic Auth неудачная аутентификация
     * Проверяет, что сервер возвращает 401 при неверном пароле
     */
    @Test
    public void testBasicAuthFailure() {
        given()
                .auth().basic("postman", "wrong-password")
                .when()
                .get("/basic-auth")
                .then()
                .statusCode(401);
    }

    /**
     * Тест 19. Проверка валидности временной метки
     * Проверяет, что сервер корректно определяет валидность даты
     */
    @Test
    public void testTimestampValidity() {
        given()
                .queryParam("timestamp", "2016-10-10")
                .when()
                .get("/time/valid")
                .then()
                .statusCode(200)
                .body("valid", equalTo(true));
    }

    /**
     * Тест 20. Форматирование временной метки
     * Проверяет, что сервер возвращает дату в указанном формате
     */
    @Test
    public void testFormatTimestamp() {
        given()
                .queryParam("timestamp", "2016-10-10")
                .queryParam("format", "YYYY")
                .when()
                .get("/time/format")
                .then()
                .statusCode(200)
                .body("format", notNullValue());
    }
}