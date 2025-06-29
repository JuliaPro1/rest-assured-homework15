import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;


public class RegresInTests extends TestBase{

    @Test
    @DisplayName("Проверка списка юзеров")
    void checkListUsersTest(){
        given()
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()
                .queryParam("page", "2")
                .get("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.size()", equalTo(6))
                .body("data.id", containsInAnyOrder(7, 8, 9, 10, 11, 12));
    }

    @Test
    @DisplayName("Создание нового юзера")
    void checkCreateNewUserTest(){
        String newUser = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .header("x-api-key", "reqres-free-v1")
                .body(newUser)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    @DisplayName("Изменение юзера PUT")
    void checkUpdateUserPutTest(){
        String updateUserPut = "{\"name\": \"mikel\", \"job\": \"zion resident\"}";

        given()
                .header("x-api-key", "reqres-free-v1")
                .body(updateUserPut)
                .contentType(JSON)
                .log().uri()
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("mikel"))
                .body("job", is("zion resident"));
    }

    @Test
    @DisplayName("Изменение юзера PATCH")
    void checkUpdateUserPatchTest(){
        String updateUserPut = "{\"name\": \"mia\", \"job\": \"resident\"}";

        given()
                .header("x-api-key", "reqres-free-v1")
                .body(updateUserPut)
                .contentType(JSON)
                .log().uri()
                .when()
                .patch("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("mia"))
                .body("job", is("resident"));
    }

    @Test
    @DisplayName("Удаление юзера")
    void checkDeleteUserTest(){

        given()
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
    }

