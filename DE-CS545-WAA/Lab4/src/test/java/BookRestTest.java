import backend_design.lab4.book.domain.Book;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
public class BookRestTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/books";
    }
    @Test
    public void testGetOneBook() {
        Book book = new Book("123", "Rene", "Test Rest", 100);
        // add new book
        given()
                .contentType("application/json")
                .body(book)
                .when().post().then()
                .statusCode(200);
        // test getting the book
        Book res = given()
                .relaxedHTTPSValidation("TLSv1.2")
                .when()
                .get("/123")
                .then()
                .extract().response().as(Book.class);
        Assert.assertEquals(book, res);
        // cleanup
        given()
                .when()
                .delete("/123");
    }

    @Test
    public void testAddBook() {
        //add
        Book book = new Book("123", "Rene", "Test Rest", 100);
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .body(book)
                .when().post().then()
                .statusCode(200);
        //cleanup
        given()
                .when()
                .delete("/123");
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book("123", "Rene", "Test Rest", 100);
        Book updatedBook = new Book("123", "Temka", "Updated Test Rest", 99);
        // add
        given()
                .contentType("application/json")
                .body(book)
                .when().post("").then()
                .statusCode(200);
        // update
        given()
                .contentType("application/json")
                .body(updatedBook)
                .when().put("/"+updatedBook.getIsbn()).then()
                .statusCode(200);
        Book res = given()
                .relaxedHTTPSValidation("TLSv1.2")
                .when()
                .get("/123")
                .then()
                .extract().response().as(Book.class);
        Assert.assertEquals(updatedBook, res);
        given()
                .when()
                .delete("/123");
    }

    @Test
    public void testDeleteBook() {
        // add the contact to be deleted book
        Book book = new Book("1", "Rene", "Test Rest", 100);
        given()
                .contentType("application/json")
                .body(book)
                .when().post().then()
                .statusCode(200);

        given()
                .when()
                .delete("/1");

        given()
                .when()
                .get("/1")
                .then()
                .statusCode(404)
                .and()
                .body("errorMessage",equalTo("Book with isbn = 1 is not found"));
    }

    @Test
    public void getAllBooks() {

        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .when()
                .get()
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }

    @Test
    public void searchBooks() {
        Book book = new Book("1", "Rene", "Test Rest", 100);
        Book book1 = new Book("2", "Temka", "Test Rest", 100);
        Book book2 = new Book("3", "Temka", "Test Rest", 100);
        given()
                .contentType("application/json")
                .body(book)
                .when().post().then()
                .statusCode(200);
        given()
                .contentType("application/json")
                .body(book1)
                .when().post().then()
                .statusCode(200);
        given()
                .contentType("application/json")
                .body(book2)
                .when().post().then()
                .statusCode(200);
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .when()
                .get("/author/Temka")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200);

        // clean up
        given()
                .when()
                .delete("/1");
        given()
                .when()
                .delete("/2");
        given()
                .when()
                .delete("/3");

    }
}
