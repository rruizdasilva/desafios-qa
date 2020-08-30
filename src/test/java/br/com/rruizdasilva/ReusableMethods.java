package br.com.rruizdasilva;

import io.restassured.http.ContentType;

import static br.com.rruizdasilva.TestData.obterEnderecoAPI;
import static br.com.rruizdasilva.TestData.obterJsonComRegraDeDataDiferente;
import static io.restassured.RestAssured.given;

public class ReusableMethods {

  public static String obterNumeroRelatorio(String token, String regra, String endAPI){
    return given()
        .contentType(ContentType.JSON)
        .header("Authorization", token)
        .body(regra)
        .when()
        .post(endAPI)
        .then()
        .extract()
        .path("result.numero");
  }
}
