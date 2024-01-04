package backend;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Books {
    private final String BASE_URL = "http://localhost:8080/api/v1/books";

    @Test
    @Order(1)
    public void getBooks() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data", isA(List.class));
    }

    @Test
    @Order(2)
    public void postBook() {
        JSONObject payload = new JSONObject();
        payload.put("title", "Test rest-assured");
        payload.put("author", "rest-assured");
        payload.put("genre", "IT");
        payload.put("image_url", "http://image-test.jpg");
        payload.put("quantity", 10);
        payload.put("price", 100);

        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .when()
                .post()
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("title", equalTo("Test rest-assured"))
                .body("author", equalTo("rest-assured"))
                .body("genre", equalTo("IT"))
                .body("image_url", equalTo("http://image-test.jpg"))
                .body("quantity", equalTo(10))
                .body("price", equalTo(100));
    }

    @Test
    @Order(3)
    public void sortByPrice() {
        Response response = given()
                .baseUri(BASE_URL)
                .param("sort", "price")
                .when()
                .get();

        JSONObject reponseBody = new JSONObject(response.body().asString());
        System.out.println(reponseBody.getJSONArray("data").getJSONObject(0).get("price").toString());
        BigDecimal priceFirstBook = new BigDecimal(reponseBody.getJSONArray("data").getJSONObject(0).get("price").toString());
        BigDecimal priceSecondBook = new BigDecimal(reponseBody.getJSONArray("data").getJSONObject(1).get("price").toString());


        assertEquals(200, response.getStatusCode());
        assertTrue(priceFirstBook.compareTo(priceSecondBook) <= 0);
    }

    @Test
    @Order(4)
    public void searchByTitle() {
        given()
                .baseUri(BASE_URL)
                .basePath("/search")
                .param("title", "Test rest-assured")
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data", isA(List.class))
                .body("data[0].title", equalTo("Test rest-assured"))
                .body("data[0].author", equalTo("rest-assured"))
                .body("data[0].genre", equalTo("IT"))
                .body("data[0].image_url", equalTo("http://image-test.jpg"))
                .body("data[0].quantity", equalTo(10))
                .body("data[0].price", equalTo(100.0F));
    }

    @Test
    @Order(5)
    public void updateBook() {
        Response getResponse = given()
                .baseUri(BASE_URL)
                .basePath("/search")
                .param("title", "Test rest-assured")
                .when()
                .get();

        JSONObject getReponseBody = new JSONObject(getResponse.body().asString());
        long id = getReponseBody.getJSONArray("data").getJSONObject(0).getLong("id");

        JSONObject payload = new JSONObject();
        payload.put("title", "Test rest-assured updated");
        payload.put("author", "rest-assured updated");
        payload.put("genre", "IT up");
        payload.put("image_url", "http://image-test-updated.jpg");
        payload.put("quantity", 20);
        payload.put("price", 2500);

        given()
                .baseUri(BASE_URL)
                .basePath(String.valueOf(id))
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .when()
                .put()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("Test rest-assured updated"))
                .body("author", equalTo("rest-assured updated"))
                .body("genre", equalTo("IT up"))
                .body("image_url", equalTo("http://image-test-updated.jpg"))
                .body("quantity", equalTo(20))
                .body("price", equalTo(2500));
    }

    @Test
    @Order(6)
    public void deleteBook() {
        Response getResponse = given()
                .baseUri(BASE_URL)
                .basePath("/search")
                .param("title", "Test rest-assured updated")
                .when()
                .get();

        JSONObject getReponseBody = new JSONObject(getResponse.body().asString());
        long id = getReponseBody.getJSONArray("data").getJSONObject(0).getLong("id");

        given()
                .baseUri(BASE_URL)
                .basePath(String.valueOf(id))
                .when()
                .delete()
                .then()
                .statusCode(204);
    }
}
