package br.com.rruizdasilva;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class MetodosReutilizaveis {

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
