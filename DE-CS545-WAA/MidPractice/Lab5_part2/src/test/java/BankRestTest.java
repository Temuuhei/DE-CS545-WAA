import bankmongo.lab5_part2.bank.DTO.BankAdapter;
import bankmongo.lab5_part2.bank.DTO.BankDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
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
        BankDTO bank = new BankDTO(101, "Temka");

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
        BankDTO bank = new BankDTO(101, "Temka");
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
        BankDTO bank = new BankDTO(101, "Temka");
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

        BankDTO res = given()
                .relaxedHTTPSValidation("TLSv1.2")
                .when()
                .get("/101")
                .then()
                .extract().response().as(BankDTO.class);
        Assert.assertEquals(BankAdapter.getBank(res).calcBalance(), 50.0, 0);

        //cleanup
        given()
                .when()
                .delete("/delete/101");
    }

    @Test
    public void testWithdraw() {
        BankDTO bank = new BankDTO(101, "Temka");
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
        BankDTO res = given()
                .relaxedHTTPSValidation("TLSv1.2")
                .when()
                .get("/101")
                .then()
                .extract().response().as(BankDTO.class);
        Assert.assertEquals(BankAdapter.getBank(res).calcBalance(), 25.0, 0);

        //cleanup
        given()
                .when()
                .delete("/delete/101");
    }

    @Test
    public void testTransferFund() {
        BankDTO fromBank = new BankDTO(102, "Temka");
        BankDTO toBank = new BankDTO(103, "Undraa");

        //        add
        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .body(fromBank)
                .when().post().then()
                .statusCode(200);

        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .body(toBank)
                .when().post().then()
                .statusCode(200);

        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .when()
                .get("/deposit/102/50")
                .then()
                .statusCode(200);

        given()
                .relaxedHTTPSValidation("TLSv1.2")
                .contentType("application/json")
                .when()
                .post("/transfer?fromAccountNumber=102&toAccountNumber=103&amount=30&description=test Transfer")
                .then()
                .statusCode(200);

        BankDTO res = given()
                .relaxedHTTPSValidation("TLSv1.2")
                .when()
                .get("/103")
                .then()
                .extract().response().as(BankDTO.class);
        Assert.assertEquals(BankAdapter.getBank(res).calcBalance(), 30.0, 0);

        given()
                .when()
                .delete("/delete/102");
        given()
                .when()
                .delete("/delete/103");

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
        BankDTO bank = new BankDTO(101, "Temka");
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
                .delete("/delete/101")
                .then()
                .statusCode(404);
        ;
//        // again call get for checking delete method is successful
//        given()
//                .when()
//                .get("/101")
//                .then()
//                .statusCode(404)
//                .and()
//                .body("errorMessage", CoreMatchers.equalTo("Bank account doesn't exist"));
    }
}
