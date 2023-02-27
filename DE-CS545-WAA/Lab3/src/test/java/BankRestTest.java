import bookandaccount.lab3.Bank;

import bookandaccount.lab3.Book;
import bookandaccount.lab3.Transaction;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class BankRestTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/banks";
    }

    @Test
    public void testAddAccount() {
        Bank bank = new Bank(101, "Temka");

        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .body(bank)
                .when().post().then()
                .statusCode(200);
        //cleanup
        given()
                .when()
                .delete("/delete/101");
    }

    @Test
    public void testGetAccount() {
        Bank bank = new Bank(101, "Temka");
//        add
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .body(bank)
                .when().post().then()
                .statusCode(200);
//        get
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .when()
                .get("/101")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("accountNumber", equalTo(101))
                .body("accountHolder", equalTo("Temka"));
        //cleanup
        given()
                .when()
                .delete("/delete/101");
    }

    @Test
    public void testDeposit() {
        Bank bank = new Bank(101, "Temka");
//        add
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .body(bank)
                .when().post().then()
                .statusCode(200);

        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .when()
                .get("/deposit/101/50")
                .then()
                .statusCode(200);

        Bank res = given()
                .relaxedHTTPSValidation("TLSv1.2")
                .when()
                .get("/101")
                .then()
                .extract().response().as(Bank.class);
        Assert.assertEquals(res.calcBalance(), 50.0, 0);

        //cleanup
        given()
                .when()
                .delete("/delete/101");
    }

    @Test
    public void testWithdraw() {
        Bank bank = new Bank(101, "Temka");
//        add
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .body(bank)
                .when().post().then()
                .statusCode(200);
        // deposit 50
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .when()
                .get("/deposit/101/50")
                .then()
                .statusCode(200);
        // withdraw 25
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .when()
                .get("/withdraw/101/25")
                .then()
                .statusCode(200);
        // checking balance with 25
        Bank res = given()
                .relaxedHTTPSValidation("TLSv1.2")
                .when()
                .get("/101")
                .then()
                .extract().response().as(Bank.class);
        Assert.assertEquals(res.calcBalance(), 25.0, 0);

        //cleanup
        given()
                .when()
                .delete("/delete/101");
    }

    @Test
    public void testGetAllAccounts() {
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .when()
                .get("")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDeleteAccount() {
        Bank bank = new Bank(101, "Temka");
        //        add
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .body(bank)
                .when().post().then()
                .statusCode(200);
//        get
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .when()
                .get("/101")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("accountNumber", equalTo(101))
                .body("accountHolder", equalTo("Temka"));
        //cleanup
        given()
                .when()
                .delete("/delete/101");
        // again call get for checking delete method is successful
        given()
                .when()
                .get("/101")
                .then()
                .statusCode(404)
                .and()
                .body("errorMessage", CoreMatchers.equalTo("Bank account doesn't exist"));
    }

}
